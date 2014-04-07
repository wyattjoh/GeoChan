/**
 * 
 */
package ca.ualberta.cs.models;

import java.util.Iterator;

import ca.ualberta.cs.providers.ElasticSearchProviderServiceHandler;

/**
 * Contains information relevant to making a request against the ElasticSearch
 * service
 * 
 * @author wyatt
 */
public class ElasticSearchOperationRequest extends ElasticSearchOperation {
	/**
	 * Builds an ElasticSearchRequest object from a request type.
	 * 
	 * @param theRequestType
	 */
	public ElasticSearchOperationRequest(
			ElasticSearchProviderServiceHandler theRequestType) {
		super(theRequestType);
		// TODO Auto-generated constructor stub
	}
	
	public String getQueryString() {
		PostModelList<TopicModel> theList = getPostModelList();
		return theList.getTheCurrentSort().getElasticSearchQueryString(this);
	}
	
	public String generateMultiGetQueryStringForComments() {
		String theQuery = "{\"docs\": [";
		
		Iterator<UpdatePackage<CommentModel>> iterator = getTheCommentIdsToGet().iterator();
		
		while (iterator.hasNext()) {
			UpdatePackage<?> theUpdatePackage = iterator.next();
			theQuery += "{\"_id\": \"";
			theQuery += theUpdatePackage.getParentId();
			theQuery += "\"}";
			
			if (iterator.hasNext() == true) {
				theQuery += ",";
			}
		}

		theQuery += "]}";
		
		return theQuery;
	}
	
	public String generateMultiGetQueryStringForTopics() {
		String theQuery = "{\"docs\": [";
		
		Iterator<UpdatePackage<TopicModel>> iterator = getTheTopicIdsToGet().iterator();
		
		while (iterator.hasNext()) {
			UpdatePackage<?> theUpdatePackage = iterator.next();
			theQuery += "{\"_id\": \"";
			theQuery += theUpdatePackage.getParentId();
			theQuery += "\"}";
			
			if (iterator.hasNext() == true) {
				theQuery += ",";
			}
		}

		theQuery += "]}";
		
		return theQuery;
	}
}