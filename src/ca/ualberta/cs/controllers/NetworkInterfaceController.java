package ca.ualberta.cs.controllers;

import android.content.Context;
import ca.ualberta.cs.providers.ElasticSearchProvider;

public class NetworkInterfaceController {
	private static NetworkInterfaceController singleton = null;
	private Context theContext;

	private NetworkInterfaceController(Context theContext) {
		this.theContext = theContext;
	}

	public static NetworkInterfaceController getControllerFromContext(
			Context theContext) {
		if (singleton == null) {
			singleton = new NetworkInterfaceController(theContext);
		}
		return singleton;
	}

	public void refreshPosts() {
		// Refresh TopicModelList
		ElasticSearchProvider.getProviderWithContext(theContext).getTopics(0,
				30);

		// TODO: Refresh FavoriteTopicTopicModelList
		// TODO: Refresh FavoriteCommentModelList
	}
}
