package ca.ualberta.cs.models;

// Credit: https://github.com/rayzhangcl/ESDemo

public class ElasticSearchResponse<T> {
    String _index;
    String _type;
    String _id;
    int _version;
    boolean exists;
    T _source;
    double max_score;
    public T getSource() {
        return _source;
    }
    public String getId() {
    	return _id;
    }
}
