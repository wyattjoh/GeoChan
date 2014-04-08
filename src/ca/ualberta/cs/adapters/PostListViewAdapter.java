package ca.ualberta.cs.adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.PostModelList;

/**
 * 
 * @author vincent
 * 
 */

public abstract class PostListViewAdapter<T extends PostModel> extends
		ArrayAdapter<T> implements OnClickListener {
	private LayoutInflater layoutInflater = null;
	protected PostModelList<T> theArrayList;
	protected Activity theActivity;

	public PostListViewAdapter(Activity activity, PostModelList<T> arrayList) {
		super(activity, R.layout.row, arrayList.getArrayList());
		// TODO Auto-generated constructor stub
		this.layoutInflater = activity.getLayoutInflater();
		this.theActivity = activity;
		this.theArrayList = arrayList;
	}

	@Override
	public View getView(int thePosition, View convertView, ViewGroup parent) {
		View postRowView = convertView;

		if (postRowView == null) {
			postRowView = layoutInflater.inflate(R.layout.row, parent, false);
		}

		// set row elements to the required data
		populateRowView(postRowView, thePosition);

		return postRowView;
	}

	/**
	 * fill in the row at the called position with the appropriate data
	 * 
	 * @param theView
	 * @param thePosition
	 */
	protected void populateRowView(View theView, int thePosition) {
		// Get the post object
		T theObject = getItem(thePosition);
		PostModel thePost = theObject;

		// Add position tag
		RelativeLayout cellActiveArea = (RelativeLayout) theView
				.findViewById(R.id.cellActiveArea);
		cellActiveArea.setTag(thePosition);
		cellActiveArea.setOnClickListener(this);

		// Fill date
		TextView dateTextView = (TextView) theView
				.findViewById(R.id.textViewAge);
		String date = (String) DateFormat.format("yyyy/MM/dd",
				thePost.getDatePosted());
		dateTextView.setText(date);

		// Fill author
		TextView authorTextView = (TextView) theView
				.findViewById(R.id.textViewAuthor);
		authorTextView.setText(thePost.getPostedBy().getUserName());

		// Fill comment count
		TextView commentTextView = (TextView) theView
				.findViewById(R.id.textViewComments);
		commentTextView.setText(Integer.toString(thePost.getChildrenComments()
				.size()));

		// Fill location
		// TODO: Add location text
		TextView locationText = (TextView) theView
				.findViewById(R.id.textViewLocation);
		if (thePost.getLocation() != null) {
			if (ActiveUserModel.getInstance().getUser().getLocation() != null) {
				Location userLocation = new Location(ActiveUserModel
						.getInstance().getUser().getLocation());
				float distanceToPost = userLocation.distanceTo(thePost
						.getLocation()) / 1000;
				String distanceButtonText = String.format("%.2f",
						distanceToPost) + " km";

				locationText.setText(distanceButtonText.toCharArray(), 0,
						distanceButtonText.length());
			} else {
				locationText.setText(thePost.getLocationAsString());
			}
		} else {
			locationText.setText("Location");
		}

		// Fill score
		TextView scoreText = (TextView) theView
				.findViewById(R.id.textViewScore);

		if (thePost.getScore() >= 0) {
			scoreText.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.ic_action_good, 0, 0, 0);
		} else {
			scoreText.setCompoundDrawablesWithIntrinsicBounds(
					R.drawable.ic_action_bad, 0, 0, 0);
		}

		String scoreString = thePost.getScore().toString();
		scoreText.setText(scoreString);

		// Fill picture
		ImageView imageView = (ImageView) theView
				.findViewById(R.id.imageViewPicture);
		if (thePost.hasPicture()) {
			imageView.setImageBitmap(thePost.getPicture());
		} else {
			imageView.setVisibility(View.GONE);
		}

		populateCellTitle(theView, theObject);
	}

	/**
	 * Trims a string to a max length
	 * 
	 * @param theString
	 *            the string to trim
	 * @param theLength
	 *            the max length of the string
	 * @return a trimmed string
	 */
	protected String trimString(String theString, int theLength) {
		if (theString.length() > theLength) {
			return theString.substring(0, theLength);
		} else {
			return theString;
		}
	}

	abstract protected void populateCellTitle(View theView, T thePost);

	abstract protected Class<?> getViewClass();

	abstract protected void setSelectedList();

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// Get the selected tag position
		Integer position = (Integer) v.getTag();

		// Mark the selected model
		this.theArrayList.addToSelectionStackFromPosition(position.intValue());

		// Start intent
		Intent intent = new Intent(this.theActivity, getViewClass());
		setSelectedList();
		this.theActivity.startActivity(intent);
	}

}