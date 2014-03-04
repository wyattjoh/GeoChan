package ca.ualberta.cs;

import java.util.ArrayList;

public class PostListController {

	/* sort values */
	public static final int SORT_PROXIMITY = 0;
	public static final int SORT_PICTURE = 1;
	public static final int SORT_DATE	 = 2;
	public static final int SORT_SCORE = 3;
	public static final int SORT_DEFAULT = 4;
	
	public static ArrayList<Object> getList(){	
		return null;
	}

	public static ArrayList<TopicModel> createTopicList(){
		// init array list
		ArrayList<TopicModel> theModelList = new ArrayList<TopicModel>();
		// init topic model
		
		
		// populate topic with test entries
		TopicModel theTopic1 = new TopicModel();
		theTopic1.setTitle("TestTitle1");
		theTopic1.setBodyText("TestText1");
		theTopic1.setScore(1);
		theModelList.add(theTopic1);
		
		TopicModel theTopic2 = new TopicModel();
		theTopic2.setTitle("TestTitle2");
		theTopic2.setBodyText("TestText2");
		theTopic2.setScore(2);
		theModelList.add(theTopic2);
		
		TopicModel theTopic3 = new TopicModel();
		theTopic3.setTitle("TestTitle3");
		theTopic3.setBodyText("TestText3");
		theTopic3.setScore(3);
		theModelList.add(theTopic3);
		
		return theModelList;
	}
}
