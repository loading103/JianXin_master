package com.congda.tianjianxin.ui.activity

import android.view.View
import com.congda.baselibrary.base.BaseActivity
import com.congda.baselibrary.utils.glide.IMGlideCacheUtil
import com.congda.baselibrary.widget.dialog.IMIosCommonDiglog
import com.congda.tianjianxin.R
import com.suke.widget.SwitchButton
import kotlinx.android.synthetic.main.activity_settings.*

class SettingActivity : BaseActivity(), SwitchButton.OnCheckedChangeListener, View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_settings
    }
    override fun initView() {
        common_top.setTopTitle("系统设置")
    }
    override fun initListener() {
        sov4.setOnClickListener(this)

        val sb1: SwitchButton = sov1.switchButton
        sb1.tag = 1
        sb1.setOnCheckedChangeListener(this)
        val sb2: SwitchButton = sov2.switchButton
        sb2.tag = 2
        sb2.setOnCheckedChangeListener(this)
        val sb3: SwitchButton = sov3.switchButton
        sb3.tag = 3
        sb3.setOnCheckedChangeListener(this)
    }


    override fun initData() {
    }
    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.sov4->{
                cleanCache()
            }
        }
    }


    override fun onCheckedChanged(view: SwitchButton, isChecked: Boolean) {
        when (view.tag) {
            1 -> {
                sov1.switchButton.isChecked = isChecked;
                showChecked(isChecked,1)
            }
            2 -> {
                sov2.switchButton.isChecked = isChecked;
                showChecked(isChecked,2)
            }
            3 -> {
                sov3.switchButton.isChecked = isChecked;
                showChecked(isChecked,3)
            }
        }
    }

    fun  showChecked(isChecked: Boolean,pos : Int){
        if(isChecked){
            showMessage("打开$pos")
        }else{
            showMessage("关闭$pos")
        }
    }

    /**
     * 清楚缓存
     */
    private fun cleanCache () {
        val diglog = IMIosCommonDiglog(this)
        val cachesize = IMGlideCacheUtil.getInstance().getCacheSize(this)
        diglog.showCommonDiglog("是否需要清除 $cachesize 缓存数据?"){
            IMGlideCacheUtil.getInstance().clearImageAllCache(this)
            val cacheSize: String = IMGlideCacheUtil.getInstance().getCacheSize(this)
        }
    }
}