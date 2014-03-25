package ca.ualberta.cs.models;

import ca.ualberta.cs.providers.ElasticSearchProviderConstants;

abstract public class ElasticSearchOperation {
	private ElasticSearchProviderConstants requestMode;
	private TopicModel theModel = null;
	private PostModelList<TopicModel> thePostModelList = null;
	
	public ElasticSearchOperation(ElasticSearchProviderConstants theRequestType) {
		this.requestMode = theRequestType;
	}
	
	public ElasticSearchProviderConstants getRequestMode() {
		return requestMode;
	}
	
	public void setTopicModel(TopicModel theModel) {
		this.theModel = theModel;
	}
	
	public TopicModel getTopicModel() {
		return this.theModel;
	}
	
	public void setPostModelList(PostModelList<TopicModel> thePostModelList) {
		this.thePostModelList = thePostModelList;
	}
	
	public PostModelList<TopicModel> getPostModelList() {
		return this.thePostModelList;
	}
}
