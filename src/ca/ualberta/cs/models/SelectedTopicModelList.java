package ca.ualberta.cs.models;

public class SelectedTopicModelList extends SelectedPostModelList<TopicModel> {
	public static SelectedTopicModelList singleton = new SelectedTopicModelList();

	public static void setTopicList(PostModelList<TopicModel> theList) {
		singleton.setSelectedPostModelList(theList);
	}

	public static PostModelList<TopicModel> getTopicList() {
		return singleton.getSelectedPostModelList();
	}
}
