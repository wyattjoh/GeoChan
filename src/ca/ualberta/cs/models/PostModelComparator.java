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
public enum PostModelComparator implements Comparator<PostModel> {

	COMPARE_BY_DATE {

		@Override
		public int compare(PostModel lhs, PostModel rhs) {
			// TODO Auto-generated method stub
			return lhs.getDatePosted().compareTo(rhs.getDatePosted());
		}

		@Override
		public String getElasticSearchQueryString(
				ElasticSearchOperationRequest theRequest) {
			return "{\"from\": "
					+ Integer.toString(theRequest.getFrom())
					+ ", \"size\": "
					+ Integer.toString(theRequest.getSize())
					+ ", \"query\": { \"match_all\": {} }, \"version\" : true, \"sort\": [{\"datePosted\": {\"order\": \"desc\"}}]}";
		}

	},
	COMPARE_BY_SCORE {

		@Override
		public int compare(PostModel lhs, PostModel rhs) {
			// TODO Auto-generated method stub
			return lhs.getScore().compareTo(rhs.getScore());
		}

		@Override
		public String getElasticSearchQueryString(
				ElasticSearchOperationRequest theRequest) {
			return "{\"from\": "
					+ Integer.toString(theRequest.getFrom())
					+ ", \"size\": "
					+ Integer.toString(theRequest.getSize())
					+ ", \"query\": { \"match_all\": {} }, \"version\" : true, \"sort\": [{\"score\": {\"order\": \"desc\"}}]}";
		}

	},
	COMPARE_BY_PROXIMITY {

		@Override
		public int compare(PostModel lhs, PostModel rhs) {
			Location myLocation = getSortingLocation();
			float distanceToOneLocation = myLocation.distanceTo(lhs
					.getLocation());
			float distanceToOtherLocation = myLocation.distanceTo(rhs
					.getLocation());
			return distanceToOneLocation < distanceToOtherLocation ? -1
					: distanceToOneLocation > distanceToOtherLocation ? 1 : 0;
		}

		@Override
		public String getElasticSearchQueryString(
				ElasticSearchOperationRequest theRequest) {
			Location theLocation = getSortingLocation();
			return "{ \"from\": "
					+ Integer.toString(theRequest.getFrom())
					+ ", \"size\": "
					+ Integer.toString(theRequest.getSize())
					+ ", \"query\": { \"match_all\": {} }, \"filter\": { \"geo_distance\": { \"distance\": \"20km\", \"location\": { \"lat\": "
					+ Double.toString(theLocation.getLatitude())
					+ ", \"lon\": "
					+ Double.toString(theLocation.getLongitude()) + " } } } }";
		}

	},
	COMPARE_BY_LATEST_GREATEST {

		@Override
		public int compare(PostModel lhs, PostModel rhs) {
			Date currentTime = new Date();
			float relativeScoreOne = lhs.getScore()
					- ((currentTime.getTime() - lhs.getDatePosted().getTime()) / 10000);
			float relativeScoreOther = rhs.getScore()
					- ((currentTime.getTime() - rhs.getDatePosted().getTime()) / 10000);
			return relativeScoreOne < relativeScoreOther ? -1
					: relativeScoreOne > relativeScoreOther ? 1 : 0;
		}

		@Override
		public String getElasticSearchQueryString(
				ElasticSearchOperationRequest theRequest) {
			Date now = new Date();
			return "{\"from\": "
					+ Integer.toString(theRequest.getFrom())
					+ ", \"size\": "
					+ Integer.toString(theRequest.getSize())
					+ ", \"query\": { \"custom_filters_score\": { \"query\": { \"match_all\": {} }, \"params\": { \"now\": "
					+ Long.toString(now.getTime())
					+ " }, \"filters\": [ { \"filter\": { \"exists\": { \"field\": \"score\" } }, \"script\": \"doc['score'].value - (now - doc['datePosted'].value)/10000\" } ] } } }";
		}

	};

	private static Location sortingLocation = null;

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

	/**
	 * Generates the query string for this paticular sorting method
	 * 
	 * @return
	 */
	abstract public String getElasticSearchQueryString(
			ElasticSearchOperationRequest theRequest);
}
