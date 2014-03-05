package ca.ualberta.cs.models;

public class CommentModel extends PostModel {

	/**
	 *  Constructors
	 *  */
	public CommentModel(){
		super();
	}
	
	public CommentModel(UserModel theUser){
		super(theUser);
	}
}
