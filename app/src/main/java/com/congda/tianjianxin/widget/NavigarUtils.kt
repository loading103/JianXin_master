package com.congda.tianjianxin.widget

import android.content.Context
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.viewpager.widget.ViewPager
import com.congda.tianjianxin.R
import com.congda.tianjianxin.utils.ScaleTransitionPagerTitleView
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

/**
 * @author jinxin
 * 剑之所指，心之所向
 * @date 2020/5/12
 */
object NavigarUtils {
    fun setNaviga(context: Context, datas: List<String>,view_pager: ViewPager) :CommonNavigator{
        val commonNavigator = CommonNavigator(context)
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return if (datas == null) 0 else datas.size
            }

            override fun getTitleView(context: Context, index: Int): IPagerTitleView {
//                val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
                val colorTransitionPagerTitleView: SimplePagerTitleView = ScaleTransitionPagerTitleView(context)
                colorTransitionPagerTitleView.textSize = 16f
                colorTransitionPagerTitleView.normalColor =  context.resources.getColor(R.color.color_666666)
                colorTransitionPagerTitleView.selectedColor = context.resources.getColor(R.color.colorPrimary)
                colorTransitionPagerTitleView.setText(datas[index])
                colorTransitionPagerTitleView.setOnClickListener{
                    view_pager.setCurrentItem(index, true)
                }
                return colorTransitionPagerTitleView
            }
            override fun getIndicator(context: Context): IPagerIndicator {
                return LinePagerIndicator(context).apply {
                    mode = LinePagerIndicator.MODE_EXACTLY
                    lineHeight = UIUtil.dip2px(context, 3.0).toFloat()
                    lineWidth = UIUtil.dip2px(context, 30.0).toFloat()
                    roundRadius = UIUtil.dip2px(context, 6.0).toFloat()
                    startInterpolator = AccelerateInterpolator()
                    endInterpolator = DecelerateInterpolator(2.0f)
                    setColors(context.resources.getColor(R.color.colorPrimary))
                }
            }
        }
        return commonNavigator;
    }
}