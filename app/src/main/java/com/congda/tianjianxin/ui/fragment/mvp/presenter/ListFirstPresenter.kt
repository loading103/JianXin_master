package com.congda.tianjianxin.ui.fragment.mvp.presenter

import com.congda.baselibrary.mvp.BasePresenter
import com.congda.baselibrary.net.TypeOneBaseHttpResult
import com.congda.baselibrary.net.TypeOneBaseObserve
import com.congda.baselibrary.rx.RxSchedulers
import com.congda.tianjianxin.bean.BannerBean
import com.congda.tianjianxin.ui.fragment.mvp.contract.ListFirstContract
import com.congda.tianjianxin.ui.fragment.mvp.contract.SecondContract
import com.congda.tianjianxin.ui.fragment.mvp.model.ListFirstModel
import com.congda.tianjianxin.ui.fragment.mvp.model.SecondModel
import com.trello.rxlifecycle2.android.ActivityEvent

class ListFirstPresenter : BasePresenter<ListFirstContract.Model, ListFirstContract.View>(){

    override fun createModel(): ListFirstContract.Model {
        return ListFirstModel()
    }
    /**
     * 获取banner
     */
    fun getBannerData() {
        model.getBannList()
            .compose(RxSchedulers.applySchedulers(getLifecycleProvider<ActivityEvent>()))
            .subscribe(object : TypeOneBaseObserve<MutableList<BannerBean>>(view,false) {
                override fun onFailure(code: String, errMsg: String, isNetError: Boolean) {
                    view.showToast(errMsg)
                }

                override fun onSuccess(baseHttpResult: TypeOneBaseHttpResult<MutableList<BannerBean>>) {
                    if(baseHttpResult?.data == null){
                        return
                    }
                    view.hanedBannerData(baseHttpResult)
                }
            })
    }

    fun getListData() {
        var  list = mutableListOf<String>()
        for (index in 0..30){
            list.add("模拟数据$index")
        }
        view.hanedListData(list)
    }
}


