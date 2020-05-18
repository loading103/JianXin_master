package com.congda.tianjianxin.ui.fragment.mvp.contract

import SplashAdBean
import VersonBeanData
import com.congda.baselibrary.mvp.IModel
import com.congda.baselibrary.mvp.IView
import com.congda.baselibrary.net.BaseHttpResult
import io.reactivex.Observable

interface ThirdContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View : IView{
    }

    interface Model : IModel{

    }
}