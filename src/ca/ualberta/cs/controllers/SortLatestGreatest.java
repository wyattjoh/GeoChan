package ca.ualberta.cs.controllers;


import ca.ualberta.cs.models.TopicModelList;

/**
 * @see ca.ualberta.cs.controllers.PostListController#SORT_LATEST_GREATEST
 */
public class SortLatestGreatest extends TheSortOrder {
	public void setSort() {
		TopicModelList.getInstance().sortByLatestGreatest();
	}
}