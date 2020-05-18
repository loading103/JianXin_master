package com.congda.tianjianxin.ui.activity.mvp.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.congda.baselibrary.base.BaseMvpActivity;
import com.congda.baselibrary.utils.IMStatusBarUtil;
import com.congda.tianjianxin.R;
import com.congda.tianjianxin.ui.activity.mvp.contract.ComWebViewContract;
import com.congda.tianjianxin.ui.activity.mvp.presenter.ComWebViewPresenter;
import com.just.agentweb.AgentWebConfig;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;


public class ComWebViewActivity extends BaseMvpActivity<ComWebViewPresenter> implements ComWebViewContract.View, View.OnClickListener {
    private LinearLayout mllRoot;
    private RelativeLayout mRlFinish;
    private RelativeLayout mRlTop;
    private TextView mTopTitle;
    private String url;
    private String title;


    private ValueCallback<Uri[]> mValueListCallback;
    public static final  int CHOOSE_PHOTO=10001;
    public static final  int CANAME_PHOTO=10002;
    @Override
    protected ComWebViewPresenter createPresenter() {
        return new ComWebViewPresenter();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_web;
    }

    @Override
    protected void initView() {
        mllRoot=findViewById(R.id.ll_root);
        mRlFinish=findViewById(R.id.re_top_finish);
        mTopTitle=findViewById(R.id.tv_top_title);
        mRlTop=findViewById(R.id.rl_top);
        url=getIntent().getStringExtra("url");
        title=getIntent().getStringExtra("title");
        mRlTop.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initListener() {
        mRlFinish.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.initData(this,mllRoot,url);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.re_top_finish :
                onBackPressed();
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (mPresenter.getWebView().canGoBack()) {
            mPresenter.getWebView().goBack();
        } else {
            finish();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.handleActivity(mValueListCallback,requestCode,data);
    }
    public WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.e("----","shouldOverrideUrlLoading:"+request.getUrl());
            return false;
        }

        //页面加载开始
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.e("----","onPageStarted:"+url);
        }
        //页面加载完成
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.e("----","onPageFinished:"+url);
        }
    };
    public WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
        }
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            mTopTitle.setText(title);
        }
        // For Android >= 5.0
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            mValueListCallback = filePathCallback;
            mPresenter.choosePhoto(ComWebViewActivity.this);
            return true;
        }
    };

    @Override
    public void setmValueListCallbackNull() {
        mValueListCallback=null;
    }
    @Override
    protected void onPause() {
        mPresenter.getmAgentWeb().getWebLifeCycle().onPause();
        super.onPause();

    }
    @Override
    protected void onResume() {
        mPresenter.getmAgentWeb().getWebLifeCycle().onResume();
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        AgentWebConfig.clearDiskCache(this);
        mPresenter.getmAgentWeb().getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
