package com.congda.baselibrary.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.congda.baselibrary.R;
import com.congda.baselibrary.widget.ActivityUtils;
import com.congda.baselibrary.widget.IMRefreshUtils;
import com.congda.baselibrary.widget.loading.ShowLoadiongUtils;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.greenrobot.eventbus.EventBus;


public abstract class BaseFragment extends RxFragment {
    public IMRefreshUtils  imRefreshUtils;
    /**
     * 缓存Fragment view
     */
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRootView==null){
            mRootView=inflater.inflate(getLayoutId(),null);
            if (useEventBus()) {
                EventBus.getDefault().register(this);//注册eventBus
            }
        }
        ViewGroup parent= (ViewGroup) mRootView.getParent();
        if(parent!=null){
            parent.removeView(mRootView);
        }
        return mRootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imRefreshUtils=new IMRefreshUtils(getActivity());
        initView();
        initListener();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (useEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);//注销eventBus
            }
        }
    }
    /**
     * 加载中
     */
    protected void showLoadingDialog() {
        ShowLoadiongUtils.showLoadingDialogTypeTwo(getActivity(), getResources().getString(R.string.im_loading));
    }
    protected void dissLoadingDialog() {
        ShowLoadiongUtils.dismissLoadingDialogTypeTwo();
    }
    /**
     * 通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, boolean isFinish) {
        ActivityUtils.next(this,cls,isFinish);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    protected void startActivity(Class<?> cls, Bundle bundle, boolean isFinish) {
        ActivityUtils.next(this,cls,bundle,isFinish);
    }


    /**
     * Toast吐司
     **/
    protected void showMessage(String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 返回一个用于显示界面的布局id
     */
    protected abstract  int getLayoutId();

    /**
     * 初始化View的代码写在这个方法中
     */
    protected abstract void initView();

    /**
     * 初始化监听器的代码写在这个方法中
     */
    protected abstract void initListener();

    /**
     * 初始数据的代码写在这个方法中，用于从服务器获取数据
     */
    protected abstract void initData();


    protected abstract boolean useEventBus();

}
