package ca.ualberta.cs.controllers;

import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.VoteStatusList;

public class PostViewController {

VoteStatusList voteStatusList;
	
	public PostViewController() {
		voteStatusList = VoteStatusList.getInstance();
	}
	
	public void toggleVote(PostModel theModel) {
		//if (theModel.isFavorite()) {
		//	theModel.setIsFavorite(false);
		//	voteStatusList.remove(theModel);
		//}
		//else {
		//	theModel.setIsFavorite(true);
		//	voteStatusList.add(theModel);
		//}
	}
}
