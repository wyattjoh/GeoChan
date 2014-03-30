package ca.ualberta.cs.models;

import ca.ualberta.cs.providers.ElasticSearchProviderServiceHandler;

public class ElasticSearchOperationResponse extends ElasticSearchOperation {
	public ElasticSearchOperationResponse(
			ElasticSearchProviderServiceHandler elasticSearchProviderServiceType) {
		super(elasticSearchProviderServiceType);
	}
}
