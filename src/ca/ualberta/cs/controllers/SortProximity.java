package ca.ualberta.cs.controllers;


import ca.ualberta.cs.models.TopicModelList;

/**
 * @see ca.ualberta.cs.controllers.PostListController#SORT_PROXIMITY
 */
public class SortProximity extends TheSortOrder {
	public void setSort() {
		TopicModelList.getInstance().sortByProximity();
	}
}