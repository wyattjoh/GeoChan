/**
 * 
 */
package ca.ualberta.cs.models;

import java.util.ArrayList;

/**
 * @author wyatt
 *
 */
public class TopicModelList {
	private static TopicModelList singleton = null;
	
	private ArrayList<TopicModel> theTopicModelArrayList;
	
	private TopicModelList() {
		theTopicModelArrayList = new ArrayList<TopicModel>();
	}
	
	/*
	 * Returns a shared TopicModelList
	 */
	public static TopicModelList shared() {
		if (singleton == null) {
			singleton = new TopicModelList();
		}
		
		return singleton;
	}
	
	public void addTopicModel(TopicModel theTopicModel) {
		
	}
	
	public void setTopicModelArrayList(ArrayList<TopicModel> theTopicModelArrayList) {
		this.theTopicModelArrayList = theTopicModelArrayList;
	}
	
	public ArrayList<TopicModel> getTopicModelArrayList() {
		return theTopicModelArrayList;
	}
}
