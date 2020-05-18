package com.congda.tianjianxin.ui.activity.mvp.presenter;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.congda.baselibrary.mvp.BasePresenter;
import com.congda.baselibrary.utils.glide.IMChooseUtils;
import com.congda.baselibrary.widget.dialog.IMSheetDialog;
import com.congda.tianjianxin.ui.activity.mvp.contract.ComWebViewContract;
import com.congda.tianjianxin.ui.activity.mvp.model.ComWebViewModel;
import com.congda.tianjianxin.ui.activity.mvp.ui.ComWebViewActivity;
import com.congda.tianjianxin.utils.AndroidInterface;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

import static com.congda.tianjianxin.ui.activity.mvp.ui.ComWebViewActivity.CANAME_PHOTO;
import static com.congda.tianjianxin.ui.activity.mvp.ui.ComWebViewActivity.CHOOSE_PHOTO;

public class ComWebViewPresenter extends BasePresenter<ComWebViewContract.Model,ComWebViewContract.View> {
    private ValueCallback<Uri> mValueCallback;
    private ValueCallback<Uri[]> mValueListCallback;
    private AgentWeb mAgentWeb;
    private WebView webView;
    private AndroidInterface androidInterface;
    @Override
    protected ComWebViewContract.Model createModel() {
        return new ComWebViewModel();
    }
    public AgentWeb getmAgentWeb() {
        return mAgentWeb;
    }

    public WebView getWebView() {
        return webView;
    }

    public AndroidInterface getAndroidInterface() {
        return androidInterface;
    }
    /**
     * 初始化
     */

    public void initData(Context context, LinearLayout mllRoot,String url) {
        mAgentWeb = AgentWeb.with((ComWebViewActivity)context)
                .setAgentWebParent(mllRoot, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .setWebChromeClient(((ComWebViewActivity)getView()).mWebChromeClient)
                .setWebViewClient(((ComWebViewActivity)getView()).mWebViewClient)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .createAgentWeb()
                .ready()
                .go(url);

        webView = mAgentWeb.getWebCreator().getWebView();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webView.getSettings().setAppCacheEnabled(true);
        // 设置允许JS弹窗
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        androidInterface = new AndroidInterface(mAgentWeb, context);
        mAgentWeb.getJsInterfaceHolder().addJavaObject("android", androidInterface);
    }
    /**
     * 选择图片
     */
    public void choosePhoto(Context context) {
        new IMSheetDialog.Builder(context)
                .addSheet("拍照", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        ImageSelector.builder()
                                .setCrop(true) // 设置是否使用图片剪切功能。
                                .setCropRatio(1.0f) // 图片剪切的宽高比,默认1.0f。宽固定为手机屏幕的宽。
                                .onlyTakePhoto(true)  // 仅拍照，不打开相册
                                .start((ComWebViewActivity)context, CANAME_PHOTO);
                    }
                })
                .addSheet("选择图片", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        IMChooseUtils.choosePhotoForResult(context,CHOOSE_PHOTO,9);
                    }
                })
                .create().show();
    }
    /**
     * 处理选择文件之后
     */
    public void handleActivity(ValueCallback<Uri[]> mValueListCallback, int requestCode, Intent data) {
        if(data==null && mValueListCallback!=null){
            mValueListCallback.onReceiveValue(null);
            getView().setmValueListCallbackNull();
            return;
        }
        if(requestCode==CHOOSE_PHOTO && mValueListCallback!=null){
            List<Uri> uris = Matisse.obtainResult(data);
            Uri[] uridatas = uris.toArray(new Uri[uris.size()]);
            mValueListCallback.onReceiveValue(uridatas);
            getView().setmValueListCallbackNull();
        }else if(requestCode==CANAME_PHOTO && mValueListCallback!=null){
            ArrayList<String> datas = data.getStringArrayListExtra(ImageSelector.SELECT_RESULT);
            Uri uri = Uri.parse(datas.get(0));
            Uri[] urix = { uri };
            mValueListCallback.onReceiveValue(urix);
            getView().setmValueListCallbackNull();
        }
    }






}
