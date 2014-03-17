package ca.ualberta.cs.views;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Observer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.PostListController;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.TopicModel;
import ca.ualberta.cs.models.TopicModelList;

import com.google.gson.Gson;

public class MainActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
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
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {

			// random generated code...
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);

			// get fragment number
			int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);

			switch (sectionNumber) {
			case 1:
				// create topic list is a stand in for the actual data
				// TODO get actual data, and add method to the controller
				PostListController.createCommentedTopics(null);
				populateFragment(rootView, TopicModelList.shared());
				setConnectionStatus(rootView);
				setListener(rootView);
				break;
			case 2:
				break;
			case 3:
				break;
			}

			return rootView;
		}

		public void populateFragment(View theRootView,
				TopicModelList topicModelList) {
			// get title & list view adapter
			ListView listView = (ListView) theRootView
					.findViewById(R.id.postListView);
			PostListViewAdapter listAdapter = new PostListViewAdapter(
					getActivity(), topicModelList.getTopicModelArrayList());

			// set adapter
			listView.setAdapter(listAdapter);
			listAdapter.notifyDataSetChanged();
		}

		/**
		 * set element on click listener
		 * 
		 * @param theRootView
		 */
		public void setListener(final View theRootView) {
			// get list view
			final ListView postList = (ListView) theRootView
					.findViewById(R.id.postListView);

			// set list view listener
			postList.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// get the topic model object from the list view
					TopicModel model = (TopicModel) postList.getAdapter()
							.getItem(position);
					System.out.println(model.getTitle());
					startCommentActivty(theRootView, model);

				}
			});
		}

		/**
		 * set the connection text taken from
		 * http://stackoverflow.com/questions/
		 * 14198605/how-to-hide-a-textview-in-simpleadapter
		 * 
		 * @param theRootView
		 */
		public void setConnectionStatus(View theRootView) {
			TextView connectionStatus = (TextView) theRootView
					.findViewById(R.id.connectionStatusText);
			// TODO toggle text based on connection status
			if (null != null) {
				connectionStatus.setVisibility(View.GONE);
			} else {
				connectionStatus.setTextColor(Color.RED);
			}
		}

		/**
		 * start comment activity and pass the topic element as an argument
		 * 
		 * @param theRootView
		 * @param theTopicModel
		 */
		public void startCommentActivty(View theRootView,
				TopicModel theTopicModel) {
			// new intent
			Intent intent = new Intent(theRootView.getContext(),
					PostViewActivity.class);

			// make a gson object and serialize the topic
			Gson gsonTopic = new Gson();
			String gson = gsonTopic.toJson(theTopicModel);

			// send gson topic to the activity
			intent.putExtra("theTopicModel", gson);
			startActivity(intent);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onStart()
	 */

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		loginFlow();

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
			return true;
		case R.id.action_sortScore:
			PostListController.setSort(PostListController.SORT_SCORE);
			return true;
		case R.id.action_sortDefault:
			PostListController.setSort(PostListController.SORT_DEFAULT);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	protected void newPost() {
		Intent intent = new Intent(this, EditTopicActivity.class);
		startActivity(intent);
	}

	protected void startSettingsActivity() {
		Intent intent = new Intent(this, SettingsActivity.class);
		startActivity(intent);
	}
}
