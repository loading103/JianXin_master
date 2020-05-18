package com.congda.tianjianxin.ui.fragment.mvp.ui

import com.congda.baselibrary.base.BaseMvpFragment
import com.congda.tianjianxin.R
import com.congda.tianjianxin.ui.fragment.mvp.contract.FourthContract
import com.congda.tianjianxin.ui.fragment.mvp.presenter.FourthPresenter

class FourthFragment : BaseMvpFragment<FourthPresenter>(),FourthContract.View{
    override fun getLayoutId(): Int {
        return R.layout.fragment_fourth
    }

    override fun createPresenter(): FourthPresenter {
        return  FourthPresenter()
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun initData() {
    }

    override fun useEventBus(): Boolean {
        return false
    }
}
