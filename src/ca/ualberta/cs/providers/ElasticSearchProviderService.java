/**
 * 
 */
package ca.ualberta.cs.providers;

import android.os.AsyncTask;

/**
 * @author wyatt
 *
 * Provides async http requests against the proxy provider
 *
 */
public class ElasticSearchProviderService extends
		AsyncTask<Integer, Integer, Long> {
	private static String baseUrl = "http://cmput301.softwareprocess.es:8080/cmput301w14t12/";

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected Long doInBackground(Integer... params) {
		// TODO Auto-generated method stub
		return null;
	}

}
