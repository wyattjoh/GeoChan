package ca.ualberta.cs.adapters;

import java.util.ArrayList;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.TopicModel;

public class TopicListViewAdapter extends PostListViewAdapter<TopicModel> {

	public TopicListViewAdapter(FragmentActivity theActivity,
			ArrayList<TopicModel> arrayList) {
		super(theActivity, arrayList);
	}

	@Override
	protected void populateCellTitle(View theView, TopicModel thePost) {
		// Fill title/comment text
		TextView titleText = (TextView) theView
				.findViewById(R.id.textViewTitle);
		// List of topics, display the title
		String theTitle = thePost.getTitle();
		titleText.setText(theTitle);
	}
}
