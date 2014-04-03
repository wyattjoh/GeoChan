package ca.ualberta.cs.models;

import java.util.UUID;

public class CommentModel extends PostModel {

	/**
	 * Constructors
	 * */
	public CommentModel() {
		super();

		// Sets the ID
		setId(UUID.randomUUID().toString());
	}

	public CommentModel(UserModel theUser) {
		super(theUser);
	}
}
