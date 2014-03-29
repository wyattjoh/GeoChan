package ca.ualberta.cs.models;

import android.content.Context;

public class VoteStatusList {
	private static VoteStatusList singleton = null;
	
	private VoteStatusList(Context applicationContext) {
		super();
	}
	
	public static VoteStatusList createInstance(Context applicationContext) {
		if (singleton == null) {
			singleton = new VoteStatusList(applicationContext);
		}
		return singleton;
	}
	
	public static VoteStatusList getInstance() {
		if (singleton == null) {
			throw new RuntimeException("Cannot get an instance before you create it!");
		}
		return singleton;
	}
}
