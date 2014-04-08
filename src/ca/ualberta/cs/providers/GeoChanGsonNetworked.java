/**
 * 
 */
package ca.ualberta.cs.providers;

import java.lang.reflect.Type;

import ca.ualberta.cs.models.UserModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Manages the networked version of GeoChanGson
 * 
 * @author wyatt
 * 
 */
public class GeoChanGsonNetworked extends GeoChanGson {
	protected static GeoChanGsonNetworked singleton = null;

	private static class UserModelTypeAdapter implements
			JsonSerializer<UserModel>, JsonDeserializer<UserModel> {

		@Override
		public UserModel deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {

			// Load the object
			final JsonObject jsonObject = json.getAsJsonObject();
			// Get the UserName element
			final JsonElement jsonUserName = jsonObject.get("userName");

			// Check that it's there
			if (jsonUserName == null) {
				// Else, freak out
				throw new JsonParseException(
						"No userName field found on object: "
								+ jsonObject.toString());
			}

			// Get the double for the jsonUserName
			final String userName = jsonUserName.getAsString();

			// Get the lat element
			final JsonElement jsonUserHash = jsonObject.get("userHash");

			// Check that it's there
			if (jsonUserHash == null) {
				// Else, freak out
				throw new JsonParseException(
						"No userHash field found on object: "
								+ jsonObject.toString());
			}

			// Get the double for the jsonUserName
			final String userHash = jsonUserHash.getAsString();

			UserModel newUser = new UserModel(userName);
			newUser.setUserHash(userHash);

			return newUser;
		}

		@Override
		public JsonElement serialize(UserModel src, Type typeOfSrc,
				JsonSerializationContext context) {

			JsonObject result = new JsonObject();
			result.add("userName", new JsonPrimitive(src.getUserName()));
			result.add("userHash", new JsonPrimitive(src.getUserHash()));

			return result;
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.ualberta.cs.providers.GeoChanGson#createGsonBuilder()
	 */
	@Override
	protected GsonBuilder createGsonBuilder() {
		GsonBuilder gsonBuilder = super.createGsonBuilder();

		// UserModel objects
		gsonBuilder.registerTypeHierarchyAdapter(UserModel.class,
				new UserModelTypeAdapter());

		return gsonBuilder;
	}

	/**
	 * Get the gson object
	 * 
	 * @return the GeoChanGsonNetworked object
	 */
	public static Gson getGson() {
		if (singleton == null) {
			singleton = new GeoChanGsonNetworked();
		}
		return singleton.gson;
	}
}
