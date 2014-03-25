/**
 * 
 */
package ca.ualberta.cs.providers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;
import ca.ualberta.cs.models.ElasticSearchResponse;
import ca.ualberta.cs.models.ElasticSearchOperationTransformer;
import ca.ualberta.cs.models.ElasticSearchOperationRequest;
import ca.ualberta.cs.models.ElasticSearchOperationResponse;
import ca.ualberta.cs.models.TopicModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author wyatt
 * 
 *         Provides async http requests against the proxy provider
 * 
 */
public class ElasticSearchProviderService extends
		AsyncTask<ElasticSearchOperationRequest, Integer, ElasticSearchOperationResponse[]> {
	private static String urlIndex = "http://cmput301.softwareprocess.es:8080/cmput301w14t12/";

	private Gson gson;
	private HttpClient client;

	public ElasticSearchProviderService() {
		this.gson = GeoChanGson.getGson();
		this.client = new DefaultHttpClient();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(ElasticSearchOperationResponse[] result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);

		int size = result.length;

		for (int i = 0; i < size; i++) {
			ElasticSearchOperationResponse theResponse = result[i];
			
			switch (theResponse.getRequestMode()) {
			case TYPE_ADD_TOPIC:
				theResponse.getPostModelList().add(theResponse.getTopicModel());
				break;

			default:
				break;
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected ElasticSearchOperationResponse[] doInBackground(
			ElasticSearchOperationRequest... requests) {
		int size = requests.length;
		ElasticSearchOperationResponse[] response = new ElasticSearchOperationResponse[size];

		for (int i = 0; i < size; i++) {
			ElasticSearchOperationRequest theRequest = requests[i];

			switch (theRequest.getRequestMode()) {
			case TYPE_ADD_TOPIC:
				response[i] = addTopic(theRequest);
				break;
			default:
				break;
			}
		}

		return response;
	}

	public ElasticSearchOperationResponse addTopic(ElasticSearchOperationRequest theRequest) {
		TopicModel theTopic = theRequest.getTopicModel();

		HttpPost request = new HttpPost(getEndpointUrl("topic"));

		String jsonString = gson.toJson(theTopic);

		// Add to entity
		try {
			request.setEntity(new StringEntity(jsonString));
			// run it
			HttpResponse response = client.execute(request);

			String jsonResponse = getStringFromResponse(response);

			Log.w("ElasticSearchProviderService", jsonResponse);

			// We have to tell GSON what type we expect
			Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<TopicModel>>() {
			}.getType();

			ElasticSearchResponse<TopicModel> esResponse = gson.fromJson(
					jsonResponse, elasticSearchResponseType);
			
			String theId = esResponse.getId();
			theTopic.setId(theId);
			
			ElasticSearchOperationResponse theResponse = ElasticSearchOperationTransformer.responseFromRequest(theRequest);
			theResponse.setTopicModel(theTopic);
			
			return theResponse;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private String getEndpointUrl(String endpoint) {
		return urlIndex + "/" + endpoint;
	}

	private String getStringFromResponse(HttpResponse response) {
		String json = "";
		try {
			// get the entity
			response.getStatusLine().toString();
			HttpEntity entity = response.getEntity();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					entity.getContent()));
			json = reader.readLine();

			String output;

			while ((output = reader.readLine()) != null) {
				json += output;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return json;
	}

}