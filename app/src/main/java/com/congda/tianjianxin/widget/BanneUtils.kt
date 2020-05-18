package com.congda.tianjianxin.widget

import android.content.Context
import com.congda.tianjianxin.R
import com.congda.tianjianxin.adapter.ImageBannerAdapter
import com.congda.tianjianxin.utils.NumIndicator
import com.youth.banner.Banner
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import com.youth.banner.listener.OnBannerListener
import com.youth.banner.listener.OnPageChangeListener
import com.youth.banner.transformer.AlphaPageTransformer
import com.youth.banner.transformer.ZoomOutPageTransformer
import com.youth.banner.util.BannerUtils


/**
 * @author jinxin
 * 剑之所指，心之所向
 * @date 2020/5/12
 */
class BanneUtils {
    constructor()

    fun setBanner(banner: Banner<Any, ImageBannerAdapter>, context: Context?, imgs: List<String?>?, listener1: OnBannerListener<*>?) {
        banner.setAdapter(ImageBannerAdapter(imgs))
            //数字指示器
//            .setIndicator(NumIndicator(context))
//            .setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
            //圆点指示器
            .setIndicator(CircleIndicator(context))
            .setIndicatorSelectedColorRes(R.color.color_666666)
            .setIndicatorNormalColorRes(R.color.color_f5f5f5)
            .setIndicatorGravity(IndicatorConfig.Direction.CENTER)
            .setIndicatorSpace(BannerUtils.dp2px(10f).toInt())
            .setIndicatorMargins(IndicatorConfig.Margins(BannerUtils.dp2px(5f).toInt()))
            .setIndicatorWidth(20, 20)
            .setOnBannerListener(listener1!!)
//            .setPageTransformer(ZoomOutPageTransformer())
            //添加画廊效果(可以和其他PageTransformer组合使用，比如AlphaPageTransformer，注意但和其他带有缩放的PageTransformer会显示冲突)
            .setBannerGalleryEffect(18, 10)
            //添加透明效果(画廊配合透明效果更棒)
            .addPageTransformer(AlphaPageTransformer())
//            .addOnPageChangeListener(listener2!!)
            .start()
    }
}