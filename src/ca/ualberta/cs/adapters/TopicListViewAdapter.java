package ca.ualberta.cs.adapters;

import java.util.ArrayList;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.TopicModel;

public class TopicListViewAdapter<T extends TopicModel> extends PostListViewAdapter<T> {

	public TopicListViewAdapter(FragmentActivity theActivity,
			ArrayList<T> arrayList) {
		super(theActivity, arrayList);
	}

	@Override
	protected void populateCellTitle(View theView, T thePost) {
		// Fill title/comment text
		TextView titleText = (TextView) theView.findViewById(R.id.textViewTitle);
		// List of topics, display the title
		String theTitle = ((TopicModel) thePost).getTitle();
		titleText.setText(theTitle);
	}
}
