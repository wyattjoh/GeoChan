package ca.ualberta.cs.models;

public class ElasticSearchOperationFactory {
	public static ElasticSearchOperationResponse responseFromRequest(ElasticSearchOperationRequest theRequest) {
		ElasticSearchOperationResponse response = new ElasticSearchOperationResponse(theRequest.getRequestType());
		response.setTopicModel(theRequest.getTopicModel());
		response.setPostModelList(theRequest.getPostModelList());
		
		return response;
	}
}
