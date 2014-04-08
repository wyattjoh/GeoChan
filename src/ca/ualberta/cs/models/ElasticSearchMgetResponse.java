/**
 * 
 */
package ca.ualberta.cs.models;

import java.util.Collection;

/**
 * @author wyatt
 * 
 */
public class ElasticSearchMgetResponse<T> {
	Collection<ElasticSearchMgetDoc<T>> docs;

	public Collection<ElasticSearchMgetDoc<T>> getDocs() {
		return docs;
	}
}
