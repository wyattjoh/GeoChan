package ca.ualberta.cs.models;

import java.util.ArrayList;

import ca.ualberta.cs.providers.ElasticSearchProviderServiceHandler;

abstract public class ElasticSearchOperation {
	private ElasticSearchProviderServiceHandler requestType;
	private TopicModel theModel = null;
	private FollowingPostModelList<TopicModel> theFollowingTopicsList = null;
	private FollowingPostModelList<CommentModel> theFollowingCommentsList = null;
	private PostModelList<TopicModel> thePostModelList = null;
	private ArrayList<TopicModel> theTopicModels = new ArrayList<TopicModel>();
	private ArrayList<UpdatePackage<CommentModel>> theCommentIdsToGet = null;
	private ArrayList<UpdatePackage<TopicModel>> theTopicIdsToGet = null;

	protected int size;
	protected int from;

	public ElasticSearchOperation(
			ElasticSearchProviderServiceHandler theRequestType) {
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
	 * @param theTopicModels
	 *            the theTopicModels to set
	 */
	public void setTheTopicModels(ArrayList<TopicModel> theTopicModels) {
		this.theTopicModels = theTopicModels;
	}

	/**
	 * @return the theFollowingList
	 */
	public FollowingPostModelList<TopicModel> getTheFollowingTopicsList() {
		return theFollowingTopicsList;
	}

	/**
	 * @param theFollowingList
	 *            the theFollowingList to set
	 */
	public void setTheFollowingTopicsList(
			FollowingPostModelList<TopicModel> theFollowingList) {
		this.theFollowingTopicsList = theFollowingList;
	}

	/**
	 * @return the theFollowingList
	 */
	public FollowingPostModelList<CommentModel> getTheFollowingCommentsList() {
		return theFollowingCommentsList;
	}

	/**
	 * @param theFollowingList
	 *            the theFollowingList to set
	 */
	public void setTheFollowingCommentsList(
			FollowingPostModelList<CommentModel> theFollowingList) {
		this.theFollowingCommentsList = theFollowingList;
	}

	/**
	 * @return the theTopicIdsToGet
	 */
	public ArrayList<UpdatePackage<TopicModel>> getTheTopicIdsToGet() {
		return theTopicIdsToGet;
	}

	/**
	 * @return the theCommentIdsToGet
	 */
	public ArrayList<UpdatePackage<CommentModel>> getTheCommentIdsToGet() {
		return theCommentIdsToGet;
	}

	/**
	 * @param theIds
	 *            the theIdsToGet to set
	 */
	public void setTheTopicIdsToGet(ArrayList<UpdatePackage<TopicModel>> theIds) {
		this.theTopicIdsToGet = theIds;
	}

	/**
	 * @param theIds
	 *            the theIdsToGet to set
	 */
	public void setTheCommentIdsToGet(
			ArrayList<UpdatePackage<CommentModel>> theIds) {
		this.theCommentIdsToGet = theIds;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * @param from
	 *            the from to set
	 */
	public void setFrom(int from) {
		this.from = from;
	}
}
