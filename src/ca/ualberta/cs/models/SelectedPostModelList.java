package ca.ualberta.cs.models;

/**
 * 
 * @author wyatt
 *
 * Stores the last list to be selected
 *
 * @param <T>
 */
abstract public class SelectedPostModelList<T extends PostModel> {
	protected PostModelList<T> theList = null;
	
	protected void setSelectedPostModelList(PostModelList<T> theList) {
		this.theList = theList;
	}
	
	protected PostModelList<T> getSelectedPostModelList() {
		return this.theList;
	}
}
