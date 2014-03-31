package ca.ualberta.cs.providers;

public enum ElasticSearchProviderConstants {
	/*
	 * Query types
	 */

	// Add a topic
	TYPE_ADD_TOPIC,

	// Update a topic
	TYPE_UPDATE_TOPIC,

	// Load topics into the indicated controller
	TYPE_LOAD_TOPICS,

	/*
	 * Sorting/Ordering types
	 */
	SORT_DATE, SORT_PROXY, SORT_LATEST_GREATEST
};
