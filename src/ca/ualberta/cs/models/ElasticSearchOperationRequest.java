/**
 * 
 */
package ca.ualberta.cs.models;

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

	public String generateSearchQueryString() {
		return "{\"from\": "
				+ Integer.toString(this.from)
				+ ", \"size\": "
				+ Integer.toString(size)
				+ ", \"query\": { \"match_all\": {} }, \"version\" : true, \"sort\": [{\"datePosted\": {\"order\": \"desc\"}}]}";
	}
}