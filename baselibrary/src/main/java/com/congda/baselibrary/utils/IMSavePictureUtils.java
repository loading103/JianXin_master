package com.congda.baselibrary.utils;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.Transition;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import cc.shinichi.library.glide.FileTarget;
import cc.shinichi.library.tool.file.FileUtil;
import cc.shinichi.library.tool.file.SingleMediaScanner;
import cc.shinichi.library.tool.image.ImageUtil;
import cc.shinichi.library.tool.text.MD5Util;
import cc.shinichi.library.tool.ui.ToastUtil;

public class IMSavePictureUtils {
    /**
     * 保存url到本地
     */
    public static void downloadPicture(final Context context, final String url) {
        Glide.with(context).downloadOnly().load(url).into(new FileTarget() {
            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                super.onLoadStarted(placeholder);
                ToastUtil.getInstance()._short(context, "开始下载");
                super.onLoadStarted(placeholder);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                ToastUtil.getInstance()._short(context, "保存失败");
            }

            @SuppressLint("ObsoleteSdkInt")
            @Override
            public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                super.onResourceReady(resource, transition);

                // 传入的保存文件夹名
                final String downloadFolderName = "jinxin";
                // 保存的图片名称
                String name = "";
                try {
                    name = url.substring(url.lastIndexOf("/") + 1);
                    if (name.contains(".")) {
                        name = name.substring(0, name.lastIndexOf("."));
                    }
                    name = MD5Util.md5Encode(name);
                } catch (Exception e) {
                    e.printStackTrace();
                    name = System.currentTimeMillis() + "";
                }
                String mimeType = ImageUtil.getImageTypeWithMime(resource.getAbsolutePath());
                name = name + "." + mimeType;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    // 大于等于29版本的保存方法
                    ContentResolver resolver = context.getContentResolver();
                    // 设置文件参数到ContentValues中
                    ContentValues values = new ContentValues();
                    values.put(MediaStore.Images.Media.DISPLAY_NAME, name);
                    values.put(MediaStore.Images.Media.DESCRIPTION, name);
                    values.put(MediaStore.Images.Media.MIME_TYPE, "images/" + mimeType);
                    values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/" + downloadFolderName + "/");

                    Uri insertUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                    BufferedInputStream inputStream = null;
                    OutputStream os = null;
                    try {
                        inputStream = new BufferedInputStream(new FileInputStream(resource.getAbsolutePath()));
                        if (insertUri != null) {
                            os = resolver.openOutputStream(insertUri);
                        }
                        if (os != null) {
                            byte[] buffer = new byte[1024 * 4];
                            int len;
                            while ((len = inputStream.read(buffer)) != -1) {
                                os.write(buffer, 0, len);
                            }
                            os.flush();
                        }
                        ToastUtil.getInstance()._short(context, "保存成功");
                    } catch (IOException e) {
                        e.printStackTrace();
                        ToastUtil.getInstance()._short(context, "保存失败");
                    } finally {
                        try {
                            if (os != null) {
                                os.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    // 低于29版本的保存方法
                    final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + downloadFolderName + "/";

                    FileUtil.createFileByDeleteOldFile(path + name);
                    boolean result = FileUtil.copyFile(resource, path, name);
                    if (result) {
                        ToastUtil.getInstance()._short(context, "保存成功");
                        new SingleMediaScanner(context, path.concat(name), new SingleMediaScanner.ScanListener() {
                            @Override
                            public void onScanFinish() {
                            }
                        });
                    } else {
                        ToastUtil.getInstance()._short(context, "保存失败");
                    }
                }
            }
        });
    }

    //保存文件到指定路径（保存drawble图片）Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.im_share_sucess);
    public static boolean saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        String storePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "jinxin";
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();

            //把文件插入到系统图库
            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
