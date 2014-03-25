package ca.ualberta.cs.models;

public class ElasticSearchOperationTransformer {
	public static ElasticSearchOperationResponse responseFromRequest(ElasticSearchOperationRequest theRequest) {
		ElasticSearchOperationResponse response = new ElasticSearchOperationResponse(theRequest.getRequestMode());
		response.setTopicModel(theRequest.getTopicModel());
		response.setPostModelList(theRequest.getPostModelList());
		
		return response;
	}
}
