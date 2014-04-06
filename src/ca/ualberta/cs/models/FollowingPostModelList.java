package ca.ualberta.cs.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import ca.ualberta.cs.providers.GeoChanGsonOffline;

import com.google.gson.Gson;

/*
 * A followed post model is a post model that has been marked as favorite or read later. These
 * models will not be networked, and only saved to the device.
 */
abstract public class FollowingPostModelList<T extends PostModel> extends
		PostModelList<T> implements UpdateableListInterface {

	private Context applicationContext;

	/**
	 * @return string for filename to save as (generally the class name)
	 */
	abstract protected String getFilenameString();

	/**
	 * @return transformed arrayList to a Java array typed correctly
	 */
	abstract protected T[] arrayListToArray();

	/**
	 * Loads the JSON file from the ISR
	 * 
	 * @param isr
	 *            reader for the JSON file
	 * @return proper typed java array
	 */
	abstract protected T[] inputStreaReaderToArray(InputStreamReader isr);

	protected FollowingPostModelList(Context applicationContext) {
		this.applicationContext = applicationContext;
		load();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.models.UpdateableListInterface#updateFromNetwork()
	 */
	@Override
	public void updateFromNetwork() {
		// TODO Auto-generated method stub

	}

	/**
	 * Saves the current state to the disk
	 */
	private void save() {
		Gson gson = GeoChanGsonOffline.getGson();

		T[] dataToSave = arrayListToArray();

		try {
			String FILENAME = getFilenameString();
			FileOutputStream fos = applicationContext.openFileOutput(FILENAME,
					Context.MODE_PRIVATE);
			OutputStreamWriter osw = new OutputStreamWriter(fos);

			gson.toJson(dataToSave, osw);
			Log.w("FollowingPostModel",
					"Current Saved: " + gson.toJson(dataToSave));

			osw.close();
			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.models.PostModelList#remove(int)
	 */
	@Override
	public void remove(int position) {
		// TODO Auto-generated method stub
		super.remove(position);

		save();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.ualberta.cs.models.PostModelList#remove(ca.ualberta.cs.models.PostModel
	 * )
	 */
	@Override
	public void remove(T theModel) {
		// TODO Auto-generated method stub
		super.remove(theModel);

		save();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.models.PostModelList#update(int,
	 * ca.ualberta.cs.models.PostModel)
	 */
	@Override
	public void update(int position, T theModel) {
		// TODO Auto-generated method stub
		super.update(position, theModel);

		save();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.ualberta.cs.models.PostModelList#setArrayList(java.util.ArrayList)
	 */
	@Override
	public void setArrayList(ArrayList<T> postModelArrayList) {
		// TODO Auto-generated method stub
		super.setArrayList(postModelArrayList);

		save();
	}

	/**
	 * Loads the state from the disk to memory
	 */
	private void load() {
		ArrayList<T> dataThatLoaded = new ArrayList<T>();

		try {
			String FILENAME = getFilenameString();
			FileInputStream fis = applicationContext.openFileInput(FILENAME);
			InputStreamReader isr = new InputStreamReader(fis);

			T[] thePrimative = inputStreaReaderToArray(isr);

			isr.close();
			fis.close();

			for (int i = 0; i < thePrimative.length; i++) {
				dataThatLoaded.add(thePrimative[i]);
			}

			// Load the data into the object
			super.setArrayList(dataThatLoaded);
		} catch (FileNotFoundException e) {
			// File was not found! Create it!
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.ualberta.cs.models.PostModelList#add(ca.ualberta.cs.models.PostModel)
	 */
	@Override
	public void add(T theModel) {
		// TODO Auto-generated method stub
		super.add(theModel);

		save();
	}
}
