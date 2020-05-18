package com.congda.baselibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**


 /**
 * created by lbw at 17/3/1
 * 日期工具
 */
public class IMTimeUtils {
    /**
     * 时间戳转时间
     */
    public static String stampToTime(String stamp,String type) {
        if(type==null){
            type="yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(type);
        long lt = new Long(stamp);
        Date date = new Date(lt);
        String res = simpleDateFormat.format(date);
        return res;
    }

}
