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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.PostListController;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.NetworkModel;
import ca.ualberta.cs.models.TopicModelList;

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

	/*
	 * Called when the cell is clicked
	 */
	public void cellClicked(View v) {
		// Get the selected tag position
		Integer position = (Integer) v.getTag();

		// Get the model list
		TopicModelList topicModelList = TopicModelList.getInstance();

		// Mark the selected model
		topicModelList.selectTopicModel(position.intValue());

		// Start intent
		Intent intent = new Intent(this, TopicViewActivity.class);
		startActivity(intent);
	}

	private void loginFlow() {
		ActiveUserModel userController = ActiveUserModel
				.createShared(getApplicationContext());

		if (userController.isLoggedIn()) {
			// continue
		} else {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivityForResult(intent, 1);
		}
	}

	protected void newPost() {
		Intent intent = new Intent(this, EditTopicActivity.class);
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

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		loginFlow();
	}

	protected void refreshFragments() {
		// Update all fragments
		sectionsPagerAdapter.refreshFragments();
	}

	protected void startSettingsActivity() {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
}
