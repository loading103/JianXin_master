package com.congda.baselibrary.widget.dialogfragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.congda.baselibrary.R;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebViewClient;

/**
 * 彩乐讯用户协议
 */
public class LoginAgreeDialog extends BaseDialog implements View.OnClickListener {
    private LinearLayout mllcontain1;
    private LinearLayout mllcontain2;
    private TextView mTv1;
    private TextView mTv2;
    private View view1;
    private View view2;
    private LinearLayout mllroot;
    private TextView mTvCancle;
    private TextView mTvSure;
    private Context context;
    private AgentWeb mAgentWeb;
    @Override
    protected int getLayoutId() {
        return R.layout.layout_dialog_login;
    }

    @Override
    protected int getDialogStyle() {
        return R.style.dialog2;
    }

    @Override
    protected boolean canCancel() {
        return true;
    }

    @Override
    protected void setWindowAttributes(Window window) {
        window.setWindowAnimations(R.style.DialogZoomAnimations);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        mllcontain1 = (LinearLayout) findViewById(R.id.ll_contain_1);
        mllcontain2 = (LinearLayout) findViewById(R.id.ll_contain_2);
        mTv1 = (TextView) findViewById(R.id.im_tv_1);
        mTv2 = (TextView) findViewById(R.id.im_tv_2);
        view1 = findViewById(R.id.im_lin_1);
        view2 = findViewById(R.id.im_lin_2);
        mllroot = (LinearLayout) findViewById(R.id.ll_root);
        mTvCancle = (TextView) findViewById(R.id.tv_cancle);
        mTvSure = (TextView) findViewById(R.id.tv_ensure);
        initAgeView();
        initListener();
    }

    private void initListener() {
        mllcontain1.setOnClickListener(this);
        mllcontain2.setOnClickListener(this);
        mTvSure.setOnClickListener(this);
        mTvCancle.setOnClickListener(this);
    }

    private void initAgeView() {
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mllroot, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .setWebViewClient(mWebViewClient)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .createAgentWeb()
                .ready()
                .go("https://www.baidu.com");

    }

    /**
     * webView进度监听
     */
    public WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false;
        }

        //页面加载开始
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        //页面加载完成
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    };


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ll_contain_1) {
            mTv1.setTextColor(getResources().getColor(R.color.colorPrimary));
            mTv2.setTextColor(getResources().getColor(R.color.color_666666));
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.GONE);
            mAgentWeb.getWebCreator().getWebView().loadUrl("https://www.baidu.com");

        }else if (view.getId() == R.id.ll_contain_2) {
            mTv1.setTextColor(getResources().getColor(R.color.color_666666));
            mTv2.setTextColor(getResources().getColor(R.color.colorPrimary));
            view1.setVisibility(View.GONE);
            view2.setVisibility(View.VISIBLE);
            mAgentWeb.getWebCreator().getWebView().loadUrl("https://www.4399.com");

        }else if (view.getId() == R.id.tv_cancle) {
            getDialog().dismiss();

        }else if (view.getId() == R.id.tv_ensure) {
            getDialog().dismiss();
        }
    }
}
