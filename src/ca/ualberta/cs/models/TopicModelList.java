/**
 * 
 */
package ca.ualberta.cs.models;

/**
 * Manages a list of topic models
 * 
 * @author wyatt
 * 
 */
public class TopicModelList extends PostModelList<TopicModel> {
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
}
