package com.congda.baselibrary.utils.glide;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.congda.baselibrary.R;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.util.List;

public class IMChooseUtils {

    /**
     * 选择图片
     */
    public static  void choosePhotoForResult(final Context context, int requestCode, final int maxnumber){
        Matisse.from((Activity) context)
                .choose(MimeType.ofAll(), true)  //是否可以选择不同的类型
                .showSingleMediaType(true)
                .countable(true)  //是否显示数据
                .capture(true)      //是否显示拍照
                ////参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                .captureStrategy(new CaptureStrategy(true, "com.congda.jinxinkt.fileprovider","test"))
                .maxSelectable(maxnumber)
                .addFilter(new IMGifSizeFilter(320, 320, 10 * Filter.K * Filter.K))
                .gridExpectedSize(context.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new IMGlide4Engine())    // for glide-V4
                .setOnSelectedListener(new OnSelectedListener() {
                    @Override
                    public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                        Log.e("onSelected", "onSelected: pathList=" + pathList);
                    }
                })
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(new OnCheckedListener() {
                    @Override
                    public void onCheck(boolean isChecked) {
                        // DO SOMETHING IMMEDIATELY HERE
                        Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                    }
                })
                .forResult(requestCode);
    }
    /**
     * 选择头像
     */
    public static  void choosePhotoHeadForResult(Context context,int requestCode,int maxnumber){
        Matisse.from((Activity) context)
                .choose(MimeType.ofImage(), false)  //是否可以选择不同的类型
                .showSingleMediaType(true)
                .countable(true)  //是否显示数据
                .capture(true)      //是否显示拍照
                ////参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                .captureStrategy(new CaptureStrategy(true, "com.congda.jinxinkt.fileprovider","test"))
                .maxSelectable(maxnumber)
                .addFilter(new IMGifSizeFilter(320, 320, 10 * Filter.K * Filter.K))
                .gridExpectedSize(context.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new IMGlide4Engine())    // for glide-V4
                .setOnSelectedListener(new OnSelectedListener() {
                    @Override
                    public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                        Log.e("onSelected", "onSelected: pathList=" + pathList);

                    }
                })
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(new OnCheckedListener() {
                    @Override
                    public void onCheck(boolean isChecked) {
                        // DO SOMETHING IMMEDIATELY HERE
                        Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                    }
                })
                .forResult(requestCode);
    }

    /**
     * 选择视频
     */
    public static void ChooseVideoForResult(Context context,int requestCode) {
        Matisse.from((Activity)context)
                .choose(MimeType.ofVideo(), false)
                .showSingleMediaType(true)
                .countable(true)
                .capture(false)
                .captureStrategy(new CaptureStrategy(true, "com.congda.jinxinkt.fileprovider", "test"))
                .maxSelectable(1)
                .addFilter(new IMGifSizeFilter(320, 320, 15 * Filter.K * Filter.K))
                .gridExpectedSize(context.getResources().getDimensionPixelSize(R.dimen.grid_expected_size))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new IMGlide4Engine())    // for glide-V4
                .setOnSelectedListener(new OnSelectedListener() {
                    @Override
                    public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                        Log.e("onSelected", "onSelected: pathList=" + pathList);

                    }
                })
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(new OnCheckedListener() {
                    @Override
                    public void onCheck(boolean isChecked) {
                        // DO SOMETHING IMMEDIATELY HERE
                        Log.e("isChecked", "onCheck: isChecked=" + isChecked);
                    }
                })
                .forResult(requestCode);
    }
}
