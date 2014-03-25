package ca.ualberta.cs.providers;

import java.lang.reflect.Type;
import java.util.Date;

import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GeoChanGson {
	private static GeoChanGson singleton = null;
	private Gson gson;

	// https://gist.github.com/orip/3635246
	// Using Android's base64 libraries. This can be replaced with any base64
	// library.
	private static class ByteArrayToBase64TypeAdapter implements
			JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
		@Override
		public byte[] deserialize(JsonElement json, Type typeOfT,
				JsonDeserializationContext context) throws JsonParseException {
			return Base64.decode(json.getAsString(), Base64.NO_WRAP);
		}

		@Override
		public JsonElement serialize(byte[] src, Type typeOfSrc,
				JsonSerializationContext context) {
			return new JsonPrimitive(Base64.encodeToString(src, Base64.NO_WRAP));
		}
	}

	/*
	 * JsonSerializer<Date>, JsonDeserializer<Date>
	 */
	private static class DateToUnixTimestampTypeAdapter implements
			JsonSerializer<Date>, JsonDeserializer<Date> {

		@Override
		public Date deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			return new Date(arg0.getAsJsonPrimitive().getAsLong());
		}

		@Override
		public JsonElement serialize(Date arg0, Type arg1,
				JsonSerializationContext arg2) {
			return new JsonPrimitive(arg0.getTime());
		}

	}

	private GeoChanGson() {
		// Get builder
		GsonBuilder gsonBuilder = new GsonBuilder();

		// Date objects
		gsonBuilder.registerTypeHierarchyAdapter(Date.class,
				new DateToUnixTimestampTypeAdapter());

		// Byte objects
		gsonBuilder.registerTypeHierarchyAdapter(byte[].class,
				new ByteArrayToBase64TypeAdapter());

		// Generate gson object
		gson = gsonBuilder.create();
	}

	public static Gson getGson() {
		if (singleton == null) {
			singleton = new GeoChanGson();
		}

		return singleton.gson;
	}
}
