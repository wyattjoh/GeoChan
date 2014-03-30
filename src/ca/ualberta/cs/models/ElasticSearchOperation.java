package ca.ualberta.cs.models;

import java.util.ArrayList;

import ca.ualberta.cs.providers.ElasticSearchProviderServiceHandler;

abstract public class ElasticSearchOperation {
	private ElasticSearchProviderServiceHandler requestType;
	private TopicModel theModel = null;
	private PostModelList<TopicModel> thePostModelList = null;
	private ArrayList<TopicModel> theTopicModels = new ArrayList<TopicModel>();
	
	protected int size;
	protected int from;
	
	public ElasticSearchOperation(ElasticSearchProviderServiceHandler theRequestType) {
		this.requestType = theRequestType;
	}
	
	public ElasticSearchProviderServiceHandler getRequestType() {
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
	
	public void setRange(int from, int size) {
		this.size = size;
		this.from = from;
	}

	/**
	 * @return the theTopicModels
	 */
	public ArrayList<TopicModel> getTheTopicModels() {
		return theTopicModels;
	}

	/**
	 * @param theTopicModels the theTopicModels to set
	 */
	public void setTheTopicModels(ArrayList<TopicModel> theTopicModels) {
		this.theTopicModels = theTopicModels;
	}
}
