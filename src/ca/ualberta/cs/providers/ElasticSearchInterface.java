/**
 * 
 */
package ca.ualberta.cs.providers;

import java.util.ArrayList;

import ca.ualberta.cs.models.TopicModel;

/**
 * @author wyatt
 *
 */
public interface ElasticSearchInterface {
	public static Integer ORDER_DATE = 0x0;
	
	public ArrayList<TopicModel> getTopics(Integer withOrder, Integer topicCount, Integer theOffset);
}
