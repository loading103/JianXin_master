package com.congda.tianjianxin.ui.fragment.mvp.ui

import com.chad.library.adapter.base.BaseQuickAdapter
import com.congda.baselibrary.base.BaseMvpFragment
import com.congda.baselibrary.widget.IMRefreshUtils
import com.congda.baselibrary.widget.MyChatHeadView
import com.congda.tianjianxin.R
import com.congda.tianjianxin.adapter.RecycleSecondadpter
import com.congda.tianjianxin.bean.TagsBean
import com.congda.tianjianxin.ui.fragment.mvp.contract.ListScondContract
import com.congda.tianjianxin.ui.fragment.mvp.presenter.ListScondPresenter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import kotlinx.android.synthetic.main.fragment_recycle_scound.*

class ListSecondFragment : BaseMvpFragment<ListScondPresenter>(), ListScondContract.View, OnRefreshListener {
    private var datas = mutableListOf<TagsBean>()
    lateinit var adapter:RecycleSecondadpter
    override fun getLayoutId(): Int {
        return R.layout.fragment_recycle_scound
    }

    override fun createPresenter(): ListScondPresenter {
        return   ListScondPresenter()
    }

    override fun initView() {
        refreshLayout.setRefreshHeader(MyChatHeadView(activity))
        imRefreshUtils.initRefresh(refreshLayout,this)
        imRefreshUtils.initVRecycle(recyclerView)
    }

    override fun initListener() {
    }

    override fun initData() {
        val getdata = mPresenter.getdata()
        datas.addAll(getdata)
        adapter=RecycleSecondadpter(datas)
        adapter.setAnimationWithDefault( BaseQuickAdapter.AnimationType.ScaleIn)
        recyclerView.adapter=adapter

    }

    override fun useEventBus(): Boolean {
        return false
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        refreshLayout.finishRefresh(2000)
        datas.addAll(mPresenter.getdata())
        adapter.notifyDataSetChanged()
    }
}
