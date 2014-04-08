package ca.ualberta.cs.test;

import java.util.Date;

import android.app.Activity;
import android.location.Location;
import android.test.ActivityInstrumentationTestCase2;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;

public class SortTesting extends ActivityInstrumentationTestCase2<Activity> {
	private TopicModelList testTopicModelList = TopicModelList.getInstance();
	
	public SortTesting(){
		super(Activity.class);
	}

	/**
	 * TestCase 1.2
	 * Test whether the posts are listed by proximity to user
	 */
	public void testProximitySort() {
		if (testTopicModelList.getArrayList().size() > 1) {
			Location userLocation = null;
			testTopicModelList.sortByProximity();
			if (ActiveUserModel.getInstance().getUser().getLocation() != null) {
				userLocation = ActiveUserModel.getInstance().getUser().getLocation();
			} else {
				userLocation = new Location("");
				userLocation.setLatitude(0);
				userLocation.setLongitude(0);
			}
			boolean correctOrder = true;
			for (int i = 0; i < testTopicModelList.getArrayList().size() - 1; i++) {
				Location closerLocation = testTopicModelList.getArrayList().get(i).getLocation();
				float distanceToCloserLocation = userLocation.distanceTo(closerLocation);
				for (int j = 1; j < testTopicModelList.getArrayList().size(); j++) {
					Location fartherLocation = testTopicModelList.getArrayList().get(j).getLocation();
					float distanceToFartherLocation = userLocation.distanceTo(fartherLocation);
					if (distanceToCloserLocation > distanceToFartherLocation) {
						correctOrder = false;
					}
				}
			}
			assertTrue("Proximity sort is not returning the correct order", correctOrder);
		}
	}
	
	/**
	 * TestCase 2.2
	 * Test whether the posts are listed by proximity to another location
	 */
	public void testProximityToSort() {
		if (testTopicModelList.getArrayList().size() > 1) {
			Location sortLocation = new Location("");
			sortLocation.setLatitude(0);
			sortLocation.setLongitude(0);
			testTopicModelList.sortByProximityTo(sortLocation);
			boolean correctOrder = true;
			for (int i = 0; i < testTopicModelList.getArrayList().size() - 1; i++) {
				Location closerLocation = testTopicModelList.getArrayList().get(i).getLocation();
				float distanceToCloserLocation = sortLocation.distanceTo(closerLocation);
				for (int j = 1; j < testTopicModelList.getArrayList().size(); j++) {
					Location fartherLocation = testTopicModelList.getArrayList().get(j).getLocation();
					float distanceToFartherLocation = sortLocation.distanceTo(fartherLocation);
					if (distanceToCloserLocation > distanceToFartherLocation) {
						correctOrder = false;
					}
				}
			}
			assertTrue("Proximity To... sort is not returning the correct order", correctOrder);
		}
	}
		
	/**
	 * TestCase 8.2
	 * Test whether the posts are listed by presence of a picture
	 */
	public void testPictureSort() {
		if (testTopicModelList.getArrayList().size() > 1) {
			testTopicModelList.sortByPicture();
			boolean correctOrder = true;
			boolean hasPicture = true;
			for (int i = 0; i < testTopicModelList.getArrayList().size(); i++) {
				TopicModel testTopic = testTopicModelList.getArrayList().get(i);
				if (!hasPicture && testTopic.hasPicture()) {
					correctOrder = false;
				}
				if (!testTopic.hasPicture()) {
					hasPicture = false;
				}
			}
			assertTrue("Picture sort is not returning the correct order", correctOrder);
		}
	}
	
	/**
	 * TestCase 9.2
	 * Test whether the posts are listed by most recent date posted
	 */
	public void testDateSort() {
		if (testTopicModelList.getArrayList().size() > 1) {
			testTopicModelList.sortByDate();
			boolean correctOrder = true;
			for (int i = 0; i < testTopicModelList.getArrayList().size() - 1; i++) {
				Date recentDate = testTopicModelList.getArrayList().get(i).getDatePosted();
				for (int j = 1; j < testTopicModelList.getArrayList().size(); j++) {
					Date lessRecentDate = testTopicModelList.getArrayList().get(j).getDatePosted();
					if (recentDate.before(lessRecentDate)) {
						correctOrder = false;
					}
				}
			}
			assertTrue("Date sort is not returning the correct order", correctOrder);
		}
	}
	
	/**
	 * Test sort by score
	 * Test whether the posts are listed by score
	 */
	public void testSortScore() {
		if (testTopicModelList.getArrayList().size() > 1) {
			testTopicModelList.sortByScore();
			boolean correctOrder = true;
			for (int i = 0; i < testTopicModelList.getArrayList().size() - 1; i++) {
				int higherScore = testTopicModelList.getArrayList().get(i).getScore();
				for (int j = 1; j < testTopicModelList.getArrayList().size(); j++) {
					int lowerScore = testTopicModelList.getArrayList().get(j).getScore();
					if (lowerScore > higherScore) {
						correctOrder = false;
					}
				}
			}
			assertTrue("Score sort is not returning the correct order", correctOrder);
		}
	}
}