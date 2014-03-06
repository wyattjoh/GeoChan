package ca.ualberta.cs.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import ca.ualberta.cs.R;
import ca.ualberta.cs.models.TopicModel;

import com.google.gson.Gson;

public class CommentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		
		// get gson string from intent
		String serializedTopic =  getIntent().getStringExtra("theTopicModel");
		Gson gsonTopic = new Gson();
		TopicModel theTopic = gsonTopic.fromJson(serializedTopic, TopicModel.class);
		
		System.out.println(theTopic.getTitle());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comment, menu);
		return true;
	}

}
