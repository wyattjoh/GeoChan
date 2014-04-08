package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.TopicModelList;

/**
 * control the post arrays
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
		switch (theSortOrder) {
		case SORT_DATE:
			TopicModelList.getInstance().sortByDate();
			break;
		case SORT_SCORE:
			TopicModelList.getInstance().sortByScore();
			break;
		case SORT_PROXIMITY:
			TopicModelList.getInstance().sortByProximity();
			break;
		case SORT_LATEST_GREATEST:
			TopicModelList.getInstance().sortByLatestGreatest();
			break;
		case SORT_PICTURE:
			TopicModelList.getInstance().sortByPicture();
			break;
		default:
			break;
		}
	}
}
