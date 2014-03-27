/**
 * 
 */
package ca.ualberta.cs.models;

import java.io.InputStreamReader;

import ca.ualberta.cs.providers.GeoChanGson;

import com.google.gson.Gson;

import android.content.Context;

/**
 * @author wyatt
 * A list for managing the list of favorite comment models
 */
public class FavoriteCommentModelList extends FollowingPostModelList<CommentModel> {
	private static FavoriteCommentModelList singleton = null;

	protected FavoriteCommentModelList(Context applicationContext) {
		super(applicationContext);
	}

	/**
	 * Creates a instance of FavoriteCommentModelList to live on the stack
	 * @param applicationContext
	 * @return FavoriteCommentModelList singleton
	 */
	public static FavoriteCommentModelList createInstance(Context applicationContext) {
		if (singleton == null) {
			singleton = new FavoriteCommentModelList(applicationContext);
		}
		return singleton;
	}
	
	/**
	 * Returns the singleton of FavoriteCommentModelList or throws an exception if none created
	 * @return FavoriteCommentModelList singleton
	 */
	public static FavoriteCommentModelList getInstance() {
		if (singleton == null) {
			throw new RuntimeException("Cannot get an instance before you create it!");
		}
		return singleton;
	}
	
	/* (non-Javadoc)
	 * @see ca.ualberta.cs.models.FollowingPostModelList#getFilenameString()
	 */
	@Override
	protected String getFilenameString() {
		// TODO Auto-generated method stub
		return "FavoriteCommentModelList.json";
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.models.FollowingPostModelList#arrayListToArray()
	 */
	@Override
	protected CommentModel[] arrayListToArray() {
		return this.getArrayList().toArray(new CommentModel[0]);
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.models.FollowingPostModelList#inputStreaReaderToArray(java.io.InputStreamReader)
	 */
	@Override
	protected CommentModel[] inputStreaReaderToArray(InputStreamReader isr) {
		Gson gson = GeoChanGson.getGson();
		
		return gson.fromJson(isr, CommentModel[].class);
	}

}
