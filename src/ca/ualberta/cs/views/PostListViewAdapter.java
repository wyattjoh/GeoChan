package ca.ualberta.cs.views;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.TopicModel;

/**
 * 
 * @author vincent
 * 
 *         taken from stackoverflow @
 *         http://stackoverflow.com/questions/8166497/
 *         custom-adapter-for-list-view
 */

public class PostListViewAdapter extends BaseAdapter {
	private Boolean isTopicList = false;
	private ArrayList<?> postModelList;
	private LayoutInflater layoutInflater = null;

	public PostListViewAdapter(Activity theActivity, ArrayList<?> thePostModelList) {
		// TODO Auto-generated constructor stub
		this.postModelList = thePostModelList;	
		this.layoutInflater = theActivity.getLayoutInflater();
		
		if (thePostModelList.get(0).getClass().equals(TopicModel.class)) {
			this.isTopicList = true;
		}
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return postModelList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return (PostModel)postModelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int thePosition, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
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
	 * @param theView
	 * @param thePosition
	 */
	public void populateRowView(View theView, int thePosition){
		// Get the post object
		PostModel thePost = (PostModel) postModelList.get(thePosition);
		
		// Fill date
		TextView dateTextView = (TextView) theView.findViewById(R.id.textViewAge);
		String date = (String) DateFormat.format("yyyy/MM/dd", thePost.getDatePosted());
		dateTextView.setText(date);		
		
		// Fill author
		TextView authorTextView = (TextView) theView.findViewById(R.id.textViewAuthor);
		authorTextView.setText(thePost.getPostedBy().getUserName());		
		
		// Fill comment count
		TextView commentTextView = (TextView) theView.findViewById(R.id.textViewComments);
		if (thePost.getChildrenComments() == null) {
			commentTextView.setText("0");
		}
		else {
			String commentCount = Integer.toString(thePost.getChildrenComments().size());
			commentTextView.setText(commentCount);
		}
		
		// Fill location
		// TODO: Add location text
		// TextView locationText = (TextView) theView.findViewById(R.id.textViewLocation);
		// locationText.setText(thePost.getLocation().toString());
		
		// Fill score
		TextView scoreText = (TextView) theView.findViewById(R.id.textViewScore);
		scoreText.setText(thePost.getScore().toString());
		
		// Fill title/comment text
		TextView titleText = (TextView) theView.findViewById(R.id.textViewTitle);
		if (this.isTopicList){
			// List of topics, display the title
			String theTitle = ((TopicModel) thePost).getTitle();
			titleText.setText(theTitle);
		}
		else{
			// List of comments, display an excerpt of their comment
			// TODO: Add excerpt code
			titleText.setText("Reply");
		}
	}
}