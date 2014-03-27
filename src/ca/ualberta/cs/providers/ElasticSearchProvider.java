/**
 * 
 */
package ca.ualberta.cs.providers;

import ca.ualberta.cs.models.ElasticSearchOperationRequest;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.TopicModel;

/**
 * @author wyatt
 *
 * Provides interface to remote and proxies objects
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
	
	public void addTopic(TopicModel theTopic, PostModelList<TopicModel> thePostModelList) {
		// Build a request
		ElasticSearchOperationRequest request = new ElasticSearchOperationRequest(ElasticSearchProviderServiceType.ADD_TOPIC);
		request.setPostModelList(thePostModelList);
		request.setTopicModel(theTopic);
		
		// Execute the request
		new ElasticSearchProviderService().execute(request);
	}
	
	public void updateTopic(TopicModel theTopic, PostModelList<TopicModel> thePostModelList) {
		// Build a request
		ElasticSearchOperationRequest request = new ElasticSearchOperationRequest(ElasticSearchProviderServiceType.UPDATE_TOPIC);
		request.setPostModelList(thePostModelList);
		request.setTopicModel(theTopic);
		
		// Execute the request
		new ElasticSearchProviderService().execute(request);
	}
}
