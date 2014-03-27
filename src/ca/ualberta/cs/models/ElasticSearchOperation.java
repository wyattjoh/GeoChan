package ca.ualberta.cs.models;

import ca.ualberta.cs.providers.ElasticSearchProviderServiceType;

abstract public class ElasticSearchOperation {
	private ElasticSearchProviderServiceType requestType;
	private TopicModel theModel = null;
	private PostModelList<TopicModel> thePostModelList = null;
	
	public ElasticSearchOperation(ElasticSearchProviderServiceType theRequestType) {
		this.requestType = theRequestType;
	}
	
	public ElasticSearchProviderServiceType getRequestType() {
		return requestType;
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
