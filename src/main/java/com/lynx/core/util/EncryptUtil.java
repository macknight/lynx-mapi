package com.lynx.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author zhufeng.liu
 * @version 13-10-26 下午2:17.
 */
public class EncryptUtil {

	private static Gson gson = null;

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.excludeFieldsWithoutExposeAnnotation();
		gson = gsonBuilder.create();
	}

	public static String format(Object obj) {
		return gson.toJson(obj);
	}
}
