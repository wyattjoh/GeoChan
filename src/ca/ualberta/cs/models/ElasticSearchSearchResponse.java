package ca.ualberta.cs.models;

// Credit: https://github.com/rayzhangcl/ESDemo

import java.util.ArrayList;
import java.util.Collection;

public class ElasticSearchSearchResponse<T> {
    int took;
    boolean timed_out;
    transient Object _shards;
    Hits<T> hits;
    boolean exists;
    String error = "";
    public String getError() {
    	return error;
    }
    public Boolean hasError() {
    	return error.length() > 0;
    }
    public Collection<ElasticSearchResponse<T>> getHits() {
        return hits.getHits();        
    }
    public Collection<T> getSources() {
        Collection<T> out = new ArrayList<T>();
        for (ElasticSearchResponse<T> essrt : getHits()) {
            out.add( essrt.getSource() );
        }
        return out;
    }
    @Override
	public String toString() {
        return (super.toString() + ":" + took + "," + _shards + "," + exists + ","  + hits);     
    }
}
