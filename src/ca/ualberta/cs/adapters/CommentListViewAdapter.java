package ca.ualberta.cs.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.CommentModel;

public class CommentListViewAdapter<T extends CommentModel> extends PostListViewAdapter<T> {

	public CommentListViewAdapter(Activity activity,
			ArrayList<T> arrayList) {
		super(activity, arrayList);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void populateCellTitle(View theView, T thePost) {
		TextView titleText = (TextView) theView.findViewById(R.id.textViewTitle);
		// List of comments, display an excerpt of their comment
		// TODO: Add excerpt code
		titleText.setText("Reply");
	}

}
