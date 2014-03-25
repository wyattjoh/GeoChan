/**
 * 
 */
package ca.ualberta.cs.models;

import ca.ualberta.cs.providers.ElasticSearchProviderConstants;

/**
 * Contains information relevant to making a request against the ElasticSearch service
 * 
 * @author wyatt
 */
public class ElasticSearchOperationRequest extends ElasticSearchOperation {
	/**
	 * Builds an ElasticSearchRequest object from a request type.
	 * 
	 * @param theRequestType
	 */
	public ElasticSearchOperationRequest(ElasticSearchProviderConstants theRequestType) {
		super(theRequestType);
		// TODO Auto-generated constructor stub
	}
}