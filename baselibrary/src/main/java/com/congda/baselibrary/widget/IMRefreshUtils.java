package com.congda.baselibrary.widget;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.congda.baselibrary.adapter.baseadapter.IMBaseRecycleViewAdapter_T;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

public class IMRefreshUtils {

    private  Context context;
    public IMRefreshUtils(Context context) {
        this.context=context;
    }

    /**
     * 主要上下滑动的弹性
     */
    public  void initNoRefreshAndMore(RefreshLayout refreshLayout) {
        refreshLayout.setEnableRefresh(false);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        refreshLayout.setEnablePureScrollMode(true);//是否启用纯滚动模式
        refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
        refreshLayout.setEnableOverScrollDrag(true);//是否启用越界拖动（仿苹果效果）1.0.4
    }

    /**
     * 主要下拉刷新
     */
    public  void initRefresh(RefreshLayout refreshLayout, OnRefreshListener listener) {
        refreshLayout.autoRefresh();//自动刷新
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        refreshLayout.setReboundDuration(1000);//回弹动画时长（毫秒）
        refreshLayout.setOnRefreshListener(listener);
        refreshLayout.setEnableOverScrollDrag(true);
        refreshLayout.setHeaderMaxDragRate(1.5f);//最大显示下拉高度/Header标准高度
        refreshLayout.setHeaderTriggerRate(1);//触发刷新距离 与 HeaderHeight 的比率1.0.4
        refreshLayout.setEnableHeaderTranslationContent(true);//是否下拉Header的时候向下平移列表或者内容
    }

    /**
     * 主要下拉下拉
     */
    public  void initRefreshMore(RefreshLayout refreshLayout, OnRefreshListener listener1, OnLoadMoreListener listener2) {
        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(true);//是否启用上拉加载功能
        refreshLayout.setReboundDuration(1000);
        refreshLayout.setOnRefreshListener(listener1);
        refreshLayout.setOnLoadMoreListener(listener2);
        refreshLayout.setHeaderMaxDragRate(1.5f);//最大显示下拉高度/Header标准高度
        refreshLayout.setHeaderTriggerRate(1);//触发刷新距离 与 HeaderHeight 的比率1.0.4
        refreshLayout.setEnableOverScrollDrag(true);
    }

    /**
     * 数值recyclerView
     */
    public  void initVRecycle(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
