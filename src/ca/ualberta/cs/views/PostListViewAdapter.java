package ca.ualberta.cs.views;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.CommentModel;
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
		if (data.get(thePosition).getClass() == TopicModel.class) {
			TextView header = (TextView) vi.findViewById(R.id.textViewTitle);
			header.setText(((TopicModel) data.get(thePosition)).getTitle());
		}

		// set row elements to topic data
		//TextView text = (TextView) vi.findViewById(R.id.);
		//text.setText(getFormatedData(thePosition));
		
		return vi;
	}

	// format the data for the list view elements
	public String getFormatedData(int thePosition) {
		// get user name & score
		String theUserName =  ((PostModel) data.get(thePosition)).getPostedBy().getUserName();
		String thePostScore = ((PostModel) data.get(thePosition)).getScore().toString();

		// get the number of replies
		ArrayList<CommentModel> theComments = ((PostModel) data.get(thePosition))
				.getChildrenComments();
		Integer theReplyCount = 0;
		// check if any, might be null
		if (theComments != null) {
			theReplyCount = theComments.size();
		}

		// return formated string
		return "Score:" + thePostScore + " Posted By:" + theUserName
				+ " Replies:" + theReplyCount;
	}
}