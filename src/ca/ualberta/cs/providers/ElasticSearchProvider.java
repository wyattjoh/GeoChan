/**
 * 
 */
package ca.ualberta.cs.providers;

import android.content.Context;
import ca.ualberta.cs.models.ElasticSearchOperationRequest;
import ca.ualberta.cs.models.PostModelList;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;

/**
 * @author wyatt
 *
 * Provides interface to remote and proxies objects
 *
 */
public class ElasticSearchProvider {
	private static ElasticSearchProvider singleton = null;
	private Context theContext;
	
	public static ElasticSearchProvider getProviderWithContext(Context theContext) {
		if (singleton == null) {
			singleton = new ElasticSearchProvider();
		}
			
		singleton.theContext = theContext;
		
		return singleton;
	}
	
	public void addTopic(TopicModel theTopic, PostModelList<TopicModel> thePostModelList) {
		// Build a request
		ElasticSearchOperationRequest request = new ElasticSearchOperationRequest(ElasticSearchProviderServiceHandler.ADD_TOPIC);
		request.setPostModelList(thePostModelList);
		request.setTopicModel(theTopic);
		
		// Execute the request
		new ElasticSearchProviderService().execute(request);
	}
	
	public void updateTopic(TopicModel theTopic, PostModelList<TopicModel> thePostModelList) {
		// Build a request
		ElasticSearchOperationRequest request = new ElasticSearchOperationRequest(ElasticSearchProviderServiceHandler.UPDATE_TOPIC);
		request.setPostModelList(thePostModelList);
		request.setTopicModel(theTopic);
		
		// Execute the request
		new ElasticSearchProviderService().execute(request);
	}
	
	public void getTopics(int from, int size) {
		ElasticSearchOperationRequest request = new ElasticSearchOperationRequest(ElasticSearchProviderServiceHandler.GET_POSTS);
		request.setPostModelList(TopicModelList.getInstance());
		request.setRange(from, size);
		
		new ElasticSearchProviderService().execute(request);
	}
}
