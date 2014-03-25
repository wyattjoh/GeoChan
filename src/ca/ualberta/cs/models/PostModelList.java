package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.Collections;

import android.util.Log;
import android.widget.BaseAdapter;

public class PostModelList<T extends PostModel> {
	private T selectedPostModel = null;
	private ArrayList<T> postModelArrayList;
	private ArrayList<BaseAdapter> listeningAdapters;

	protected PostModelList() {
		this.postModelArrayList = new ArrayList<T>();
		this.listeningAdapters = new ArrayList<BaseAdapter>();
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

	public void setArrayList(ArrayList<T> postModelArrayList) {
		this.postModelArrayList = postModelArrayList;

		updateListeningAdapters();
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

		updateListeningAdapters();
	}

	/*
	 * Sorts theTopicModelArrayList by date
	 */
	public void sortByDate() {
		Collections.sort(this.postModelArrayList, PostModel.COMPARE_BY_DATE);

		updateListeningAdapters();
	}

	/*
	 * Adds a model to the top of the list singleton
	 */
	public void add(T theModel) {
		this.postModelArrayList.add(0, theModel);

		updateListeningAdapters();
	}

	public void remove(int position) {
		this.postModelArrayList.remove(position);

		updateListeningAdapters();
	}

	public void update(int position, T theModel) {
		this.postModelArrayList.set(position, theModel);

		updateListeningAdapters();
	}

	public void remove(T theModel) {
		this.postModelArrayList.remove(theModel);

		updateListeningAdapters();
	}

	public Boolean contains(T theModelToSearchFor) {
		return this.getArrayList().contains(theModelToSearchFor);
	}

	public void updateListeningAdapters() {
		Log.w("PostModelList", "The number of listening adapters is: " + Integer.toString(this.listeningAdapters.size()));
		for (BaseAdapter theAdapter : this.listeningAdapters) {
			if (theAdapter != null) {
				theAdapter.notifyDataSetChanged();
			}
		}
	}

	public void registerListeningAdapter(BaseAdapter theAdapter) {
		this.listeningAdapters.add(theAdapter);
	}

	public void unRegisterListeningAdapter(BaseAdapter theAdapter) {
		this.listeningAdapters.remove(theAdapter);
	}
}
