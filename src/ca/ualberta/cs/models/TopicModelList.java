/**
 * 
 */
package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author wyatt
 *
 */
public class TopicModelList {
	private static TopicModelList singleton = null;
	
	private ArrayList<TopicModel> theTopicModelArrayList;
	private TopicModel selectedTopicModel = null;
	
	private TopicModelList() {
		theTopicModelArrayList = new ArrayList<TopicModel>();
	}
	
	/*
	 * Returns a shared TopicModelList
	 */
	public static TopicModelList getInstance() {
		if (singleton == null) {
			singleton = new TopicModelList();
		}
		
		return singleton;
	}
	
	/*
	 * Adds a topic model to the top of the list singleton
	 */
	public void addTopicModel(TopicModel theTopicModel) {
		this.theTopicModelArrayList.add(0, theTopicModel);
	}
	
	public void setTopicModelArrayList(ArrayList<TopicModel> theTopicModelArrayList) {
		this.theTopicModelArrayList = theTopicModelArrayList;
	}
	
	/*
	 * Gets the topic model array list
	 */
	public ArrayList<TopicModel> getTopicModelArrayList() {
		return this.theTopicModelArrayList;
	}
	
	/*
	 * Sorts theTopicModelArrayList by score 
	 */
	public void sortByScore() {
		Collections.sort(this.theTopicModelArrayList, PostModel.COMPARE_BY_SCORE);
	}
	
	/*
	 * Sorts theTopicModelArrayList by date
	 */
	public void sortByDate() {
		Collections.sort(this.theTopicModelArrayList, PostModel.COMPARE_BY_DATE);
	}
	
	/*
	 * Sorts theTopicModelArrayList by location
	 */
	public void sortByLocation() {
		// TODO: Implement method
	}
	
	/*
	 * Selects a TopicModel in a list by a position
	 */
	public void selectTopicModel(int position) {
		// TODO: Implement method

	}
}
