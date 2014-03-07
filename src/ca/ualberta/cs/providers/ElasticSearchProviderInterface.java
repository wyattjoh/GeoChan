/**
 * 
 */
package ca.ualberta.cs.providers;

import java.util.ArrayList;

import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.TopicModel;

/**
 * @author wyatt
 *
 */
public interface ElasticSearchProviderInterface {
	/*
	 * Getter methods
	 */
	public ArrayList<TopicModel> getTopics(Integer withOrder, Integer topicCount, Integer theOffset);
	
	
	/*
	 * Setter methods
	 */
	public void addTopic(PostModel theTopic);
}
