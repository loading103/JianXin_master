package com.congda.tianjianxin.ui.fragment.mvp.presenter

import com.congda.baselibrary.mvp.BasePresenter
import com.congda.tianjianxin.ui.fragment.mvp.contract.FourthContract
import com.congda.tianjianxin.ui.fragment.mvp.model.FourthModel

class FourthPresenter : BasePresenter<FourthContract.Model, FourthContract.View>(){

    override fun createModel(): FourthContract.Model {
        return FourthModel()
    }
}


