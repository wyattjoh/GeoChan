package ca.ualberta.cs.models;

import java.util.ArrayList;

public class ElasticSearchOperationFactory {
	public static ElasticSearchOperationResponse responseFromRequest(
			ElasticSearchOperationRequest theRequest,
			ElasticSearchResponse<?> serverResponse) {
		ElasticSearchOperationResponse response = new ElasticSearchOperationResponse(
				theRequest.getRequestType());
		TopicModel theTopicModel = theRequest.getTopicModel();

		// Update the ID
		String theId = serverResponse.getId();
		theTopicModel.setId(theId);

		// Update the version
		int theVersion = serverResponse.getVersion();
		theTopicModel.setVersion(theVersion);

		response.setTopicModel(theTopicModel);
		response.setPostModelList(theRequest.getPostModelList());

		return response;
	}

	public static ElasticSearchOperationResponse responseFromRequest(
			ElasticSearchOperationRequest theRequest,
			ElasticSearchSearchResponse<TopicModel> esResponse) {
		ArrayList<TopicModel> theTopicModelArrayList = new ArrayList<TopicModel>();
		
		for (ElasticSearchResponse<TopicModel> r: esResponse.getHits()) {
			TopicModel theTopic = r.getSource();
			theTopicModelArrayList.add(theTopic);
		}
		
		ElasticSearchOperationResponse response = new ElasticSearchOperationResponse(
				theRequest.getRequestType());
		response.setTheTopicModels(theTopicModelArrayList);
		response.setPostModelList(theRequest.getPostModelList());
		
		return response;
	}
}
