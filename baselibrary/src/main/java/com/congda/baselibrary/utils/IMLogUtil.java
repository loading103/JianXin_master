package com.congda.baselibrary.utils;

import android.text.TextUtils;
import android.util.Log;


/**
 * Created by rwz on 2016/12/9.
 */

public class IMLogUtil {

    private static final String TAG = "tag";
    private static final String SPACE = "   ";
    private static final String E_MSG_BEF = "【";
    private static final String E_MSG_AFT = "】";

    //    private static final boolean isDebug = IMSConfig.NEED_LOG;
    private static final boolean isDebug = true;
    public static void d(Object msg) {
        if (isDebug) {
            Log.d(TAG, "" + msg);
        }
    }

    public static void tag(String tag, Object... msg) {
        if (isDebug) {
            StringBuffer sb = new StringBuffer();
            if (msg != null) {
                for (Object o : msg) {
                    sb.append(o + ",");
                }
            }
            if (!TextUtils.isEmpty(sb) && sb.length() > 0) {
                String print = sb.toString().substring(0,sb.toString().length() - 1);
                Log.d(tag, print);
            }
        }
    }

    public static void d(Object... msg) {
        tag(TAG, msg);
    }
    public static void e(Object tips) {
        if (isDebug) {
            Log.e(TAG, tips + "");
        }
    }

    public static void e(Object tips ,Object... msg) {
        if (isDebug) {
            StringBuffer sb = new StringBuffer();
            if (msg != null) {
                for (Object o : msg) {
                    sb.append(o + ", ");
                }
            }
            String s = sb.toString();
            if (!TextUtils.isEmpty(s) && s.length() > 0) {
                String print = tips + E_MSG_BEF + s.substring(0, s.length() - 1) + E_MSG_AFT;
                Log.e(TAG, "║" + SPACE + "\n║" + tips + SPACE + print);
            }
        }
    }

}
