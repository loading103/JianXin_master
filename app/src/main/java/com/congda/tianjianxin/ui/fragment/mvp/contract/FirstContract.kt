package com.congda.tianjianxin.ui.fragment.mvp.contract

import SplashAdBean
import VersonBeanData
import com.congda.baselibrary.mvp.IModel
import com.congda.baselibrary.mvp.IView
import com.congda.baselibrary.net.BaseHttpResult
import com.congda.baselibrary.net.TypeOneBaseHttpResult
import com.congda.tianjianxin.bean.BannerBean
import io.reactivex.Observable

interface FirstContract {
    interface View : IView{
    }

    interface Model : IModel{
    }
}