package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.TopicModelList;

/**
 * control the post arrays (?!)
 * 
 * @author vincent
 * 
 */
public class PostListController {

	/* sort values */
	public static final int SORT_PROXIMITY = 0;
	public static final int SORT_PICTURE = 1;
	public static final int SORT_DATE = 2;
	public static final int SORT_SCORE = 3;
	public static final int SORT_DEFAULT = 2;
	public static final int SORT_LATEST_GREATEST = 4;

	public static void setSort(final int theSortOrder) {
		getTheSortOrderObject(theSortOrder).setSort();
	}

	private static TheSortOrder getTheSortOrderObject(int theSortOrder) {
		switch (theSortOrder) {
		case SORT_DATE:
			return new SortDate();
		case SORT_SCORE:
			return new SortScore();
		case SORT_PROXIMITY:
			return new SortProximity();
		case SORT_LATEST_GREATEST:
			return new SortLatestGreatest();
		case SORT_PICTURE:
			return new SortPicture();
		}
		return null;
	}
}
