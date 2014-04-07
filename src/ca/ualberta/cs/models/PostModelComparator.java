/**
 * 
 */
package ca.ualberta.cs.models;

import java.util.Comparator;
import java.util.Date;

import android.location.Location;

/**
 * @author wyatt
 * 
 */
public class PostModelComparator {

	private static Location sortingLocation = null;

	public static Comparator<PostModel> COMPARE_BY_DATE = new Comparator<PostModel>() {
		@Override
		public int compare(PostModel one, PostModel other) {
			return one.getDatePosted().compareTo(other.getDatePosted());
		}
	};
	public static Comparator<PostModel> COMPARE_BY_SCORE = new Comparator<PostModel>() {
		@Override
		public int compare(PostModel one, PostModel other) {
			return one.getScore().compareTo(other.getScore());
		}
	};
	public static Comparator<PostModel> COMPARE_BY_PROXIMITY = new Comparator<PostModel>() {
		@Override
		public int compare(PostModel one, PostModel other) {
			Location myLocation = getSortingLocation();
			float distanceToOneLocation = myLocation.distanceTo(one
					.getLocation());
			float distanceToOtherLocation = myLocation.distanceTo(other
					.getLocation());
			return distanceToOneLocation < distanceToOtherLocation ? -1
					: distanceToOneLocation > distanceToOtherLocation ? 1 : 0;
		}
	};
	public static Comparator<PostModel> COMPARE_BY_LATEST_GREATEST = new Comparator<PostModel>() {
		@Override
		public int compare(PostModel one, PostModel other) {
			Date currentTime = new Date();
			float relativeScoreOne = one.getScore()
					- ((currentTime.getTime() - one.getDatePosted().getTime()) / 10000);
			float relativeScoreOther = other.getScore()
					- ((currentTime.getTime() - other.getDatePosted().getTime()) / 10000);
			return relativeScoreOne < relativeScoreOther ? -1
					: relativeScoreOne > relativeScoreOther ? 1 : 0;
		}
	};

	/**
	 * @return the sortingLocation
	 */
	public static Location getSortingLocation() {
		return sortingLocation;
	}

	/**
	 * @param sortingLocation
	 *            the sortingLocation to set
	 */
	public static void setSortingLocation(Location sortingLocation) {
		PostModelComparator.sortingLocation = sortingLocation;
	}

}
