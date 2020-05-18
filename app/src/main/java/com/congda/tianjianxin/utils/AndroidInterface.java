package com.congda.tianjianxin.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.just.agentweb.AgentWeb;

/**
 * Created by Administrator on 2019/12/26.
 * Describe:
 */
public class AndroidInterface {
    private Handler deliver = new Handler(Looper.getMainLooper());
    private AgentWeb agent;
    private Context context;

    public AndroidInterface(AgentWeb agent, Context context) {
        this.agent = agent;
        this.context = context;
    }

    @JavascriptInterface
    public void ChoosePickure(String token) {

    }

    @JavascriptInterface
    public void recordAudio(final String msg) {
        Log.i("WOLF", "Thread:" + msg);
    }


}
