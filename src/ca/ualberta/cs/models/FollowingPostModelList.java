package ca.ualberta.cs.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.OverlappingFileLockException;
import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import ca.ualberta.cs.providers.GeoChanGsonNetworked;

import com.google.gson.Gson;

/*
 * A followed post model is a post model that has been marked as favorite or read later. These
 * models will not be networked, and only saved to the device.
 */
abstract public class FollowingPostModelList<T extends PostModel> extends
		PostModelList<T> implements UpdateableListInterface {

	private ArrayList<UpdatePackage<T>> updatedPackages = new ArrayList<UpdatePackage<T>>();

	private final class SaveThread extends Thread {
		private final T[] cloned;

		private SaveThread(T[] cloned) {
			this.cloned = cloned;
		}

		@Override
		public void run() {
			synchronized (SaveThread.class) {
				performSave();
			}
		}

		private void performSave() {
			try {
				Gson gson = GeoChanGsonNetworked.getGson();

				String FILENAME = getFilenameString();
				FileOutputStream fos = applicationContext.openFileOutput(
						FILENAME, Context.MODE_PRIVATE);
				fos.getChannel().lock();
				OutputStreamWriter osw = new OutputStreamWriter(fos);

				gson.toJson(cloned, osw);

				osw.close();
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException ee) {
				// TODO Auto-generated catch block
				ee.printStackTrace();
			} catch (OverlappingFileLockException eee) {
				eee.printStackTrace();
			}
		}
	}

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
	 * Adds in special variables to model
	 * @param thePost
	 * @return thePost
	 */
	public abstract T specializePost(T thePost);

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
		final T[] cloned = arrayListToArray().clone();

		new SaveThread(cloned).start();
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

			// If there was an error reading, then we should just save over it.
			if (thePrimative == null) {
				super.setArrayList(new ArrayList<T>());
				save();
				return;
			}

			for (int i = 0; i < thePrimative.length; i++) {
				T theModel = specializePost(thePrimative[i]);
				dataThatLoaded.add(theModel);
			}

			// Load the data into the object
			super.setArrayList(dataThatLoaded);
		} catch (FileNotFoundException e) {
			// File was not found! Create it!
			super.setArrayList(new ArrayList<T>());
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Delete the contents of the list from disk
	 */
	public void delete() {
		setArrayList(new ArrayList<T>());
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

	/**
	 * Gets the post id's to be updated
	 * 
	 * @return
	 */
	public ArrayList<UpdatePackage<T>> getUpdateablePackages() {
		ArrayList<UpdatePackage<T>> theIds = new ArrayList<UpdatePackage<T>>();

		for (PostModel post : getArrayList()) {
			UpdatePackage<T> aPackage = new UpdatePackage<T>(
					post.getQualifyingId(), post.getId());

			theIds.add(aPackage);
		}

		return theIds;
	}

	/**
	 * @param updatedPackages
	 *            the updatedPackages to set
	 */
	public void setUpdatedPackages(ArrayList<UpdatePackage<T>> updatedPackages) {
		this.updatedPackages = updatedPackages;

		performPostUpdate();
	}

	public void performPostUpdate() {
		Iterator<UpdatePackage<T>> iterator = this.updatedPackages.iterator();

		while (iterator.hasNext()) {
			UpdatePackage<T> aPackage = iterator.next();

			T theModel = aPackage.getTheUpdatedModel();

			super.update(theModel);
		}

		// Save when done
		save();
	}
}
