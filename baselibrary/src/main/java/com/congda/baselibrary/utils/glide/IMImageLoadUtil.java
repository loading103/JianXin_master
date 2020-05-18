package com.congda.baselibrary.utils.glide;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.congda.baselibrary.R;
import com.congda.baselibrary.utils.IMDensityUtil;

import io.reactivex.annotations.Nullable;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;


/**
 * @author jinxin
 * 剑之所指，心之所向
 * @date 2019/8/4
 *加载图片类
 */
public class IMImageLoadUtil {
    /**
     * 加载普通网络图片
     */
    public static void CommonImageLoad(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.im_icon_stub_loading)
                .error(R.mipmap.im_icon_stub)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(imageView);
    }
    /**
     * 加载普通类型的图片centerCrop
     */
    public static void CommonImageLoadCp(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.im_icon_stub_loading)
                .error(R.mipmap.im_icon_stub)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .centerCrop()
                .into(imageView);
    }
    /**
     * 加载头像(默认)
     */
    public static void CommonHeadImageLoad(Context context, String url, ImageView imageView) {
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.im_icon_stub_loading_circle)
                .error(R.mipmap.im_icon_stub_circle)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .apply(requestOptions)
                .into(imageView);
    }
    /**
     * 加载圆形图片
     */
    public static void CommonImageCircleLoad(Context context, String url, ImageView imageView) {
        RequestOptions requestOptions = RequestOptions.circleCropTransform();
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.im_icon_stub_loading)
                .error(R.mipmap.im_icon_stub)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .apply(requestOptions)
                .into(imageView);
    }
    /**
     * 加载圆形图片(带边框)
     */
    public static void CommonImageLineCircleLoad(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions().bitmapTransform(new CircleCrop())
                        .transform(new IMGlideCircleWithBorder(context, 2,context.getResources().getColor(R.color.color_0BD2CF))))
                .placeholder(R.mipmap.im_icon_stub_loading_circle)
                .error(R.mipmap.im_icon_stub_circle)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(imageView);
    }
    /**
     *圆角图片
     */
    public static void CommonImageRoundLoad(Context context, String url, ImageView imageView) {
        RoundedCornersTransformation transform = new RoundedCornersTransformation(IMDensityUtil.dip2px(context,5),0);
        RequestOptions options = new RequestOptions().placeholder(R.mipmap.im_icon_stub).error(R.mipmap.im_icon_stub).transform(transform);
        Glide.with(context)
                .asBitmap()
                .load(url)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(options)
                .dontAnimate()
                .into(imageView);
    }

    /**
     * 加载普通类型的图片fitCenter
     */
    public static void CommonImageLoadFc(Context context, String url, ImageView imageView) {
        final ObjectAnimator anim = ObjectAnimator.ofInt(imageView, "ImageLevel", 0, 10000);
        anim.setDuration(800);
        anim.setRepeatCount(ObjectAnimator.INFINITE);
        anim.start();

        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.im_image_loading)
                .error(R.mipmap.im_icon_stub)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        anim.cancel();
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        anim.cancel();
                        return false;
                    }
                })
                .fitCenter()
                .into(imageView);
    }



    /**
     * 加载渐渐显示的效果(只在启动页使用)
     */
    public static void CommonSplashImageLoadCp(Context context, String url, ImageView imageView) {
        DrawableCrossFadeFactory drawableCrossFadeFactory = new DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build();
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.im_splash_bg)
                .error(R.mipmap.im_splash_bg)
                .transition(DrawableTransitionOptions.with(drawableCrossFadeFactory))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(imageView);
    }
    /**
     * 加载gif
     */
    public static void CommonGifLoadCp(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).listener(new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                if (resource instanceof GifDrawable) {
                    //加载一次
                    ((GifDrawable)resource).setLoopCount(-1);
                }
                return false;
            }

        }).into(imageView);
    }


}
