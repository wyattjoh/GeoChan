package ca.ualberta.cs.views;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.PostListController;
import ca.ualberta.cs.models.ActiveUserModel;

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
			args.putInt(TopicListActivityFragment.ARG_SECTION_NUMBER, position + 1);
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
		
		public void updateFragments() {
			for (Fragment fragment : fragments) {
				// Update Fragments
				((TopicListActivityFragment) fragment).update();
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
		case R.id.action_new_post:
			newPost();
			return true;
		case R.id.action_settings:
			startSettingsActivity();
			return true;
		case R.id.action_sortDate:
			PostListController.setSort(PostListController.SORT_DATE);
			
			// Refresh the lists
			refreshLists();
			return true;
		case R.id.action_sortScore:
			PostListController.setSort(PostListController.SORT_SCORE);
			
			// Refresh the lists
			refreshLists();
			return true;
		case R.id.action_sortDefault:
			PostListController.setSort(PostListController.SORT_DEFAULT);

			// Refresh the lists
			refreshLists();
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

	protected void refreshLists() {
		// Update all fragments
		sectionsPagerAdapter.updateFragments();
	}

	protected void startSettingsActivity() {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
}