package ca.ualberta.cs.models;

public class TopicModel extends PostModel {
	private String title;

	/**
	 * Constructors
	 */
	public TopicModel(){
		super();
		this.title = "Default Title";
	}
	
	public TopicModel(UserModel theUser) {
		super(theUser); 
	}
	
	/**
	 * auto generated setters and getters
	 */
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
