package com.congda.baselibrary.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.congda.baselibrary.app.BaseApplication;
import com.congda.baselibrary.app.IMSConfig;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class IMSavePhotoUtil {

    /**
     * 把Bitmap保存成图片文件
     */
    public static  String saveBitmap(Bitmap bitmap,String filename) {
        String path = null;
        File dir = new File(IMSConfig.PICTURI_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String name="";
        if(TextUtils.isEmpty(filename)){
            name=IMTimeUtils.stampToTime(System.currentTimeMillis()+"","MM-dd HH:mm:ss") + ".jpg";
        }else {
            name=filename + ".jpg";
        }
        File imageFile = new File(dir,name);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            path = imageFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return path;
    }

    /**
     * 保存drawable下的图片到绝对路径下
     * filename为空  用时间错保存
     */
    public static  void saveDrawableIcon( int Res,String fileName) {
        Resources res = BaseApplication.getContext().getResources();
        BitmapDrawable d = (BitmapDrawable) res.getDrawable(Res);
        Bitmap img = d.getBitmap();
        saveBitmap(img,fileName);
    }
    /**
     * 将图片(url)保存到本地相册
     */
    public static void saveUrlToPhoto(final Context context, final String imgUrl,String filename) {
        if (TextUtils.isEmpty(imgUrl)) {
            Toast.makeText(context, "未获取到图片", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name="";
                if(TextUtils.isEmpty(filename)){
                    name=IMTimeUtils.stampToTime(System.currentTimeMillis()+"","MM-dd HH:mm:ss") + ".jpg";
                }else {
                    name=filename + ".jpg";
                }
                File f = new File(IMSConfig.PICTURI_PATH, name);

                try {
                    // 保存图片
                    URL url = new URL(imgUrl);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(1000 * 6);
                    if (con.getResponseCode() == 200) {
                        InputStream inputStream = con.getInputStream();
                        byte[] b = getBytes(inputStream);
                        FileOutputStream fileOutputStream = new FileOutputStream(f);
                        fileOutputStream.write(b);
                        fileOutputStream.close();
                    } else {
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "图片保存失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                        return;
                    }

                    //把文件插入到系统图库
                    MediaStore.Images.Media.insertImage(context.getContentResolver(),
                            f.getAbsolutePath(), name, null);

                    // 通知图库更新
                    context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(f.getPath()))));

                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "图片保存至相册", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception e) {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "图片保存失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
    }

    /**
     * 将InputStream，转换为字节
     */
    public static byte[] getBytes(InputStream inputStream) throws Exception {
        byte[] b = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = -1;
        while ((len = inputStream.read(b)) != -1) {
            byteArrayOutputStream.write(b, 0, len);
        }
        byteArrayOutputStream.close();
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
