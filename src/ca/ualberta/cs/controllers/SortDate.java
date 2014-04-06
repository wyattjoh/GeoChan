package ca.ualberta.cs.controllers;


import ca.ualberta.cs.models.TopicModelList;

/**
 * @see ca.ualberta.cs.controllers.PostListController#SORT_DATE
 */
public class SortDate extends TheSortOrder {
	public void setSort() {
		TopicModelList.getInstance().sortByDate();
	}
}