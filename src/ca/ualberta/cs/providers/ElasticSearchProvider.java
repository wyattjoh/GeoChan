/**
 * 
 */
package ca.ualberta.cs.providers;

import ca.ualberta.cs.models.ElasticSearchRequest;
import ca.ualberta.cs.models.FollowingPostModelList;
import ca.ualberta.cs.models.PostModel;
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

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.providers.ElasticSearchInterface#getTopics(java.lang.Integer, java.lang.Integer, java.lang.Integer)
	 */
	public void getTopics(Integer withOrder,
			Integer topicCount, Integer theOffset) {
		// TODO Auto-generated method stub
	}

	public void addTopic(PostModel theTopic) {
		// TODO Auto-generated method stub
		ElasticSearchRequest theRequest = new ElasticSearchRequest(ElasticSearchProviderConstants.TYPE_ADD_TOPIC);
		
	}
	
	public void updateTopic(String theId, FollowingPostModelList<TopicModel> theListToInsert) {
		
	}
}
