package jsjh.king.com.jsdandroidn.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import jsjh.king.com.salesmanapp.common.Constant;
import jsjh.king.com.salesmanapp.global.MyApplication;


//TODO: Auto-generated Javadoc

/**
* 描述：保存到 SharedPreferences 的数据.
*/
public class SpUtil {

	private static final String SHARED_PATH = "app_share";

	public static SharedPreferences getDefaultSharedPreferences() {
		return MyApplication.getContext().getSharedPreferences(SHARED_PATH, Context.MODE_PRIVATE);
	}

	
	public static void putInt(String key, int value) {
		SharedPreferences sharedPreferences = getDefaultSharedPreferences();
		Editor edit = sharedPreferences.edit();
		edit.putInt(key, value);
		edit.commit();
	}

	public static int getInt(String key) {
		SharedPreferences sharedPreferences = getDefaultSharedPreferences();
		return sharedPreferences.getInt(key, -1);
	}
	
	public static void putString(String key, String value) {
		SharedPreferences sharedPreferences = getDefaultSharedPreferences();
		Editor edit = sharedPreferences.edit();
		edit.putString(key, value);
		edit.commit();
	}

	public static String getString(String key) {
		SharedPreferences sharedPreferences = getDefaultSharedPreferences();
		return sharedPreferences.getString(key, "");
	}
	
	public static void putBoolean(String key, boolean value) {
		SharedPreferences sharedPreferences = getDefaultSharedPreferences();
		Editor edit = sharedPreferences.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}

	public static boolean getBoolean(String key, boolean defValue) {
		SharedPreferences sharedPreferences = getDefaultSharedPreferences();
		return sharedPreferences.getBoolean(key,defValue);
	}
	
	public static void putLong(String key, long value) {
		SharedPreferences sharedPreferences = getDefaultSharedPreferences();
		Editor edit = sharedPreferences.edit();
		edit.putLong(key, value);
		edit.commit();
	}
	
	public static long getLong(String key) {
		SharedPreferences sharedPreferences = getDefaultSharedPreferences();
		return sharedPreferences.getLong(key, -1L);
	}

	public static void putString(String path,String key,String value){
		SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(path, Context.MODE_PRIVATE);
		Editor edit = sharedPreferences.edit();
		edit.putString(key, value);
		edit.commit();
	}

	public static String getString(String path,String key){
		SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(path, Context.MODE_PRIVATE);
		return sharedPreferences.getString(key,"");
	}

	public static void clearData(String path) {
		SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(path, Context.MODE_PRIVATE);
		sharedPreferences.edit().clear().commit();
	}
	
	public static void clearData() {
		getDefaultSharedPreferences().edit().clear().commit();
	}


	public static void remove(String key) {
		SharedPreferences sharedPreferences = getDefaultSharedPreferences();
		Editor edit = sharedPreferences.edit();
		edit.remove(key);
		edit.commit();
	}

	public static void removeUser() {
		SharedPreferences sharedPreferences = getDefaultSharedPreferences();
		Editor edit = sharedPreferences.edit();
		edit.remove(Constant.USER_CNAME);
		edit.remove(Constant.USER_COMPANY_ID);
		edit.remove(Constant.USER_COMPANY_NO);
		edit.remove(Constant.USER_COMPANY_NAME);
		edit.remove(Constant.USER_SCID);
		edit.remove(Constant.USER_TOKEN);
		edit.remove(Constant.USER_EXPRIES);
		edit.remove(Constant.USER_NO);
		edit.commit();
	}
}
