package ca.ualberta.cs.models;

/**
 * Stores the selected topic model list
 * 
 * @author wyatt
 *
 */
public class SelectedTopicModelList extends SelectedPostModelList<TopicModel> {
	public static SelectedTopicModelList singleton = new SelectedTopicModelList();

	public static void setTopicList(PostModelList<TopicModel> theList) {
		singleton.setSelectedPostModelList(theList);
	}

	public static PostModelList<TopicModel> getTopicList() {
		return singleton.getSelectedPostModelList();
	}
}
