package ca.ualberta.cs.controllers;


import ca.ualberta.cs.models.TopicModelList;

/**
 * @see ca.ualberta.cs.controllers.PostListController#SORT_PICTURE
 */
public class SortPicture extends TheSortOrder {
	public void setSort() {
		TopicModelList.getInstance().sortByPicture();
	}
}