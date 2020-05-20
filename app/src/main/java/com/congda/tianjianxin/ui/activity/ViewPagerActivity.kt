package com.congda.tianjianxin.ui.activity

import android.content.Intent
import com.congda.baselibrary.base.BaseActivity
import com.congda.tianjianxin.R
import com.congda.tianjianxin.bean.ModelHomeEntrance
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_settings.common_top
import kotlinx.android.synthetic.main.activity_view_pager.*
import java.util.*

class ViewPagerActivity : BaseActivity(){
    var   lastpositon :Int=0

    private var datas= mutableListOf<ModelHomeEntrance>()

    override fun getLayoutId(): Int {
        return R.layout.activity_view_pager
    }
    override fun initView() {
        common_top.setTopTitle("分页标题栏")
    }
    override fun initListener() {
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

    override fun initData() {
        vp_title.setData(this,datas)
    }

    override fun onStop() {
        super.onStop()
        key_board.clickHomeView()
    }
}