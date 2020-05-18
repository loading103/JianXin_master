package com.congda.tianjianxin.ui.fragment.mvp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.congda.baselibrary.base.BaseMvpFragment
import com.congda.baselibrary.net.TypeOneBaseHttpResult
import com.congda.baselibrary.widget.IMRefreshUtils
import com.congda.tianjianxin.R
import com.congda.tianjianxin.adapter.ImageBannerAdapter
import com.congda.tianjianxin.adapter.RecycleDemodapter
import com.congda.tianjianxin.bean.BannerBean
import com.congda.tianjianxin.ui.activity.mvp.ui.ComWebViewActivity
import com.congda.tianjianxin.ui.fragment.mvp.contract.ListFirstContract
import com.congda.tianjianxin.ui.fragment.mvp.presenter.ListFirstPresenter
import com.congda.tianjianxin.widget.BanneUtils
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.youth.banner.Banner
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_recycle_first.*

class ListFirstFragment : BaseMvpFragment<ListFirstPresenter>(),ListFirstContract.View,
    OnLoadMoreListener, OnRefreshListener, OnItemClickListener, OnBannerListener<Any> {

    lateinit var banner : Banner<Any, ImageBannerAdapter>
    lateinit var adapter: RecycleDemodapter
    private var datas: MutableList<String> = mutableListOf()
    private var bannerdatas: MutableList<BannerBean> = mutableListOf()

    override fun getLayoutId(): Int {
        return R.layout.fragment_recycle_first
    }

    override fun createPresenter(): ListFirstPresenter {
        return  ListFirstPresenter()
    }

    override fun initView() {
        imRefreshUtils.initRefreshMore(refreshLayout,this,this)
        imRefreshUtils.initVRecycle(recyclerView)
        adapter = RecycleDemodapter()
        adapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInRight)
        adapter.setOnItemClickListener(this)
        recyclerView.adapter=adapter
        adapterAddView()
    }
    /**
     * 加入头部和空布局
     */
    private fun adapterAddView() {
        val viewHead: View = LayoutInflater.from(activity).inflate(R.layout.layout_banner, null)
        val viewEmept: View = LayoutInflater.from(activity).inflate(R.layout.layout_recycle_empty, null)
        banner = viewHead.findViewById(R.id.banner);
        adapter.addHeaderView(viewHead)
        adapter.setEmptyView(viewEmept)
    }

    override fun initData() {
        common_top.setTopTitle("首页")
        mPresenter.getBannerData()
        mPresenter.getListData()
    }

    override fun useEventBus(): Boolean {
        return false
    }
    /**
     * 上拉刷新
     */
    override fun onRefresh(refreshLayout: RefreshLayout) {
        refreshLayout.finishRefresh(2000)

    }
    override fun onLoadMore(refreshLayout: RefreshLayout) {
        refreshLayout.finishLoadMore()
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        showMessage(datas[position])
    }

    /**
     * 处理banner数据
     */
    override fun hanedBannerData(bean: TypeOneBaseHttpResult<MutableList<BannerBean>>) {
        bannerdatas=bean.data
        val imgs: MutableList<String> = mutableListOf()
        for (i in bean.data.indices) {
            imgs.add(bean.data[i].imagePath)
        }
        BanneUtils().setBanner(banner,activity,imgs,this)
    }

    override fun hanedListData(bean: MutableList<String>) {
        datas=bean
        adapter.setNewData(bean)
    }

    override fun OnBannerClick(data: Any?, position: Int) {
        var bundle=Bundle()
        bundle.putString("url", bannerdatas[position].url)
        startActivity(ComWebViewActivity::class.java,bundle,false)
    }

    override fun onResume() {
        super.onResume()
        banner.start();
    }

    override fun onPause() {
        super.onPause()
        banner.stop();
    }

    override fun onDestroy() {
        super.onDestroy()
        banner.destroy()
    }
}
