package ca.ualberta.cs.views;

import java.util.ArrayList;

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

	Context context;
	ArrayList<?> data;
	private static LayoutInflater inflater = null;

	public PostListViewAdapter(Context context, ArrayList<?> theData) {
		// TODO Auto-generated constructor stub
		this.data = theData;		
		this.context = context;		
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return (PostModel)data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int thePosition, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View vi = convertView;
		if (vi == null)
			vi = inflater.inflate(R.layout.row, null);

		// set row elements to the required data
		setRowValues(vi, thePosition);
		
		return vi;
	}
	
	/**
	 * fill in the row at the called position with the appropriate data
	 * @param theView
	 * @param thePosition
	 */
	public void setRowValues(View theView, int thePosition){
		// get text views for data
		TextView dateText = (TextView) theView.findViewById(R.id.textViewAge);
		TextView authorText = (TextView) theView.findViewById(R.id.textViewAuthor);
		TextView commentText = (TextView) theView.findViewById(R.id.textViewComments);
		TextView locationText = (TextView) theView.findViewById(R.id.textViewLocation);
		TextView scoreText = (TextView) theView.findViewById(R.id.textViewScore);
		TextView titleText = (TextView) theView.findViewById(R.id.textViewTitle);
		
		// check and set text views
		// DATE
		if (dateText != null){
			String date = (String) DateFormat.format("yyyy/MM/dd", ((PostModel) data.get(thePosition)).getDatePosted());
			dateText.setText(date);
		}
		
		// AUTHOR
		if (authorText != null){
			authorText.setText(((PostModel) data.get(thePosition)).getPostedBy().getUserName());
		}
		
		// NUMBER OF COMMENTS
		if (commentText != null ){
			if (((PostModel) data.get(thePosition)).getChildrenComments() != null){
				commentText.setText(((PostModel) data.get(thePosition)).getChildrenComments().size() + " Replies");
			}
			else {
				commentText.setText("0 Replies");
			}
		}
		
		// LOCATION
		if (locationText != null){
			locationText.setText("'162 '163.123");
			// filler values until we get the location handler working
			
			// locationText.setText(((PostModel) data.get(thePosition)).getLocation().toString());
		}
		
		// SCORE
		if (scoreText != null){
			scoreText.setText(((PostModel) data.get(thePosition)).getScore().toString());
		}
		
		// TITLE
		if (titleText != null){
			if (data.get(thePosition).getClass() == TopicModel.class){
				titleText.setText(((TopicModel) data.get(thePosition)).getTitle());
			}
			else{
				titleText.setText("Reply");
			}
		}
	}
}