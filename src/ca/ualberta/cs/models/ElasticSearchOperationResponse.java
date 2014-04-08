package ca.ualberta.cs.models;

import ca.ualberta.cs.providers.ElasticSearchProviderServiceHandler;

/**
 * Stores the response object from the elastic search operation
 * @author wyatt
 *
 */
public class ElasticSearchOperationResponse extends ElasticSearchOperation {
	public ElasticSearchOperationResponse(
			ElasticSearchProviderServiceHandler elasticSearchProviderServiceType) {
		super(elasticSearchProviderServiceType);
	}
}
