package ca.ualberta.cs.models;

public class TopicModel extends PostModel {
	private String id;
	private String title;
	private int version;

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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see ca.ualberta.cs.models.PostModel#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		if (!(o instanceof TopicModel)) {
			return false;
		}
		
		TopicModel oModel = (TopicModel) o;
		
		if (oModel.getId().equals(getId())) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
