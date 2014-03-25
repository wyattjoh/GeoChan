/**
 * 
 */
package ca.ualberta.cs.models;

import ca.ualberta.cs.providers.ElasticSearchProviderConstants;

/**
 * @author wyatt
 *
 */
public class ElasticSearchRequest {

	private ElasticSearchProviderConstants requestMode;
	
	public ElasticSearchRequest(ElasticSearchProviderConstants theRequestType) {
		this.requestMode = theRequestType;
	}
	
	public ElasticSearchProviderConstants getRequestMode() {
		return requestMode;
	}
	
	public void addModel(PostModel theModel) {
		
	}
	
}