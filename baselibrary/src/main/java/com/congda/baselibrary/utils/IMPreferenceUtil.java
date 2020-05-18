package com.congda.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class IMPreferenceUtil {
	/**
	 * 保存Preference的name
	 */
	public static final String PREFERENCE_NAME = "saveInfo_user";


	private static SharedPreferences mSharedPreferences;
	private static IMPreferenceUtil mPreferencemManager;
	private static SharedPreferences.Editor editor;
	
	private static String KEY_IS_FIRST = "KEY_IS_FIRST";
	private static String KEY_IS_FIRST_ACTIVITY = "KEY_IS_FIRST_ACTIVITY";
	private static String KEY_IS_FIRST_CONMUNICATE = "KEY_IS_FIRST_CONMUNICATE";
	private static String KEY_IS_FIRST_ACTIVITYINFO = "KEY_IS_FIRST_ACTIVITYINFO";


	private IMPreferenceUtil(Context cxt) {
		mSharedPreferences = cxt.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		editor = mSharedPreferences.edit();
	}

	public static synchronized void init(Context cxt){
	    if(mPreferencemManager == null){
	        mPreferencemManager = new IMPreferenceUtil(cxt);
	    }
	}
	
	/**
	 * 单例模式，获取instance实例
	 * 
	 * @return
	 */
	public synchronized static IMPreferenceUtil getInstance() {
		if (mPreferencemManager == null) {
			throw new RuntimeException("please init first!");
		}
		
		return mPreferencemManager;
	}
	
	/**
	 * @param
	 */
	public static void setPreference_Float(String key, Float aFloat) {
		editor.putFloat(key, aFloat);
		editor.apply();
	}
	
	/**
	 * @return
	 */
	public static float getPreference_Float(String key, Float aFloat) {
		return mSharedPreferences.getFloat(key,aFloat);
	}
	
	/**
	 * @return
	 */
	public static String getPreference_String(String key, String value) {
		if(mSharedPreferences==null){
			return null;
		}
		return mSharedPreferences.getString(key,value);
	}
	/**
	 * @param
	 */
	public static void setPreference_String(String key, String value) {
		editor.putString(key, value);
		editor.apply();

	}
	/**
	 * @return
	 */
	public static boolean getPreference_Boolean(String key, boolean value) {
		return mSharedPreferences.getBoolean(key,value);
	}
	/**
	 * @param
	 */
	public static void setPreference_Boolean(String key, boolean value) {
		editor.putBoolean(key, value);
		editor.apply();
	}


	/**
	 */
	public static void clean() {
		editor.clear();
		editor.apply();
		editor.commit();
	}
}
