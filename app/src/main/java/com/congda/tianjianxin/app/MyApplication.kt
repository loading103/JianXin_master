package com.congda.tianjianxin.app

import android.content.Context
import cc.shinichi.library.tool.ui.ToastUtil
import com.congda.baselibrary.app.BaseApplication
import com.congda.tianjianxin.BuildConfig
import com.congda.tianjianxin.app.Constant.BASE_URL

class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        switchEnv()
    }
    companion object {
        var context: Context? = null
    }

    private fun switchEnv() {
        when(BuildConfig.BUILD_TYPE){
            "debug"->{
                BASE_URL=Constant.BASE_URL_DEBUG;
                ToastUtil.getInstance()._short(this,"debug");
            }
            "release"->{
                BASE_URL=Constant.BASE_URL_RELSESE;
                ToastUtil.getInstance()._short(this,"release");
            }
        }
    }

}