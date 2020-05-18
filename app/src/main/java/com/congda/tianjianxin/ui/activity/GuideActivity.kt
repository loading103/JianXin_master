package com.congda.tianjianxin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.congda.baselibrary.base.BaseActivity
import com.congda.tianjianxin.R
import com.congda.tianjianxin.adapter.GuideViewPagerAdapter
import kotlinx.android.synthetic.main.activity_guide.*

class GuideActivity : BaseActivity(), View.OnClickListener, ViewPager.OnPageChangeListener {
    private var views: ArrayList<View> = arrayListOf()
    // 引导页图片资源
    private val pics = intArrayOf(R.layout.layout_guide_one, R.layout.layout_guide_two, R.layout.layout_guide_three)
    // 底部小点图片
    lateinit var dots: Array<ImageView?>

    private var currentIndex = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_guide
    }

    override fun initView() {

    }
    override fun initListener() {

    }
    override fun initData() {
        // 初始化引导页视图列表
        for (i in pics.indices) {
            val view: View = LayoutInflater.from(this).inflate(pics.get(i), null)
            if (i ==pics.size - 1) {
                val tvGuideLogin = view.findViewById<View>(R.id.tv_guide_login) as TextView
                tvGuideLogin.setOnClickListener(this)
            }
            views.add(view)
        }
        initDots()
        // 初始化adapter
        var adapter = GuideViewPagerAdapter(views)
        vp_guide.adapter = adapter
        vp_guide.addOnPageChangeListener(this)
    }
    private fun initDots() {
        dots = arrayOfNulls<ImageView?>(pics.size)
        // 循环取得小点图片
        for (i in pics.indices) { // 得到一个LinearLayout下面的每一个子元素
            dots[i] = llcontain.getChildAt(i) as ImageView
            dots[i]!!.isEnabled = false // 都设为灰色
            dots[i]!!.setOnClickListener(this)
            dots[i]!!.tag = i // 设置位置tag，方便取出与当前位置对应
        }
        currentIndex = 0
        dots[currentIndex]!!.isEnabled = true // 设置为白色，即选中状态
    }

    override fun onClick(p0: View?) {
        startActivity(MainActivity::class.java,true)
    }

    /**
     * 滑动引导页
     */
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        setCurDot(position)
    }
    /**
     * 设置当前指示点
     */
    private  fun setCurDot(position: Int) {
        if (position < 0 || position > pics.size || currentIndex == position) {
            return
        }
        if (position ==pics.size-1) {
            llcontain.visibility=View.GONE
            currentIndex = position
            return
        }
        llcontain.visibility=View.VISIBLE
        dots[position]!!.isEnabled = true
        dots[currentIndex]!!.isEnabled = false
        currentIndex = position
    }
}