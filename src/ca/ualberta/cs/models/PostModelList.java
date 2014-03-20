package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.Collections;

abstract public class PostModelList<T extends PostModel> {
	private T selectedPostModel = null;
	private ArrayList<T> postModelArrayList;
	
	protected PostModelList() {
		this.postModelArrayList = new ArrayList<T>();
	}
	
	public void setSelection(int position) {
		this.selectedPostModel = postModelArrayList.get(position);
	}
	
	public void setSelection(T selectedPostModel) {
		this.selectedPostModel = selectedPostModel;
	}
	
	public T getSelection() {
		return this.selectedPostModel;
	}
	
	/*
	 * Adds a model to the top of the list singleton
	 */
	public void add(T theModel) {
		this.postModelArrayList.add(0, theModel);
	}
	
	public void setArrayList(ArrayList<T> postModelArrayList) {
		this.postModelArrayList = postModelArrayList;
	}
	
	/*
	 * Gets the topic model array list
	 */
	public ArrayList<T> getArrayList() {
		return this.postModelArrayList;
	}
	
	/*
	 * Sorts theTopicModelArrayList by score 
	 */
	public void sortByScore() {
		Collections.sort(this.postModelArrayList, PostModel.COMPARE_BY_SCORE);
	}
	
	/*
	 * Sorts theTopicModelArrayList by date
	 */
	public void sortByDate() {
		Collections.sort(this.postModelArrayList, PostModel.COMPARE_BY_DATE);
	}
}
