package ca.ualberta.cs.models;

public class ElasticSearchMgetDoc<T> {
	String _index;
	String _type;
	String _id;
	int _version;
	Boolean exists;
	T _source;

	public T getSource() {
		return (exists) ? _source : null;
	}

	public int getVersion() {
		return (exists) ? _version : null;
	}
}
