package ca.ualberta.cs.providers;

import java.lang.reflect.Type;
import java.util.Date;

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

	private GeoChanGson() {
		// Get builder
		GsonBuilder gsonBuilder = new GsonBuilder();

		// Register an adapter to manage the date types as long values
		gsonBuilder.registerTypeAdapter(Date.class,
				new JsonDeserializer<Date>() {
					@Override
					public Date deserialize(JsonElement arg0, Type arg1,
							JsonDeserializationContext arg2)
							throws JsonParseException {
						// TODO Auto-generated method stub
						return new Date(arg0.getAsJsonPrimitive().getAsLong());
					}
				});
		
		gsonBuilder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {

			@Override
			public JsonElement serialize(Date arg0, Type arg1,
					JsonSerializationContext arg2) {
				return new JsonPrimitive(arg0.getTime());
			}
		});

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
