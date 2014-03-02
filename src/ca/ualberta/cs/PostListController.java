package ca.ualberta.cs;

import java.util.ArrayList;

public class PostListController {

	/* sort values */
	public static final int SORT_PROXIMITY = 0;
	public static final int SORT_PICTURE = 1;
	public static final int SORT_DATE	 = 2;
	public static final int SORT_SCORE = 3;
	public static final int SORT_DEFAULT = 4;
	
	public static ArrayList<PostModel> getList(){	
		return null;
	}

	public static ArrayList<TopicModel> createTopicList(){
		// init array list
		ArrayList<TopicModel> theModelList = new ArrayList<TopicModel>();
		// init topic model
		TopicModel theTopic = new TopicModel();
		
		// populate topic with test entries
		theTopic.setBodyText("Test1");
		theModelList.add(theTopic);
		theTopic.setBodyText("Test2");
		theModelList.add(theTopic);
		theTopic.setBodyText("Test3");
		theModelList.add(theTopic);
		
		return theModelList;
	}
}
