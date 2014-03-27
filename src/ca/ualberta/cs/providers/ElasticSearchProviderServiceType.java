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

import android.util.Log;
import ca.ualberta.cs.models.ElasticSearchOperationFactory;
import ca.ualberta.cs.models.ElasticSearchOperationRequest;
import ca.ualberta.cs.models.ElasticSearchOperationResponse;
import ca.ualberta.cs.models.ElasticSearchResponse;
import ca.ualberta.cs.models.TopicModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public enum ElasticSearchProviderServiceType {
	ADD_TOPIC {

		@Override
		public ElasticSearchOperationResponse doInBackground(ElasticSearchOperationRequest theRequest) {
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
				
				int theVersion = esResponse.getVersion();
				theTopic.setVersion(theVersion);
				
				ElasticSearchOperationResponse theResponse = ElasticSearchOperationFactory.responseFromRequest(theRequest);
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

		@Override
		public void onPostExecute(ElasticSearchOperationResponse theResponse) {
			theResponse.getPostModelList().add(theResponse.getTopicModel());
		}
		
	},
	UPDATE_TOPIC {

		@Override
		public ElasticSearchOperationResponse doInBackground(ElasticSearchOperationRequest theRequest) {
			TopicModel theTopic = theRequest.getTopicModel();
			
			HttpPost request;
			if (theTopic.getVersion() > 1) {
				request = new HttpPost(getVersionedEndpoint("topic", theTopic));
			}
			else {
				request = new HttpPost(getEndpointUrl("topic"));
			}

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
				
				int theVersion = esResponse.getVersion();
				theTopic.setVersion(theVersion);
				
				ElasticSearchOperationResponse theResponse = ElasticSearchOperationFactory.responseFromRequest(theRequest);
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

		@Override
		public void onPostExecute(ElasticSearchOperationResponse theResponse) {
			theResponse.getPostModelList().update(theResponse.getTopicModel());
		}
		
	};
	
	public abstract ElasticSearchOperationResponse doInBackground(ElasticSearchOperationRequest theRequest);
	public abstract void onPostExecute(ElasticSearchOperationResponse theResponse);
	
	private static String urlIndex = "http://cmput301.softwareprocess.es:8080/cmput301w14t12/";
	private static Gson gson = GeoChanGson.getGson();
	private static HttpClient client = new DefaultHttpClient();
	
	private static String getStringFromResponse(HttpResponse response) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}
	
	private static String getEndpointUrl(String endpoint) {
		return urlIndex + "/" + endpoint;
	}
	
	private static String getVersionedEndpoint(String endpoint, TopicModel theTopic) {
		String theUrl = urlIndex + "/" + endpoint + "/";
		
		String theVersion = Integer.toString(theTopic.getVersion());
		
		return theUrl + theTopic.getId() + "?version=" + theVersion;
	}
}
