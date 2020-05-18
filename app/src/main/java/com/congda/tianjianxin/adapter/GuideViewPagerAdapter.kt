package com.congda.tianjianxin.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class GuideViewPagerAdapter(private val views: List<View>) : PagerAdapter() {
    override fun getCount(): Int {
        return views.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
        (container as ViewPager).removeView(views[position])
    }

    override fun isViewFromObject(view: View, any: Any): Boolean {
        return view === any as View
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        (container as ViewPager).addView(views[position], 0)
        return views[position]
    }

}