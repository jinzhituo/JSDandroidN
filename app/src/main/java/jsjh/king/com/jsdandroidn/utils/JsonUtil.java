package jsjh.king.com.jsdandroidn.utils;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

	/**
	 * @param result
	 * @return 服务器返回是否成功
	 */
	public static boolean isSuccess(String result) {
		try {
			JSONObject jsonObject = new JSONObject(result);
			if (jsonObject.optInt("code") == 200)
				return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 * @param result
	 * @return 服务器返回是否成功
	 */
	public static int getCode(String result) {
		try {
			JSONObject jsonObject = new JSONObject(result);
			return jsonObject.optInt("code");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Integer.MAX_VALUE;
	}

	/**
	 * 把一个map变成json字符串
	 * 
	 * @param map
	 * @return
	 */
	public static String parseMapToJson(Map<?, ?> map) {
		try {
			Gson gson = new Gson();
			return gson.toJson(map);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 把一个bean变成json字符串
	 *
	 * @return
	 */
	public static String beanToJson(Object obj) {
		try {
			Gson gson = new Gson();
			return gson.toJson(obj);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 把一个bean变成json字符串
	 *
	 * @return
	 */
	/**
	 * list to json
	 *
	 * @param list
	 * @return
	 */
	public static String listToJson(List list) {
		try {
			Gson gson = new Gson();
			String json = gson.toJson(list);
			return json;
		} catch (Exception e) {
		}
		return null;
	}




	/**
	 * 把一个json字符串变成对象
	 * 
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> T parseJsonToBean(String json, Class<T> cls) {
		Gson gson = new Gson();
		T t = null;
		try {
			t = gson.fromJson(json, cls);
		} catch (Exception e) {
			Log.e("dbg", e.toString());
		}
		return t;
	}

	/**
	 * 把json字符串变成map
	 * 
	 * @param json
	 * @return
	 */
	public static HashMap<String, Object> parseJsonToMap(String json) {
		Gson gson = new Gson();
		Type type = new TypeToken<HashMap<String, Object>>() {
		}.getType();
		HashMap<String, Object> map = null;
		try {
			map = gson.fromJson(json, type);
		} catch (Exception e) {
		}
		return map;
	}

	/**
	 * 把json字符串变成集合 params: new TypeToken<List<yourbean>>(){}.getType(),
	 * 
	 * @param json
	 * @param type
	 *            new TypeToken<List<yourbean>>(){}.getType()
	 * @return
	 */
	public static List<?> parseJsonToList(String json, Type type) {
		Gson gson = new Gson();
		List<?> list = gson.fromJson(json, type);
		return list;
	}

	/**
	 * 获取json串中某个字段的值，注意，只能获取同一层级的value
	 * 
	 * @param json
	 * @param key
	 * @return
	 */
	public static String getFieldValue(String json, String key) {
		if (TextUtils.isEmpty(json))
			return null;
		if (!json.contains(key))
			return "";
		JSONObject jsonObject = null;
		String value = null;
		try {
			jsonObject = new JSONObject(json);
			value = jsonObject.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}

}
