/**
 * 
 */
package ca.ualberta.cs.providers;

/**
 * @author wyatt
 *
 * Provides interface to remote and proxies objects
 *
 */
public class ElasticSearchProvider {
	private static ElasticSearchProvider singleton = null;
	
	public static ElasticSearchProvider getProvider() {
		if (singleton == null) {
			singleton = new ElasticSearchProvider();
		}
		
		return singleton;
	}
	
	
}
