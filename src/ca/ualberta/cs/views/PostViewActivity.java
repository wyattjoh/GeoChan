package ca.ualberta.cs.views;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.adapters.CommentListViewAdapter;
import ca.ualberta.cs.models.CommentModel;
import ca.ualberta.cs.models.CommentModelList;
import ca.ualberta.cs.models.PostModel;

public abstract class PostViewActivity<T extends PostModel> extends Activity {
	protected T theModel = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_view);

		// Get the selected model to display
		getSelectedModel();

		// If we have a selected model...
		if (theModel != null) {
			// Populate the view!
			populateView();
		} else {
			// finish it?
			finish();
		}
	}

	abstract protected void getSelectedModel();

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post_view, menu);
		return true;
	}

	private void populateView() {
		// Add comment
		TextView commentView = (TextView) findViewById(R.id.commentTextView);
		commentView.setText(theModel.getCommentText());

		// Add score
		TextView scoreView = (TextView) findViewById(R.id.scorePostTextView);
		String scoreString = "";
		if (theModel.getScore() > 0) {
			scoreString = "+";
		} else if (theModel.getScore() < 0) {
			scoreString = "-";
		}
		scoreString = scoreString + theModel.getScore().toString();
		scoreView.setText(scoreString);
		
		// Add Buttons
		ImageButton downVoteButton = (ImageButton) findViewById(R.id.downVoteButton);
		
		downVoteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				theModel.decrementScore();
				populateView();
				
			}
		});
		
		ImageButton upVoteButton = (ImageButton) findViewById(R.id.upVoteButton);
		
		upVoteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				theModel.incrementScore();
				populateView();
			}
		});

		// Add Author
		TextView authorView = (TextView) findViewById(R.id.authorTextView);
		authorView.setText(theModel.getPostedBy().getUserName());

		// Add or remove title text
		setTitleText();

		// Add image
		ImageView imageView = (ImageView) findViewById(R.id.imageView);
		Bitmap thePicture = theModel.getPicture();
		if (thePicture == null) {
			// No picture, hide the field
			imageView.setVisibility(View.GONE);
		} else {
			// A picture, add the image
			// TODO: Implement
		}

		// Add comments
		ListView commentsListView = (ListView) findViewById(R.id.commentsListView);
		
		if (theModel.getChildrenComments() == null) {
			
		}
		else {
			// Has children!
			CommentListViewAdapter<CommentModel> commentsAdapter = new CommentListViewAdapter<CommentModel>(this, theModel.getChildrenComments());
			commentsListView.setAdapter(commentsAdapter);
		}
	}

	abstract void setTitleText();
	
	public void cellClicked(View theView) {
		Integer thePosition = (Integer) theView.getTag();
		
		CommentModelList commentModelList = CommentModelList.getInstance(theModel);
		commentModelList.setSelection(thePosition);
		
		Intent intent = new Intent(this, CommentViewActivity.class);
		startActivity(intent);
		
		Log.w("PostViewActivity", "the cell was clicked!");
	}

}