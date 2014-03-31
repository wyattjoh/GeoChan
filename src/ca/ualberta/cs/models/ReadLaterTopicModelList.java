/**
 * 
 */
package ca.ualberta.cs.models;

import java.io.InputStreamReader;

import android.content.Context;
import ca.ualberta.cs.providers.GeoChanGson;

import com.google.gson.Gson;

/**
 * @author wyatt
 * 
 */
public class ReadLaterTopicModelList extends FollowingPostModelList<TopicModel> {
	private static ReadLaterTopicModelList singleton = null;

	private static final String FILENAME = "FavoriteTopicModelList.json";

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
		return FILENAME;
	}

	@Override
	protected TopicModel[] inputStreaReaderToArray(InputStreamReader isr) {
		Gson gson = GeoChanGson.getGson();

		return gson.fromJson(isr, TopicModel[].class);
	}

	@Override
	protected TopicModel[] arrayListToArray() {
		// TODO Auto-generated method stub
		return this.getArrayList().toArray(new TopicModel[0]);
	}
}
