package com.metricstream.systemi.utils;

import com.google.gson.Gson;

/**
 * Utility for JSON data conversion
 */
public class GsonUtil {

	/**
	 * Method to convert json String to object
	 *
	 * @param <T> the generic type
	 * @param jsonString the json string
	 * @param classOfT the class of t
	 * @return the object the <T> object
	 */
	public static <T> Object readGson(String jsonString, Class<T> classOfT)
	{
		 Gson gson = new Gson();
		return gson.fromJson(jsonString, classOfT);
	}
	
	/**
	 * Method to convert the object to json string
	 *
	 * @param object the object
	 * @return the string
	 */
	public static String writeGson(Object object)
	{
		 Gson gson = new Gson();
		 return gson.toJson(object);
	}
	
}
