package com.congda.tianjianxin.ui.activity.mvp.presenter

import SplashAdBean
import com.congda.baselibrary.mvp.BasePresenter
import com.congda.baselibrary.net.BaseHttpResult
import com.congda.baselibrary.net.BaseObserver
import com.congda.baselibrary.rx.RxSchedulers
import com.congda.tianjianxin.ui.activity.mvp.contract.SplashContract
import com.congda.tianjianxin.ui.activity.mvp.model.SplashModel
import com.trello.rxlifecycle2.android.ActivityEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_splash.*

class SplashPresenter : BasePresenter<SplashContract.Model, SplashContract.View>() {

    override fun createModel(): SplashContract.Model {
        return SplashModel()
    }

    fun getSplashData() {
        model.getGetAdJson()
            .compose(RxSchedulers.applySchedulers(getLifecycleProvider<ActivityEvent>()))
            .subscribe(object : BaseObserver<List<SplashAdBean>>(view,false) {
                override fun onFailure(code: String, errMsg: String, isNetError: Boolean) {
                    view.showToast(errMsg)
                    view.setSplashData(null)
                }

                override fun onSuccess(result: BaseHttpResult<List<SplashAdBean>>) {
                    view.setSplashData(result)
                }

            })
    }
}


