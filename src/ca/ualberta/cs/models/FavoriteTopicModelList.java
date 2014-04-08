/**
 * 
 */
package ca.ualberta.cs.models;

import java.io.InputStreamReader;

import ca.ualberta.cs.providers.GeoChanGsonOffline;

import com.google.gson.Gson;

import android.content.Context;

/**
 * Stores a list of favorites
 * 
 * @author wyatt
 * 
 */
public class FavoriteTopicModelList extends FollowingPostModelList<TopicModel> {
	private static FavoriteTopicModelList singleton = null;

	private static final String FILENAME = "FavoriteTopicModelList.json";

	private FavoriteTopicModelList(Context applicationContext) {
		super(applicationContext);
	}

	public static FavoriteTopicModelList createInstance(
			Context applicationContext) {
		if (singleton == null) {
			singleton = new FavoriteTopicModelList(applicationContext);
		}
		return singleton;
	}

	public static FavoriteTopicModelList getInstance() {
		if (singleton == null) {
			throw new RuntimeException(
					"Cannot get an instance before you create it!");
		}
		return singleton;
	}

	@Override
	protected String getFilenameString() {
		return FILENAME;
	}

	@Override
	protected TopicModel[] inputStreaReaderToArray(InputStreamReader isr) {
		Gson gson = GeoChanGsonOffline.getGson();

		return gson.fromJson(isr, TopicModel[].class);
	}

	@Override
	protected TopicModel[] arrayListToArray() {
		// TODO Auto-generated method stub
		return this.getArrayList().toArray(new TopicModel[0]);
	}

	@Override
	public TopicModel specializePost(TopicModel thePost) {
		thePost.setIsFavorite(true);
		return thePost;
	}
}
