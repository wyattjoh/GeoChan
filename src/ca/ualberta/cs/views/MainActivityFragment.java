package ca.ualberta.cs.views;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.NetworkModel;

/**
 * A dummy fragment representing a section of the application, but that simply
 * displays dummy text.
 */
public class MainActivityFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// get fragment number
		int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

		// get fragment view
		View rootView = inflater.inflate(R.layout.fragment_post_list, container, false);
		LinearLayout theLayout = (LinearLayout) rootView.findViewById(R.id.mainListBox);
		
		MainActivityFragmentComponent theComponent = MainActivityFragmentComponent.getComponentForPosition(sectionNumber);
		theComponent.setupView(inflater, theLayout, getActivity());

		// Update the status
		setConnectionStatus(rootView);

		return rootView;
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onDestroyView()
	 */
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		
		// get fragment number
		int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
		
		MainActivityFragmentComponent theComponent = MainActivityFragmentComponent.getComponentForPosition(sectionNumber);
		theComponent.destroy();
	}

	/**
	 * Updates all lists
	 */
	public void notifyNetworkStateChanged() {
		// Refresh views
		List<Fragment> fragments = getFragmentManager().getFragments();

		for (Fragment fragment : fragments) {
			// Get the view
			View theView = fragment.getView();

			if (theView != null) {
				// Update connection status
				setConnectionStatus(fragment.getView());
			}
		}
	}

	// TODO: Refactor this whole thing...
	private static enum STATE {
		CONNECTED, NOT_CONNECTED, LOADING
	};

	/**
	 * set the connection text taken from http://stackoverflow.com/questions/
	 * 14198605/how-to-hide-a-textview-in-simpleadapter
	 * 
	 * @param theRootView
	 */
	public void setConnectionStatus(View theRootView) {
		Drawable theConnectionIcon = null;
		String statusText = null;

		STATE state = null;
		if (NetworkModel.isNetworkAvailable(getActivity()
				.getApplicationContext())) {
			state = STATE.CONNECTED;
		} else {
			state = STATE.NOT_CONNECTED;
		}

		switch (state) {
		case CONNECTED:
			break;

		case NOT_CONNECTED:
			theConnectionIcon = getResources().getDrawable(
					R.drawable.ic_action_warning);
			statusText = "Not Connected";
			break;

		case LOADING:
			theConnectionIcon = getResources().getDrawable(
					R.drawable.ic_action_refresh);
			statusText = "Loading...";
			break;
		}

		if (statusText != null) {
			// Make visible
			LinearLayout listStatusBox = (LinearLayout) theRootView
					.findViewById(R.id.listStatusBox);
			listStatusBox.setVisibility(View.VISIBLE);

			// Set the text
			TextView listStatusText = (TextView) theRootView
					.findViewById(R.id.listStatusText);
			listStatusText.setText(statusText);

			// Set the image
			ImageView listStatusIcon = (ImageView) theRootView
					.findViewById(R.id.listStatusIcon);
			listStatusIcon.setImageDrawable(theConnectionIcon);
		} else {
			// Hide bar, its connected!
			LinearLayout listStatusBox = (LinearLayout) theRootView
					.findViewById(R.id.listStatusBox);
			listStatusBox.setVisibility(View.INVISIBLE);
		}
	}
}