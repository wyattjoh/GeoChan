/**
 * 
 */
package ca.ualberta.cs.providers;

import java.util.ArrayList;

import ca.ualberta.cs.models.TopicModel;

/**
 * @author wyatt
 *
 * Provides interface to remote and proxies objects
 *
 */
public class ElasticSearchProvider implements ElasticSearchInterface {
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
	@Override
	public ArrayList<TopicModel> getTopics(Integer withOrder,
			Integer topicCount, Integer theOffset) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
