package ca.ualberta.cs.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.EditTopicController;
import ca.ualberta.cs.models.TopicModel;

public class EditTopicActivity extends Activity {
	public static final String IS_NEW_TOPIC_KEY = "IS_NEW_TOPIC";

	private EditTopicController theController;
	private Boolean isNewTopic = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_topic);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_topic, menu);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		// Get the extras
		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			this.isNewTopic = extras.getBoolean(IS_NEW_TOPIC_KEY);
		}

		// Get the controller
		this.theController = new EditTopicController();

		// Populate the view
		populateView();
	}

	private void populateView() {
		Button saveButton = (Button) findViewById(R.id.saveOrAddButton);

		if (this.isNewTopic) {
			saveButton.setText("Add Topic");
			saveButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					TopicModel theTopicModel = new TopicModel();

					// Get the title
					EditText titleField = (EditText) findViewById(R.id.titleTextField);
					theTopicModel.setTitle(titleField.getText().toString());

					// Get the comment
					EditText commentField = (EditText) findViewById(R.id.commentTextField);
					theTopicModel.setCommentText(commentField.getText().toString());

					theController.newTopic(theTopicModel);

					finish();
				}
			});
		} else {
			saveButton.setText("Update Topic");
		}

	}

	public Boolean getIsNewTopic() {
		return isNewTopic;
	}

}
