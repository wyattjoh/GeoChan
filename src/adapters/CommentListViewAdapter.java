package adapters;

import java.util.ArrayList;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.CommentModel;

public class CommentListViewAdapter<T extends CommentModel> extends PostListViewAdapter<T> {

	public CommentListViewAdapter(FragmentActivity theActivity,
			ArrayList<T> arrayList) {
		super(theActivity, arrayList);
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
