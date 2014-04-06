package ca.ualberta.cs.controllers;


import ca.ualberta.cs.models.TopicModelList;

/**
 * @see ca.ualberta.cs.controllers.PostListController#SORT_SCORE
 */
public class SortScore extends TheSortOrder {
	public void setSort() {
		TopicModelList.getInstance().sortByScore();
	}
}