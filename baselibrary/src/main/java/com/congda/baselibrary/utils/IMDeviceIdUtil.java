package com.congda.baselibrary.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;


public class IMDeviceIdUtil {

    @SuppressLint("MissingPermission")
    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            TelephonyManager tm = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = null;
            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                device_id = tm.getDeviceId();
            }
            String mac = null;
            FileReader fstream = null;
            try {
                fstream = new FileReader("/sys/class/net/wlan0/address");
            } catch (FileNotFoundException e) {
                fstream = new FileReader("/sys/class/net/eth0/address");
            }
            BufferedReader in = null;
            if (fstream != null) {
                try {
                    in = new BufferedReader(fstream, 1024);
                    mac = in.readLine();
                } catch (IOException e) {
                } finally {
                    if (fstream != null) {
                        try {
                            fstream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            json.put("mac", mac);
            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }
            if (TextUtils.isEmpty(device_id)) {
                device_id = Settings.Secure.getString(context.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
            }
            json.put("device_id", device_id);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取设备的信息
     * @param context
     * @param permission
     * @return
     */
    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                Class<?> clazz = Class.forName("android.content.Context");
                Method method = clazz.getMethod("checkSelfPermission", String.class);
                int rest = (Integer) method.invoke(context, permission);
                if (rest == PackageManager.PERMISSION_GRANTED) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Exception e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }
    private static String sDeviceID;


    /** 获取设备Id */
    public static String getDeviceID(Context context) {
        if ((sDeviceID != null) && (!"unknown".equals(sDeviceID)))
            return sDeviceID;
        try {
            @SuppressLint("WrongConstant")
            TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                sDeviceID = tm.getDeviceId();
                if ((sDeviceID == null) || (sDeviceID.length() == 0)) {
                    sDeviceID = getAndroidId(context);
                }
                return sDeviceID;
            }
            sDeviceID = tm.getDeviceId();
            if ((sDeviceID == null) || (sDeviceID.length() == 0)) {
                sDeviceID = getAndroidId(context);
            }
            return sDeviceID;
        }
        catch (Exception e) {
        }
        sDeviceID = getAndroidId(context);
        return sDeviceID;
    }

    /** 获取安卓Id */
    public static String getAndroidId(Context context)
    {
        String androidid = "unknown";
        try
        {
            androidid = Settings.Secure.getString(context.getContentResolver(), "android_id");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if ((androidid == null) || (androidid.length() == 0)) { return "unknown"; }
        return androidid;
    }
}