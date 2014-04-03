package ca.ualberta.cs.models;

// Credit: https://github.com/rayzhangcl/ESDemo

import java.util.Collection;

public class Hits<T> {
	int total;
	double max_score;
	Collection<ElasticSearchResponse<T>> hits;

	public Collection<ElasticSearchResponse<T>> getHits() {
		return hits;
	}

	@Override
	public String toString() {
		return (super.toString() + "," + total + "," + max_score + "," + hits);
	}
}