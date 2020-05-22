package com.congda.tianjianxin.ui.activity

import android.graphics.Color
import androidx.fragment.app.Fragment
import com.congda.baselibrary.base.BaseActivity
import com.congda.baselibrary.utils.IMStatusBarUtil
import com.congda.tianjianxin.R
import com.congda.tianjianxin.adapter.MyViewPagerAdapter
import com.congda.tianjianxin.ui.fragment.mvp.ui.ListFirstFragment
import com.congda.tianjianxin.ui.fragment.mvp.ui.ListSecondFragment
import com.congda.tianjianxin.ui.fragment.mvp.ui.SecondFragment
import com.congda.tianjianxin.widget.NavigarUtils
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_recycle.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import kotlin.math.abs


class RecycleDemoActivity : BaseActivity() {
    var datas = arrayListOf("列表一", "多布局列表二","列表三")
    var fragments : MutableList<Fragment> = mutableListOf()

    override fun getLayoutId(): Int {
        return R.layout.activity_recycle
    }

    override fun initStatusBar() {
        IMStatusBarUtil.setTranslucentForImageView(this, 0, null)
    }
    override fun initView() {
        val naviga = NavigarUtils().setNaviga(this, datas,view_pager)
        magic_indicator.navigator = naviga
    }
    override fun initListener() {
    }
    override fun initData() {
        fragments.add(ListFirstFragment())
        fragments.add(ListSecondFragment())
        fragments.add(SecondFragment())
        var pagerAdapter = MyViewPagerAdapter(supportFragmentManager, fragments)
        view_pager.adapter = pagerAdapter
        ViewPagerHelper.bind(magic_indicator, view_pager)

        appBarLayout.addOnOffsetChangedListener( AppBarLayout.OnOffsetChangedListener() {
                appBarLayout: AppBarLayout, verh: Int ->
                    toolbar.setBackgroundColor(changeAlpha(resources.getColor(R.color.colorPrimary), abs(verh * 1.0f) / appBarLayout.totalScrollRange))
        })
    }

    /**
     * 根据百分比改变颜色透明度
     */
    public fun changeAlpha(color: Int, fraction :Float) :Int{
        val red = Color.red(color);
        val green = Color.green(color);
        val blue = Color.blue(color);
        val alpha =  (Color.alpha(color) * fraction).toInt();
        return Color.argb(alpha, red, green, blue);
    }

}