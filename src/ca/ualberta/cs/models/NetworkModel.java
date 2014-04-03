package ca.ualberta.cs.models;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkModel {
	private static NetworkModel singleton = null;

	public static NetworkModel getInstance() {
		if (singleton == null) {
			singleton = new NetworkModel();
		}

		return singleton;
	}

	public static boolean isNetworkAvailable(Context context) {
		return ((ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo() != null;
	}
}
