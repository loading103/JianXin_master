package com.congda.tianjianxin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.congda.tianjianxin.R
import com.congda.tianjianxin.adapter.CagegoryViewPagerAdapter
import com.congda.tianjianxin.adapter.RecycleViewPagerdapter
import com.congda.tianjianxin.bean.ModelHomeEntrance
import kotlinx.android.synthetic.main.item_view_dot.view.*
import java.util.*

class ViewPagerTitle : LinearLayout, ViewPager.OnPageChangeListener {
    private  var   lastpositon :Int=0
    private var pageSize: Int = 10
    private var datas= mutableListOf<ModelHomeEntrance>()
    lateinit var viewPager :ViewPager
    lateinit var mllcontain :LinearLayout
    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }
    private fun initView(context: Context) {
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_viewpager_title, this)
        viewPager = view.findViewById<ViewPager>(R.id.view_pager)
        mllcontain = view.findViewById<LinearLayout>(R.id.llcontain)
    }

    public fun setData( context: Context,data :MutableList<ModelHomeEntrance>){
        this.datas=data
        initData(context)
    }
    public fun setpageSize( pageSize:Int){
        this.pageSize=pageSize
    }


    private fun initData(context: Context){
        //一共的页数等于 总数/每页数量，并取整。
        val pageCount = Math.ceil(datas.size * 1.0 / pageSize).toInt()
        val viewList: MutableList<View> = ArrayList()
        for (index in 0 until pageCount) { //每个页面都是inflate出一个新实例

            val recyclerView: RecyclerView =  LayoutInflater.from(context).inflate(R.layout.item_home_entrance_vp, viewPager, false) as RecyclerView
            recyclerView.layoutManager = GridLayoutManager(context, pageSize/2)
            val adapter = RecycleViewPagerdapter(context, index, pageSize,datas)
            recyclerView.adapter = adapter


            viewList.add(recyclerView)
            val view:View= LayoutInflater.from(context).inflate(R.layout.item_view_dot, viewPager, false)
            val iv_dot = view.findViewById<ImageView>(R.id.iv_dot)
            iv_dot.isEnabled = index==0
            mllcontain.addView(view)
        }
        val adapter = CagegoryViewPagerAdapter(viewList)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        mllcontain.getChildAt(position).iv_dot.isEnabled=true
        mllcontain.getChildAt(lastpositon).iv_dot.isEnabled=false
        lastpositon=position
    }

}