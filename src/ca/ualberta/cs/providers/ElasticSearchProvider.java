/**
 * 
 */
package ca.ualberta.cs.providers;

import java.util.ArrayList;

import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.ElasticSearchOperationRequest;
import ca.ualberta.cs.models.FollowingPostModelList;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.models.UpdatePackage;

/**
 * @author wyatt
 * 
 *         Provides interface to remote and proxies objects
 * 
 */
public class ElasticSearchProvider {
	private static ElasticSearchProvider singleton = null;

	public static ElasticSearchProvider getProvider() {
		if (singleton == null) {
			singleton = new ElasticSearchProvider();
		}

		return singleton;
	}

	public void addTopic(TopicModel theTopic,
			PostModelList<TopicModel> thePostModelList) {
		// Build a request
		ElasticSearchOperationRequest request = new ElasticSearchOperationRequest(
				ElasticSearchProviderServiceHandler.ADD_TOPIC);
		request.setPostModelList(thePostModelList);
		request.setTopicModel(theTopic);

		// Execute the request
		new ElasticSearchProviderService().execute(request);
	}

	public void updateTopic(TopicModel theTopic,
			PostModelList<TopicModel> thePostModelList) {
		// Build a request
		ElasticSearchOperationRequest request = new ElasticSearchOperationRequest(
				ElasticSearchProviderServiceHandler.UPDATE_TOPIC);
		request.setPostModelList(thePostModelList);
		request.setTopicModel(theTopic);

		// Execute the request
		new ElasticSearchProviderService().execute(request);
	}

	public void getTopics(int from, int size) {
		ElasticSearchOperationRequest request = new ElasticSearchOperationRequest(
				ElasticSearchProviderServiceHandler.GET_POSTS);
		request.setPostModelList(TopicModelList.getInstance());
		request.setRange(from, size);

		new ElasticSearchProviderService().execute(request);
	}
	
	public void performMultiGetComments(ArrayList<UpdatePackage<CommentModel>> theIds, FollowingPostModelList<CommentModel> theModelList) {
		ElasticSearchOperationRequest request = new ElasticSearchOperationRequest(ElasticSearchProviderServiceHandler.MULTI_GET_COMMENTS);
		request.setTheFollowingCommentsList(theModelList);
		request.setTheCommentIdsToGet(theIds);
		
		new ElasticSearchProviderService().execute(request);
	}
	
	public void performMultiGetTopics(ArrayList<UpdatePackage<TopicModel>> theIds, FollowingPostModelList<TopicModel> theModelList) {
		ElasticSearchOperationRequest request = new ElasticSearchOperationRequest(ElasticSearchProviderServiceHandler.MULTI_GET_TOPICS);
		request.setTheFollowingTopicsList(theModelList);
		request.setTheTopicIdsToGet(theIds);
		
		new ElasticSearchProviderService().execute(request);
	}
}
