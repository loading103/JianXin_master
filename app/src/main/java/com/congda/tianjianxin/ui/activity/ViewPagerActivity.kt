package com.congda.tianjianxin.ui.activity

import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.congda.baselibrary.base.BaseActivity
import com.congda.baselibrary.utils.IMDensityUtil
import com.congda.tianjianxin.R
import com.congda.tianjianxin.adapter.CagegoryViewPagerAdapter
import com.congda.tianjianxin.adapter.EntranceAdapter
import com.congda.tianjianxin.bean.ModelHomeEntrance
import kotlinx.android.synthetic.main.activity_recycle.view_pager
import kotlinx.android.synthetic.main.activity_settings.common_top
import kotlinx.android.synthetic.main.activity_view_pager.*
import java.util.*

class ViewPagerActivity : BaseActivity(),View.OnClickListener {

    private var homeEntrances= mutableListOf<ModelHomeEntrance>()
    override fun getLayoutId(): Int {
        return R.layout.activity_view_pager
    }
    override fun initView() {
        common_top.setTopTitle("分页标题栏")
    }
    override fun initListener() {
        val mviewpagerParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (IMDensityUtil.getScreenWidth(this)*1.0f / 2.0f).toInt())
        val  mllroot= LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (IMDensityUtil.getScreenWidth(this) *1.0f / 2.0f + 70).toInt())
        home_entrance.layoutParams = mllroot
        view_pager.layoutParams = mviewpagerParams

    }


    override fun initData() {
        getData();
        val mviewpagerParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (IMDensityUtil.getScreenWidth(this)*1.0f/ 2.0f).toInt())

        val inflater = LayoutInflater.from(this)
        //将RecyclerView放至ViewPager中：
        val pageSize: Int = 10
        //一共的页数等于 总数/每页数量，并取整。
        val pageCount = Math.ceil(homeEntrances.size * 1.0 / pageSize).toInt()
        val viewList: MutableList<View> = ArrayList()
        for (index in 0 until pageCount) { //每个页面都是inflate出一个新实例
            val recyclerView: RecyclerView = inflater.inflate(R.layout.item_home_entrance_vp, view_pager, false) as RecyclerView
            recyclerView.layoutParams = mviewpagerParams
            recyclerView.layoutManager = GridLayoutManager(this, 5)
            val entranceAdapter = EntranceAdapter(this, homeEntrances, index, 10)
            recyclerView.adapter = entranceAdapter
            viewList.add(recyclerView)
        }
        val adapter = CagegoryViewPagerAdapter(viewList)
        view_pager.adapter = adapter
//        entranceIndicatorView.setIndicatorCount(entranceViewPager.getAdapter().getCount())
//        entranceIndicatorView.setCurrentIndicator(entranceViewPager.getCurrentItem())
        view_pager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
        })

    }
    override fun onClick(p0: View?) {
    }

    fun getData() {
        homeEntrances = ArrayList()
        homeEntrances.add(ModelHomeEntrance("美食", R.mipmap.ic_category_0))
        homeEntrances.add(ModelHomeEntrance("电影", R.mipmap.ic_category_1))
        homeEntrances.add(ModelHomeEntrance("酒店住宿", R.mipmap.ic_category_2))
        homeEntrances.add(ModelHomeEntrance("生活服务", R.mipmap.ic_category_3))
        homeEntrances.add(ModelHomeEntrance("KTV", R.mipmap.ic_category_4))
        homeEntrances.add(ModelHomeEntrance("旅游", R.mipmap.ic_category_5))
        homeEntrances.add(ModelHomeEntrance("学习培训", R.mipmap.ic_category_6))
        homeEntrances.add(ModelHomeEntrance("汽车服务", R.mipmap.ic_category_7))
        homeEntrances.add(ModelHomeEntrance("摄影写真", R.mipmap.ic_category_8))
        homeEntrances.add(ModelHomeEntrance("休闲娱乐", R.mipmap.ic_category_10))
        homeEntrances.add(ModelHomeEntrance("丽人", R.mipmap.ic_category_11))
        homeEntrances.add(ModelHomeEntrance("运动健身", R.mipmap.ic_category_12))
        homeEntrances.add(ModelHomeEntrance("大保健", R.mipmap.ic_category_13))
        homeEntrances.add(ModelHomeEntrance("团购", R.mipmap.ic_category_14))
        homeEntrances.add(ModelHomeEntrance("景点", R.mipmap.ic_category_16))
        homeEntrances.add(ModelHomeEntrance("全部分类", R.mipmap.ic_category_15))
    }
}