package com.congda.tianjianxin.ui.fragment.mvp.contract

import SplashAdBean
import VersonBeanData
import com.congda.baselibrary.mvp.IModel
import com.congda.baselibrary.mvp.IView
import com.congda.baselibrary.net.BaseHttpResult
import com.congda.baselibrary.net.TypeOneBaseHttpResult
import com.congda.tianjianxin.bean.BannerBean
import io.reactivex.Observable

interface ListFirstContract {
    interface View : IView{
        fun hanedBannerData(baseHttpResult: TypeOneBaseHttpResult<MutableList<BannerBean>>)
        fun hanedListData(bean:MutableList<String>)
    }

    interface Model : IModel{
        fun  getBannList(): Observable<TypeOneBaseHttpResult<MutableList<BannerBean>>>
    }
}