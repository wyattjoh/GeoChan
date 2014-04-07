package ca.ualberta.cs.models;

public class TopicModel extends PostModel {
	private String title;
	private transient int version;

	/**
	 * Constructors
	 */
	public TopicModel() {
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

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String getQualifyingId() {
		return getId();
	}

}
