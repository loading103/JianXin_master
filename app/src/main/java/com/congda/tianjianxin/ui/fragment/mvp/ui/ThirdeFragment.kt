package com.congda.tianjianxin.ui.fragment.mvp.ui

import com.congda.baselibrary.base.BaseMvpFragment
import com.congda.tianjianxin.R
import com.congda.tianjianxin.ui.fragment.mvp.contract.ThirdContract
import com.congda.tianjianxin.ui.fragment.mvp.presenter.ThirdPresenter

class ThirdeFragment : BaseMvpFragment<ThirdPresenter>(),ThirdContract.View{
    override fun getLayoutId(): Int {
        return R.layout.fragment_third
    }

    override fun createPresenter(): ThirdPresenter {
        return  ThirdPresenter()
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
