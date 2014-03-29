package ca.ualberta.cs.models;

import java.util.ArrayList;
import java.util.Collections;

import ca.ualberta.cs.adapters.PostListViewAdapter;
import android.util.Log;

public class PostModelList<T extends PostModel> {
	private T selectedPostModel = null;
	private ArrayList<T> postModelArrayList;
	private ArrayList<PostListViewAdapter<T>> listeningAdapters;

	protected PostModelList() {
		this.postModelArrayList = new ArrayList<T>();
		this.listeningAdapters = new ArrayList<PostListViewAdapter<T>>();
	}

	public void setSelection(int position) {
		this.selectedPostModel = postModelArrayList.get(position);
	}

	public void setSelection(T selectedPostModel) {
		this.selectedPostModel = selectedPostModel;
	}
	
	public void resetSelection(){
		this.selectedPostModel = null;
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
	 * Sorts theTopicModelArrayList by proximity to user
	 */
	public void sortByProximity() {
		Collections.sort(this.postModelArrayList, PostModel.COMPARE_BY_PROXIMITY);
		
		updateListeningAdapters();
	}
	
	/*
	 * Sorts theTopicModelArrayList by "latest greatest"
	 */
	public void sortByLatestGreatest() {
		Collections.sort(this.postModelArrayList, PostModel.COMPARE_BY_LATEST_GREATEST);
		
		updateListeningAdapters();
	}
	
	/*
	 * Sorts theTopicModelArrayList by picture
	 */
	public void sortByPicture() {
		Collections.sort(this.postModelArrayList, PostModel.COMPARE_BY_DATE);
		ArrayList<T> tempList = new ArrayList<T>();
		tempList = (ArrayList<T>) this.postModelArrayList.clone();
		this.postModelArrayList.clear();
		for(T theModel : tempList) {
			if(theModel.hasPicture()) {
				this.postModelArrayList.add(theModel);
			}
		}
		for(T theModel : tempList) {
			if(!theModel.hasPicture()) {
				this.postModelArrayList.add(theModel);
			}
		}
		
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
	
	public void update(T theModel) {
		boolean foundObjectToUpdate = false;
		int size = this.postModelArrayList.size();
		
		for (int i = 0; i < size; i++) {
			T model = this.postModelArrayList.get(i);
			
			if (model != null && model.equals(theModel)) {
				this.postModelArrayList.set(i, theModel);
				foundObjectToUpdate = true;
				break;
			}
		}
		
		if (!foundObjectToUpdate) {
			throw new RuntimeException("Tried to update an entry that wasn't here!");
		}
	}

	public void remove(T theModel) {
		this.postModelArrayList.remove(theModel);

		updateListeningAdapters();
	}

	public Boolean contains(T theModelToSearchFor) {
		return this.getArrayList().contains(theModelToSearchFor);
	}

	public void updateListeningAdapters() {
		for (PostListViewAdapter<T> theAdapter : this.listeningAdapters) {
			if (theAdapter != null) {
				theAdapter.notifyDataSetChanged();
				Log.w("PostModelList", "The adapter has been notified");
			}
		}
	}

	public void registerListeningAdapter(PostListViewAdapter<T> theAdapter) {
		this.listeningAdapters.add(theAdapter);
	}

	public void unRegisterListeningAdapter(PostListViewAdapter<T> theAdapter) {
		this.listeningAdapters.remove(theAdapter);
	}
}
