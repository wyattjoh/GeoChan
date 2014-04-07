package ca.ualberta.cs.models;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;

import android.location.Location;
import android.util.Log;
import ca.ualberta.cs.adapters.PostListViewAdapter;

public class PostModelList<T extends PostModel> {
	private ArrayList<T> postModelArrayList;
	private ArrayList<PostListViewAdapter<?>> listeningAdapters;
	private PostModelComparator theCurrentSort = PostModelComparator.COMPARE_BY_LATEST_GREATEST;

	private Deque<T> selectedPostModelStack = new ArrayDeque<T>();

	protected PostModelList() {
		this.postModelArrayList = new ArrayList<T>();
		this.listeningAdapters = new ArrayList<PostListViewAdapter<?>>();
	}

	/**
	 * Add the element in the list at position indicated to the selection stack
	 * 
	 * @param position
	 *            is the position of the selected model
	 */
	public void addToSelectionStackFromPosition(int position) {
		this.selectedPostModelStack.add(postModelArrayList.get(position));
	}

	/**
	 * Add the element to the selection stack
	 * 
	 * @param selectedPostModel
	 */
	public void addToSelectionStack(T selectedPostModel) {
		this.selectedPostModelStack.add(selectedPostModel);
	}

	/**
	 * Remove the element at the end of the list from the list
	 * 
	 * @return the item removed from the selection stack
	 */
	public T popFromSelectionStack() {
		return this.selectedPostModelStack.pop();
	}

	/**
	 * @return the last element in the stack (last selected)
	 */
	public T getLastSelection() {
		return this.selectedPostModelStack.peekLast();
	}

	public void setArrayList(ArrayList<T> postModelArrayList) {
		this.postModelArrayList.clear();
		this.addToArrayList(postModelArrayList);
	}

	public void addToArrayList(ArrayList<T> postModelArrayList) {
		this.postModelArrayList.addAll(postModelArrayList);

		updateListeningAdapters();
	}

	public PostModel getSelectionOffsetFromEnd(Integer theIndex) {
		Object[] temp = this.selectedPostModelStack.toArray();
		if (temp.length == 1) {
			return ((PostModel) temp[temp.length - theIndex]);
		}
		return ((PostModel) temp[temp.length - (theIndex + 1)]);
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
		Boolean reverse = true;
		setTheCurrentSort(PostModelComparator.COMPARE_BY_SCORE);
		
		executeSort(reverse, true);
	}

	/*
	 * Sorts theTopicModelArrayList by date
	 */
	public void sortByDate() {
		Boolean reverse = true;
		setTheCurrentSort(PostModelComparator.COMPARE_BY_DATE);
		
		executeSort(reverse, true);
	}

	/*
	 * Sorts theTopicModelArrayList by proximity to user
	 */
	public void sortByProximity() {
		Boolean reverse = true;
		setTheCurrentSort(PostModelComparator.COMPARE_BY_PROXIMITY);
		
		Location sortingLocation = ActiveUserModel.getInstance().getUser().getLocation();
		PostModelComparator.setSortingLocation(sortingLocation);

		executeSort(reverse, true);
	}

	/*
	 * Sorts theTopicModelArrayList by "latest greatest"
	 */
	public void sortByLatestGreatest() {
		Boolean reverse = true;
		setTheCurrentSort(PostModelComparator.COMPARE_BY_LATEST_GREATEST);

		executeSort(reverse, true);
	}

	/*
	 * Sorts theTopicModelArrayList by picture
	 */
	public void sortByPicture() {
		Boolean reverse = true;
		setTheCurrentSort(PostModelComparator.COMPARE_BY_DATE);
		
		executeSort(reverse, false);

		ArrayList<T> tempList = new ArrayList<T>();
		tempList.addAll(this.postModelArrayList);

		this.postModelArrayList.clear();
		for (T theModel : tempList) {
			if (theModel.hasPicture()) {
				this.postModelArrayList.add(theModel);
			}
		}
		for (T theModel : tempList) {
			if (!theModel.hasPicture()) {
				this.postModelArrayList.add(theModel);
			}
		}

		updateListeningAdapters();
	}

	/**
	 * Executes a sort
	 * @param reverse
	 * @param updateAdapter
	 */
	protected void executeSort(Boolean reverse, Boolean updateAdapter) {
		Collections.sort(this.postModelArrayList, getTheCurrentSort());
		if (reverse) {
			Collections.reverse(this.postModelArrayList);
		}
	
		if (updateAdapter) {
			updateListeningAdapters();
		}
	}

	/*
	 * Sorts theTopicModelArrayList by distance to a specified location
	 */
	public void sortByProximityTo(Location location) {
		Boolean reverse = true;
		setTheCurrentSort(PostModelComparator.COMPARE_BY_PROXIMITY);
		
		PostModelComparator.setSortingLocation(location);

		executeSort(reverse, true);
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
			this.postModelArrayList.clear();
		}

		updateListeningAdapters();
	}

	public void remove(T theModel) {
		String removingId = theModel.getId();

		Iterator<T> iter = this.postModelArrayList.iterator();
		while (iter.hasNext()) {
			if (iter.next().getId().equals(removingId)) {
				iter.remove();
				updateListeningAdapters();
				return;
			}
		}
	}

	public Boolean contains(T theModelToSearchFor) {
		return this.getArrayList().contains(theModelToSearchFor);
	}

	public void updateListeningAdapters() {
		for (PostListViewAdapter<?> theAdapter : this.listeningAdapters) {
			if (theAdapter != null) {
				theAdapter.notifyDataSetChanged();
				Log.w("PostModelList", "The adapter has been notified");
			}
		}
	}

	public void registerListeningAdapter(PostListViewAdapter<?> theAdapter) {
		this.listeningAdapters.add(theAdapter);
		Log.w("PostModelList", "Listener added");
	}

	public void unRegisterListeningAdapter(PostListViewAdapter<?> theAdapter) {
		this.listeningAdapters.remove(theAdapter);
		Log.w("PostModelList", "Listener removed");
	}

	/**
	 * @return the theCurrentSort
	 */
	public PostModelComparator getTheCurrentSort() {
		return theCurrentSort;
	}

	/**
	 * @param theCurrentSort the theCurrentSort to set
	 */
	public void setTheCurrentSort(PostModelComparator theCurrentSort) {
		this.theCurrentSort = theCurrentSort;
	}
}
