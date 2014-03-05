/**
 * 
 */
package ca.ualberta.cs.providers;

import java.util.ArrayList;

import ca.ualberta.cs.models.TopicModel;

/**
 * @author wyatt
 * 
 * Simulates a networked environment for the purposes of caching data
 */
public class ElasticSearchProxyProvider implements ElasticSearchInterface {

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
