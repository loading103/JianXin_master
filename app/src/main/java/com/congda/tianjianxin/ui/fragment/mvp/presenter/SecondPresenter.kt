package com.congda.tianjianxin.ui.fragment.mvp.presenter

import com.congda.baselibrary.mvp.BasePresenter
import com.congda.tianjianxin.ui.fragment.mvp.contract.SecondContract
import com.congda.tianjianxin.ui.fragment.mvp.model.SecondModel

class SecondPresenter : BasePresenter<SecondContract.Model, SecondContract.View>(){

    override fun createModel(): SecondContract.Model {
        return SecondModel()
    }
}


