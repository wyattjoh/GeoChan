/**
 * 
 */
package ca.ualberta.cs.models;

import java.io.InputStreamReader;

import ca.ualberta.cs.providers.GeoChanGsonOffline;

import com.google.gson.Gson;

import android.content.Context;

/**
 * @author wyatt
 * 
 */
public class ReadLaterTopicModelList extends FollowingPostModelList<TopicModel> {
	private static ReadLaterTopicModelList singleton = null;

	private ReadLaterTopicModelList(Context applicationContext) {
		super(applicationContext);
	}

	public static ReadLaterTopicModelList createInstance(
			Context applicationContext) {
		if (singleton == null) {
			singleton = new ReadLaterTopicModelList(applicationContext);
		}
		return singleton;
	}

	public static ReadLaterTopicModelList getInstance() {
		if (singleton == null) {
			throw new RuntimeException(
					"Cannot get an instance before you create it!");
		}
		return singleton;
	}

	@Override
	protected String getFilenameString() {
		return "ReadLaterTopicModelList.json";
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
		thePost.setIsReadLater(true);
		return thePost;
	}
}
