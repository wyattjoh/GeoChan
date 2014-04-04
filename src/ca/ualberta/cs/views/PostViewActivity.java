package ca.ualberta.cs.views;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.adapters.CommentListViewAdapter;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.CommentModelList;
import ca.ualberta.cs.models.EditPostModel;
import ca.ualberta.cs.models.PostModel;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.models.UserModel;

public abstract class PostViewActivity<T extends PostModel> extends Activity {
	/**
	 * Populates theModel with the proper selected model
	 * 
	 * @return TODO
	 */
	abstract protected T getSelectedModel();

	abstract void setTitleText();

	abstract protected OnClickListener getFavoriteOnClickListener();

	protected T theModel = null;

	/**
	 * Starts an activity to reply to the currently visible post
	 */
	protected void replyToPost() {
		EditPostModel theEditPostModel = EditPostModel.getInstance();
		theEditPostModel.setTheParent(theModel);

		Intent intent = new Intent(this, EditCommentActivity.class);
		startActivity(intent);
	}

	protected CommentListViewAdapter thePostAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_view);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Populate the model
		this.theModel = getSelectedModel();

		// Populate the view
		if (this.theModel == null) {
			throw new RuntimeException(
					"Tried to execute the view without selecting anything? (No idea how you got here...)");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// Populate the view!
		populateView();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		if (thePostAdapter != null) {
			TopicModelList.getInstance().unRegisterListeningAdapter(
					thePostAdapter);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.cellActiveArea:
			replyToPost();
			return true;
		case R.id.action_settings:
			startSettingsActivity();
			return true;
		case android.R.id.home:
			onBackPressed();
			return true;
		case R.id.refreshButton:
			populateView();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Starts the settings activity
	 */
	protected void startSettingsActivity() {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}

	protected void populateView() {
		// Add comment
		TextView commentView = (TextView) findViewById(R.id.commentTextView);
		commentView.setText(theModel.getCommentText());

		// Add score
		final TextView scoreView = (TextView) findViewById(R.id.scorePostTextView);

		// Add Buttons
		final ImageButton downVoteButton = (ImageButton) findViewById(R.id.downVoteButton);
		final ImageButton upVoteButton = (ImageButton) findViewById(R.id.upVoteButton);
		populateScoreControlsAndView(scoreView, downVoteButton, upVoteButton);

		// Add Date
		TextView dateView = (TextView) findViewById(R.id.ageTextView);
		String date = (String) DateFormat.format("yyyy/MM/dd",
				theModel.getDatePosted());
		dateView.setText(date);

		// Add Author
		TextView authorView = (TextView) findViewById(R.id.authorTextView);
		authorView.setText(theModel.getPostedBy().getUserName());

		// Add or remove title text
		setTitleText();

		// Add image
		ImageView imageView = (ImageView) findViewById(R.id.imageView);
		populateImageView(imageView);

		// Distance button
		Button distanceButton = (Button) findViewById(R.id.distanceButton);
		populateDistanceButton(distanceButton);

		// Add comments
		populateCommentsView();

		// Favorite Button
		ImageButton favoriteButton = (ImageButton) findViewById(R.id.favoriteButton);
		populateFavoritesButton(favoriteButton);
	}

	private void populateScoreControlsAndView(final TextView scoreView,
			final ImageButton downVoteButton, final ImageButton upVoteButton) {
		String scoreString = "";
		if (theModel.getScore() > 0) {
			scoreString = "+";
		}

		scoreString = scoreString + theModel.getScore().toString();
		scoreView.setText(scoreString);

		UserModel theLoggedInUser = ActiveUserModel.getInstance().getUser();
		ArrayList<String> downVoteList = theLoggedInUser.getDownVoteList();

		if (downVoteList.contains(theModel.getId())) {
			downVoteButton.setPressed(true);
		} else {
			downVoteButton.setPressed(false);
		}

		downVoteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UserModel theLoggedInUser = ActiveUserModel.getInstance()
						.getUser();

				if (!theLoggedInUser.getUpVoteList().contains(theModel.getId())) {
					if (theLoggedInUser.getDownVoteList().contains(
							theModel.getId())) {
						theLoggedInUser.removePostIdDownVoteList(theModel
								.getId());
						theModel.incrementScore();
						String string = "";
						if (theModel.getScore() > 0) {
							string = "+";
						}

						string = string + theModel.getScore().toString();
						scoreView.setText(string);
					} else {
						theLoggedInUser.addPostIdDownVoteList(theModel.getId());
						theModel.decrementScore();
						String string = "";
						if (theModel.getScore() > 0) {
							string = "+";
						}

						string = string + theModel.getScore().toString();
						scoreView.setText(string);

					}
				} else {
					theLoggedInUser.removePostIdUpVoteList(theModel.getId());
					theLoggedInUser.addPostIdDownVoteList(theModel.getId());
					theModel.decrementScore();
					theModel.decrementScore();
					String string = "";
					if (theModel.getScore() > 0) {
						string = "+";
					}

					string = string + theModel.getScore().toString();
					scoreView.setText(string);
				}
			}
		});

		if (theLoggedInUser.getUpVoteList().contains(theModel.getId())) {
			upVoteButton.setPressed(true);
		} else {
			upVoteButton.setPressed(false);
		}

		upVoteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				UserModel theLoggedInUser = ActiveUserModel.getInstance()
						.getUser();

				if (!theLoggedInUser.getDownVoteList().contains(
						theModel.getId())) {
					if (theLoggedInUser.getUpVoteList().contains(
							theModel.getId())) {
						theLoggedInUser.removePostIdUpVoteList(theModel.getId());
						theModel.decrementScore();
						String string = "";
						if (theModel.getScore() > 0) {
							string = "+";
						}

						string = string + theModel.getScore().toString();
						scoreView.setText(string);
					} else {
						theLoggedInUser.addPostIdUpVoteList(theModel.getId());
						theModel.incrementScore();
						String string = "";
						if (theModel.getScore() > 0) {
							string = "+";
						}

						string = string + theModel.getScore().toString();
						scoreView.setText(string);

					}
				} else {
					theLoggedInUser.removePostIdDownVoteList(theModel.getId());
					theLoggedInUser.addPostIdUpVoteList(theModel.getId());
					theModel.incrementScore();
					theModel.incrementScore();
					String string = "";
					if (theModel.getScore() > 0) {
						string = "+";
					}

					string = string + theModel.getScore().toString();
					scoreView.setText(string);
				}
			}

		});
	}

	private void populateImageView(ImageView imageView) {
		Bitmap thePicture = theModel.getPicture();
		if (thePicture == null) {
			// No picture, hide the field
			imageView.setVisibility(View.GONE);
		} else {
			// A picture, add the image
			// TODO: Implement
			imageView.setImageBitmap(thePicture);
		}
	}

	private void populateFavoritesButton(ImageButton favoriteButton) {
		favoriteButton.setOnClickListener(getFavoriteOnClickListener());

		if (theModel.isFavorite()) {
			favoriteButton.setImageResource(android.R.drawable.btn_star_big_on);
		} else {
			favoriteButton
					.setImageResource(android.R.drawable.btn_star_big_off);
		}
	}

	private void populateDistanceButton(Button distanceButton) {
		if (theModel.getLocation() != null) {
			if (ActiveUserModel.getInstance().getUser().getLocation() != null) {
				Location userLocation = new Location(ActiveUserModel
						.getInstance().getUser().getLocation());
				float distanceToPost = userLocation.distanceTo(theModel
						.getLocation()) / 1000;
				String distanceButtonText = String.format("%.2f",
						distanceToPost) + " km";

				distanceButton.setText(distanceButtonText.toCharArray(), 0,
						distanceButtonText.length());
			} else {
				distanceButton.setText(theModel.getLocationAsString());
			}
		} else {
			distanceButton.setText("Location");
		}
	}

	private void populateCommentsView() {
		ListView commentsListView = (ListView) findViewById(R.id.commentsListView);
		CommentModelList theCommentModelList = CommentModelList
				.getInstanceFromParent(theModel);

		// Has children!
		thePostAdapter = new CommentListViewAdapter(this, theCommentModelList);
		commentsListView.setAdapter(thePostAdapter);
	}
}