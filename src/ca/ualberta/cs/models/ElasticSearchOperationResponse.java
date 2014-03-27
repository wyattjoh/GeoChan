package ca.ualberta.cs.models;

import ca.ualberta.cs.providers.ElasticSearchProviderServiceType;

public class ElasticSearchOperationResponse extends ElasticSearchOperation {
	public ElasticSearchOperationResponse(
			ElasticSearchProviderServiceType elasticSearchProviderServiceType) {
		super(elasticSearchProviderServiceType);
	}
}
