package ca.ualberta.cs.views;

import java.util.ArrayList;

import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.PostListController;
import ca.ualberta.cs.models.TopicModel;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A dummy fragment representing a section of the app, but that simply
 * displays dummy text.
 */
public class DummySectionFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";

	public DummySectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// random generated code...
		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
				container, false);
		TextView dummyTextView = (TextView) rootView
				.findViewById(R.id.section_label);
		dummyTextView.setText(Integer.toString(getArguments().getInt(
				ARG_SECTION_NUMBER)));

		// get fragment number
		int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

		switch (sectionNumber) {
			case 1:
				populateFragment(rootView, PostListController.createTopicList());
				break;
			case 2:
				break;
			case 3:
				break;
		}

		return rootView;
	}

	public void populateFragment(View theRootView,
			ArrayList<TopicModel> theTopicList) {
		ListView listView = (ListView) theRootView
				.findViewById(R.id.postListView);
		TopicListAdapter listAdapter = new TopicListAdapter(getActivity(),
				PostListController.createTopicList());
		listView.setAdapter(listAdapter);
	}
}
