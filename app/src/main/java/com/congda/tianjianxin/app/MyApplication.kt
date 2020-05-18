package com.congda.tianjianxin.app

import android.content.Context
import com.congda.baselibrary.app.BaseApplication

class MyApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        var context: Context? = null
    }
}