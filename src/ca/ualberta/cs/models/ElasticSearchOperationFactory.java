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

		for (ElasticSearchResponse<TopicModel> r : esResponse.getHits()) {
			TopicModel theTopic = r.getSource();
			if (theTopic.getId() == null || theTopic.getId() == "null") {
				theTopic.setId(r.getId());
			}
			theTopicModelArrayList.add(theTopic);
		}

		ElasticSearchOperationResponse response = new ElasticSearchOperationResponse(
				theRequest.getRequestType());
		response.setTheTopicModels(theTopicModelArrayList);
		response.setPostModelList(theRequest.getPostModelList());

		return response;
	}

	public static ElasticSearchOperationResponse responseFromCommentsRequest(
			ElasticSearchOperationRequest theRequest,
			ElasticSearchMgetResponse<TopicModel> esResponse) {
		ArrayList<UpdatePackage<CommentModel>> initialRequest = theRequest
				.getTheCommentIdsToGet();
		ArrayList<ElasticSearchMgetDoc<TopicModel>> theDocs = new ArrayList<ElasticSearchMgetDoc<TopicModel>>();

		int length = initialRequest.size();

		for (ElasticSearchMgetDoc<TopicModel> theDoc : esResponse.getDocs()) {
			theDocs.add(theDoc);
		}

		for (int i = 0; i < length; i++) {
			TopicModel theModel = theDocs.get(i).getSource();
			UpdatePackage<CommentModel> thePackage = initialRequest.get(i);

			if (theModel != null) {
				CommentModel theComment = theModel
						.fetchCommentWithId(thePackage.getMyId());
				thePackage.setTheUpdatedModel(theComment);
			} else {
				thePackage.setTheUpdatedModel(null);
			}
		}

		ElasticSearchOperationResponse response = new ElasticSearchOperationResponse(
				theRequest.getRequestType());
		response.setTheFollowingCommentsList(theRequest.getTheFollowingCommentsList());
		response.setTheCommentIdsToGet(initialRequest);

		return response;
	}
	
	public static ElasticSearchOperationResponse responseFromTopicsRequest(
			ElasticSearchOperationRequest theRequest,
			ElasticSearchMgetResponse<TopicModel> esResponse) {
		ArrayList<UpdatePackage<TopicModel>> initialRequest = theRequest
				.getTheTopicIdsToGet();
		ArrayList<ElasticSearchMgetDoc<TopicModel>> theDocs = new ArrayList<ElasticSearchMgetDoc<TopicModel>>();

		int length = initialRequest.size();

		for (ElasticSearchMgetDoc<TopicModel> theDoc : esResponse.getDocs()) {
			theDocs.add(theDoc);
		}

		for (int i = 0; i < length; i++) {
			TopicModel theModel = theDocs.get(i).getSource();
			UpdatePackage<TopicModel> thePackage = initialRequest.get(i);

			if (theModel != null) {
				thePackage.setTheUpdatedModel(theModel);
			} else {
				thePackage.setTheUpdatedModel(null);
			}
		}

		ElasticSearchOperationResponse response = new ElasticSearchOperationResponse(
				theRequest.getRequestType());
		response.setTheFollowingTopicsList(theRequest.getTheFollowingTopicsList());
		response.setTheTopicIdsToGet(initialRequest);

		return response;
	}
}
