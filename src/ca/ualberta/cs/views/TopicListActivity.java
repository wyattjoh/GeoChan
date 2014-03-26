package ca.ualberta.cs.views;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.PostListController;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.DummyPostListFactory;
import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.models.UserModel;

/**
 * 
 * Lists recent topics, favorite topics and comments, and read later topics and comments.
 * 
 * @author wyatt
 */
public class TopicListActivity extends FragmentActivity {

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> fragments;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);

			this.fragments = new ArrayList<Fragment>();
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new TopicListActivityFragment();
			Bundle args = new Bundle();
			args.putInt(TopicListActivityFragment.ARG_SECTION_NUMBER,
					position + 1);
			fragment.setArguments(args);

			// Add to list of fragments
			fragments.add(fragment);

			return fragment;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}

		public void refreshFragments() {
			for (Fragment fragment : fragments) {
				// Update Fragments
				((TopicListActivityFragment) fragment).refresh();
			}
		}
	}

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter sectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	BroadcastReceiver connectionBroadcastReceiver = null;

	/**
	 * Called when the cell is clicked. Starts the detail view activity
	 * 
	 * @param v The view that was clicked
	 */
	public void cellClicked(View v) {
		// Get the selected tag position
		Integer position = (Integer) v.getTag();

		// Get the model list
		TopicModelList topicModelList = TopicModelList.getInstance();

		// Mark the selected model
		topicModelList.setSelection(position.intValue());

		// Start intent
		Intent intent = new Intent(this, TopicViewActivity.class);
		startActivity(intent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == Activity.RESULT_OK) {
				loginFlow();
			}
		}
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		sectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(sectionsPagerAdapter);

		Context context = getApplicationContext();
		this.connectionBroadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				// Network changed, refresh the fragments
				refreshFragments();
			}
		};

		context.registerReceiver(this.connectionBroadcastReceiver,
				new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		// Unregister from broadcasts
		if (this.connectionBroadcastReceiver != null) {
			Context context = getApplicationContext();
			context.unregisterReceiver(this.connectionBroadcastReceiver);
			this.connectionBroadcastReceiver = null;
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.cellActiveArea:
			newPost();
			return true;
		case R.id.action_settings:
			startSettingsActivity();
			return true;
		case R.id.action_sortDate:
			PostListController.setSort(PostListController.SORT_DATE);

			// Refresh the lists
			refreshFragments();
			return true;
		case R.id.action_sortScore:
			PostListController.setSort(PostListController.SORT_SCORE);

			// Refresh the lists
			refreshFragments();
			return true;
		case R.id.action_sortDefault:
			PostListController.setSort(PostListController.SORT_DEFAULT);

			// Refresh the lists
			refreshFragments();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onStart()
	 */
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		// Must be first thing that is started, sets up contexts
		createSharedSingletons(getApplicationContext());

		// Perform the login flow process
		loginFlow();
		
		
		// TODO remove
		// populate with fake data
		DummyPostListFactory.createCommentedTopics(new UserModel("4ChanTroll"));
		FavoriteTopicModelList.getInstance().setArrayList(DummyPostListFactory.getTopicList(new UserModel("GeoChanUser")));
	}

	/**
	 * Creates shared singletons requiring initialization
	 * 
	 * @param applicationContext The application context for the main activity
	 */
	private void createSharedSingletons(Context applicationContext) {
		// Create Active User
		ActiveUserModel.createShared(getApplicationContext());

		// Create Favorites List
		FavoriteTopicModelList.createInstance(applicationContext);
	}

	/**
	 * Refreshes fragments inside the sectionPagerAdapter
	 */
	protected void refreshFragments() {
		// Update all fragments
		sectionsPagerAdapter.refreshFragments();
	}

	/**
	 * Starts a new activity to create a new post 
	 */
	protected void newPost() {
		Intent intent = new Intent(this, EditTopicActivity.class);
		startActivity(intent);
	}

	/**
	 * Starts the settings activity
	 */
	protected void startSettingsActivity() {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}

	/**
	 * Verifies that the user is logged in, if not, starts the login activity
	 */
	private void loginFlow() {
		ActiveUserModel userController = ActiveUserModel.getShared();

		if (userController.isLoggedIn()) {
			// continue
		} else {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivityForResult(intent, 1);
		}
	}
}
