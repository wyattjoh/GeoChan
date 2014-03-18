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
	
	public void addTopicModel(TopicModel theTopicModel) {
		theTopicModelArrayList.add(theTopicModel);
	}
	
	public void setTopicModelArrayList(ArrayList<TopicModel> theTopicModelArrayList) {
		this.theTopicModelArrayList = theTopicModelArrayList;
	}
	
	public ArrayList<TopicModel> getTopicModelArrayList() {
		return theTopicModelArrayList;
	}
	
	/*
	 * Sorting Methods
	 */
	public void sortByScore() {
		Collections.sort(this.theTopicModelArrayList, PostModel.COMPARE_BY_SCORE);
	}

	public void sortByDate() {
		Collections.sort(this.theTopicModelArrayList, PostModel.COMPARE_BY_DATE);
	}
	
	public void sortByLocation() {
		//Not yet implemented
	}
	
	public void selectTopicModel(int position) {
		

	}
}
