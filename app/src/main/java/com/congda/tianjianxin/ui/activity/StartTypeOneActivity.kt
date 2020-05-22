package com.congda.tianjianxin.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import android.transition.Explode
import android.transition.Fade
import android.view.View
import com.congda.baselibrary.base.BaseActivity
import com.congda.tianjianxin.R
import kotlinx.android.synthetic.main.activity_start_type1.*


class StartTypeOneActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_start_type1
    }

    override fun initView() {
        val type = intent.getStringExtra("type") ?: return
        if(type.equals("3")){
            //分解效果
            window.enterTransition = Explode().setDuration(1500)
            window.exitTransition = Explode().setDuration(1500)
        }
        if(type.equals("4")){
            //浅入浅出
            window.enterTransition = Fade().setDuration(1500)
            window.exitTransition = Fade().setDuration(1500)
        }
    }
    override fun initListener() {}
    override fun initData() {}
}