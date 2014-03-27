/**
 * 
 */
package ca.ualberta.cs.providers;

import android.os.AsyncTask;
import ca.ualberta.cs.models.ElasticSearchOperationRequest;
import ca.ualberta.cs.models.ElasticSearchOperationResponse;

/**
 * @author wyatt
 * 
 *         Provides async http requests against the proxy provider
 * 
 */
public class ElasticSearchProviderService extends
		AsyncTask<ElasticSearchOperationRequest, Integer, ElasticSearchOperationResponse[]> {

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
			ElasticSearchProviderServiceType requestType = theResponse.getRequestType();
			requestType.onPostExecute(theResponse);
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
			ElasticSearchProviderServiceType requestType = theRequest.getRequestType();
			response[i] = requestType.doInBackground(theRequest);
		}

		return response;
	}
}