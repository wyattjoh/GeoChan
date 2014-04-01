package ca.ualberta.cs.models;

import ca.ualberta.cs.views.MainActivityFragmentComponent;

public class FragmentTag {
	private MainActivityFragmentComponent component;
	private int position;

	/**
	 * @return the component
	 */
	public MainActivityFragmentComponent getComponent() {
		return component;
	}

	/**
	 * @param component the component to set
	 */
	public void setComponent(MainActivityFragmentComponent component) {
		this.component = component;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
}
