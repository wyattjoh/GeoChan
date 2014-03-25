/**
 * 
 */
package ca.ualberta.cs.providers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;
import ca.ualberta.cs.models.ElasticSearchRequest;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.TopicModel;

import com.google.gson.Gson;

/**
 * @author wyatt
 * 
 *         Provides async http requests against the proxy provider
 * 
 */
public class ElasticSearchProviderService extends
		AsyncTask<ElasticSearchRequest, Integer, String> {
	private static String urlIndex = "http://cmput301.softwareprocess.es:8080/cmput301w14t12/";

	private Integer mode;
	private Gson gson;
	private HttpClient client;

	public ElasticSearchProviderService(Integer mode) {
		// Mode
		this.mode = mode;

		// Gson objects
		this.gson = GeoChanGson.getGson();
		this.client = new DefaultHttpClient();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected String doInBackground(ElasticSearchRequest... requests) {
		int size = requests.length;

		for (int i = 0; i < size; i++) {
			ElasticSearchRequest theRequest = requests[i];
			
			switch (theRequest.getRequestMode()) {
			case TYPE_ADD_TOPIC:

				break;

			default:
				break;
			}
		}

		return null;
	}

	public ArrayList<TopicModel> getTopics(Integer withOrder,
			Integer topicCount, Integer theOffset) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addTopic(PostModel theTopic) {
		// TODO Auto-generated method stub
		// HttpPost request = new HttpPost("http://requestb.in/xpn5gvxp");
		HttpPost request = new HttpPost(getEndpointUrl("topic"));

		String jsonString = gson.toJson(theTopic);

		try {
			// Add to entity
			request.setEntity(new StringEntity(jsonString));

			// run it
			HttpResponse response = client.execute(request);

			String jsonResponse = getStringFromResponse(response);

			Log.w("ElasticSearchProviderService", jsonResponse);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
