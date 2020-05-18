package com.congda.baselibrary.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.congda.baselibrary.mvp.BasePresenter;
import com.congda.baselibrary.mvp.IView;

/**
 * @author xuhao
 * @date 2018/6/9 17:12
 * @desc 基类 BaseMvpActivity
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements IView {
    protected T mPresenter;
    protected abstract T createPresenter();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        dissLoadingDialog();
    }
    @Override
    public void showToast(String msg) {
        showMessage(msg);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initView() {

    }
}
