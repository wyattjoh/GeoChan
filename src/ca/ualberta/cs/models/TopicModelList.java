/**
 * 
 */
package ca.ualberta.cs.models;

/**
 * @author wyatt
 * 
 */
public class TopicModelList extends PostModelList<TopicModel> implements
		UpdateableListInterface {
	private static TopicModelList singleton = null;

	/*
	 * Returns a shared TopicModelList
	 */
	public static TopicModelList getInstance() {
		if (singleton == null) {
			singleton = new TopicModelList();
		}

		return singleton;
	}

	@Override
	public void updateFromNetwork() {
		// TODO Auto-generated method stub

	}
}
