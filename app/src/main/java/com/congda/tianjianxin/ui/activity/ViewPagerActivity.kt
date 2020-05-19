package com.congda.tianjianxin.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.congda.baselibrary.base.BaseActivity
import com.congda.baselibrary.utils.IMDensityUtil
import com.congda.tianjianxin.R
import com.congda.tianjianxin.adapter.CagegoryViewPagerAdapter
import com.congda.tianjianxin.adapter.RecycleViewPagerdapter
import com.congda.tianjianxin.bean.ModelHomeEntrance
import kotlinx.android.synthetic.main.activity_recycle.view_pager
import kotlinx.android.synthetic.main.activity_settings.common_top
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlinx.android.synthetic.main.item_view_dot.view.*
import java.util.*

class ViewPagerActivity : BaseActivity(),View.OnClickListener, ViewPager.OnPageChangeListener {
    var   lastpositon :Int=0
    private var datas= mutableListOf<ModelHomeEntrance>()
    override fun getLayoutId(): Int {
        return R.layout.activity_view_pager
    }
    override fun initView() {
        common_top.setTopTitle("分页标题栏")
    }
    override fun initListener() {

    }


    override fun initData() {
        getData();
        //将RecyclerView放至ViewPager中：
        val pageSize: Int = 10
        //一共的页数等于 总数/每页数量，并取整。
        val pageCount = Math.ceil(datas.size * 1.0 / pageSize).toInt()
        val viewList: MutableList<View> = ArrayList()
        for (index in 0 until pageCount) { //每个页面都是inflate出一个新实例
            val recyclerView: RecyclerView =  LayoutInflater.from(this).inflate(R.layout.item_home_entrance_vp, view_pager, false) as RecyclerView
            recyclerView.layoutManager = GridLayoutManager(this, 5)
            val adapter = RecycleViewPagerdapter(this, index, 10,datas)
            recyclerView.adapter = adapter
            viewList.add(recyclerView)
            val view:View= LayoutInflater.from(this).inflate(R.layout.item_view_dot, view_pager, false)
            val iv_dot = view.findViewById<ImageView>(R.id.iv_dot)
            iv_dot.isEnabled = index==0
            llcontain.addView(view)
        }
        val adapter = CagegoryViewPagerAdapter(viewList)
        view_pager.adapter = adapter
        view_pager.addOnPageChangeListener(this)

    }
    override fun onClick(p0: View?) {
    }

    fun getData() {
        datas = ArrayList()
        datas.add(ModelHomeEntrance("美食", R.mipmap.ic_category_0))
        datas.add(ModelHomeEntrance("电影", R.mipmap.ic_category_1))
        datas.add(ModelHomeEntrance("酒店住宿", R.mipmap.ic_category_2))
        datas.add(ModelHomeEntrance("生活服务", R.mipmap.ic_category_3))
        datas.add(ModelHomeEntrance("KTV", R.mipmap.ic_category_4))
        datas.add(ModelHomeEntrance("旅游", R.mipmap.ic_category_5))
        datas.add(ModelHomeEntrance("学习培训", R.mipmap.ic_category_6))
        datas.add(ModelHomeEntrance("汽车服务", R.mipmap.ic_category_7))
        datas.add(ModelHomeEntrance("摄影写真", R.mipmap.ic_category_8))
        datas.add(ModelHomeEntrance("休闲娱乐", R.mipmap.ic_category_10))
        datas.add(ModelHomeEntrance("丽人", R.mipmap.ic_category_11))
        datas.add(ModelHomeEntrance("运动健身", R.mipmap.ic_category_12))
        datas.add(ModelHomeEntrance("大保健", R.mipmap.ic_category_13))
        datas.add(ModelHomeEntrance("团购", R.mipmap.ic_category_14))
        datas.add(ModelHomeEntrance("景点", R.mipmap.ic_category_16))
        datas.add(ModelHomeEntrance("全部分类", R.mipmap.ic_category_15))
        datas.add(ModelHomeEntrance("休闲娱乐1", R.mipmap.ic_category_10))
        datas.add(ModelHomeEntrance("丽人1", R.mipmap.ic_category_11))
        datas.add(ModelHomeEntrance("运动健身1", R.mipmap.ic_category_12))
        datas.add(ModelHomeEntrance("大保健1", R.mipmap.ic_category_13))
        datas.add(ModelHomeEntrance("团购1", R.mipmap.ic_category_14))
        datas.add(ModelHomeEntrance("景点1", R.mipmap.ic_category_16))
        datas.add(ModelHomeEntrance("全部分类1", R.mipmap.ic_category_15))
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        llcontain.getChildAt(position).iv_dot.isEnabled=true
        llcontain.getChildAt(lastpositon).iv_dot.isEnabled=false
        lastpositon=position
    }
}