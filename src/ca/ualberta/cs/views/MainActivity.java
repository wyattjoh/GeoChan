package ca.ualberta.cs.views;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import ca.ualberta.cs.R;
import ca.ualberta.cs.controllers.NetworkInterfaceController;
import ca.ualberta.cs.controllers.PostListController;
import ca.ualberta.cs.models.ActiveUserModel;
import ca.ualberta.cs.models.FavoriteCommentModelList;
import ca.ualberta.cs.models.FavoriteTopicModelList;
import ca.ualberta.cs.models.ReadLaterCommentModelList;
import ca.ualberta.cs.models.ReadLaterTopicModelList;
import ca.ualberta.cs.models.TopicModelList;
import ca.ualberta.cs.providers.LocationProvider;

/**
 * 
 * Lists recent topics, favorite topics and comments, and read later topics and
 * comments.
 * 
 * @author wyatt
 */
public class MainActivity extends FragmentActivity implements
		LocationUpdatedInterface {

	/*
	 * Intent request codes
	 */
	private static final int LOGIN_ACTIVITY = 1;
	private static final int GET_LOCATION = 2;

	/*
	 * Post sorting location
	 */
	private Location theSortLocation = new Location("");

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		private ArrayList<Fragment> fragments;

		// TODO: Add documentation
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);

			this.fragments = new ArrayList<Fragment>();
		}

		// TODO: Add documentation
		@Override
		public int getCount() {
			// Show 3 total pages.
			return MainActivityFragmentComponent.getSize();
		}

		// TODO: Add documentation
		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page
			Fragment fragment = new MainActivityFragment();
			Bundle args = new Bundle();
			args.putInt(MainActivityFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);

			// Add to list of fragments
			fragments.add(fragment);

			return fragment;
		}

		// TODO: Add documentation
		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			MainActivityFragmentComponent theComponent = MainActivityFragmentComponent
					.getComponentForPosition(position + 1);
			if (theComponent != null) {
				return theComponent.getTitle().toUpperCase(l);
			} else {
				return null;
			}
		}

		// TODO: Add documentation
		public void notifyNetworkStateChanged() {
			for (Fragment fragment : fragments) {
				// Update Fragments
				MainActivityFragment theTopicListActivityFragment = (MainActivityFragment) fragment;
				theTopicListActivityFragment.notifyNetworkStateChanged();
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
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == LOGIN_ACTIVITY) {
			if (resultCode == Activity.RESULT_OK) {
				loginFlow();
			}
		} else if (requestCode == GET_LOCATION) {
			if (resultCode == RESULT_OK) {
				try {
					Double retLatitude = data.getDoubleExtra("extLatitude", 0);
					Double retLongitude = data
							.getDoubleExtra("extLongitude", 0);

					this.theSortLocation.setLatitude(retLatitude);
					this.theSortLocation.setLongitude(retLongitude);
					TopicModelList.getInstance().sortByProximityTo(
							this.theSortLocation);

				} catch (Exception e) {
					Toast.makeText(
							this,
							"FAILED "
									+ Double.toString(data.getDoubleExtra(
											"extLatitude", 0))
									+ Double.toString(data.getDoubleExtra(
											"extLongitude", 0)),
							Toast.LENGTH_LONG).show();
				}
			}
			if (resultCode == RESULT_CANCELED) {
				this.theSortLocation = ActiveUserModel.getInstance().getUser()
						.getLocation();
				Toast.makeText(this,
						"Sorting by proximity to current location",
						Toast.LENGTH_LONG).show();
				PostListController.setSort(PostListController.SORT_PROXIMITY);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
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
				notifyNetworkStateChanged();
			}
		};

		context.registerReceiver(this.connectionBroadcastReceiver,
				new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));

		// Must be first thing that is started, sets up contexts
		createSharedSingletons(getApplicationContext());

		LocationProvider.getInstance(getApplicationContext())
				.registerForLocationUpdates(this);
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

		LocationProvider.getInstance(null).unregisterForLocationUpdates(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.findItem(R.id.readLaterButton).setVisible(false);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.cellActiveArea:
			newPost();
			return true;
		case R.id.action_help:
			startHelpActivity();
			return true;
		case R.id.action_sortDate:
			PostListController.setSort(PostListController.SORT_DATE);
			return true;
		case R.id.action_sortScore:
			PostListController.setSort(PostListController.SORT_SCORE);
			return true;
		case R.id.action_sortProximity:
			PostListController.setSort(PostListController.SORT_PROXIMITY);
			return true;
		case R.id.action_sortProximityTo:
			sortPostsByProximityTo();
			return true;
		case R.id.action_sortLatestGreatest:
			PostListController.setSort(PostListController.SORT_LATEST_GREATEST);
			return true;
		case R.id.action_sortPicture:
			PostListController.setSort(PostListController.SORT_PICTURE);
			return true;
		case R.id.action_logout:
			logout();
			return true;
		case R.id.refreshButton:
			NetworkInterfaceController.refreshPosts();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		// Perform the login flow process
		loginFlow();
	}

	/**
	 * Creates shared singletons requiring initialization
	 * 
	 * @param applicationContext
	 *            The application context for the main activity
	 */
	private void createSharedSingletons(Context applicationContext) {
		// Create Active User
		ActiveUserModel.createInstance(applicationContext);

		// Create Favorites List
		FavoriteTopicModelList.createInstance(applicationContext);
		FavoriteCommentModelList.createInstance(applicationContext);

		// Create Read Later list
		ReadLaterTopicModelList.createInstance(applicationContext);
		ReadLaterCommentModelList.createInstance(applicationContext);

		// Create Location Provider
		LocationProvider.getInstance(applicationContext);
	}

	/**
	 * Refreshes fragments inside the sectionPagerAdapter
	 */
	protected void notifyNetworkStateChanged() {
		// Update all fragments
		sectionsPagerAdapter.notifyNetworkStateChanged();
	}

	/**
	 * Starts a new activity to create a new post
	 */
	protected void newPost() {
		Intent intent = new Intent(this, EditTopicActivity.class);
		intent.putExtra(EditPostActivity.IS_NEW, true);

		startActivity(intent);
	}

	/**
	 * Selects the location to sort relative to
	 */
	protected void sortPostsByProximityTo() {
		Intent intent = new Intent(this, LocationActivity.class);
		intent.putExtra("previousLocation", ActiveUserModel.getInstance()
				.getUser().getLocation());
		startActivityForResult(intent, GET_LOCATION);
	}

	/**
	 * Starts the Help Activity
	 */
	protected void startHelpActivity() {
		Intent intent = new Intent(this, HelpViewActivity.class);
		startActivity(intent);
	}

	/**
	 * Allows the user to log out
	 */
	protected void logout() {
		ActiveUserModel userController = ActiveUserModel.getInstance();
		userController.performLogout();

		Intent intent = new Intent(this, LoginActivity.class);
		startActivityForResult(intent, LOGIN_ACTIVITY);
	}

	/**
	 * Verifies that the user is logged in, if not, starts the login activity
	 */
	private void loginFlow() {
		ActiveUserModel userController = ActiveUserModel.getInstance();

		if (userController.isLoggedIn()) {
			// continue
		} else {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivityForResult(intent, LOGIN_ACTIVITY);
		}
	}

	@Override
	public void locationWasUpdated(Location theNewLocation) {
		NetworkInterfaceController.refreshPosts();
	}
}
