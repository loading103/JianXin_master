package com.congda.baselibrary.service;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.congda.baselibrary.app.IMSConfig;
import com.congda.baselibrary.net.download.DownLoadManager;
import com.congda.baselibrary.net.download.ProgressCallBack;
import com.congda.baselibrary.utils.IMNetworkUtil;
import com.congda.baselibrary.utils.IMTimeUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;


public class DownLoadService extends Service {
    private final IBinder mBinder = new DownLoadService.DownBind();
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, START_STICKY, startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    /**
     * 获取service实列
     */
    public class DownBind extends Binder {
        public DownLoadService getService() {
            return DownLoadService.this;
        }
    }
    public  void downLoadFile(String url) {
        String name= IMTimeUtils.stampToTime(System.currentTimeMillis() + "", null) + ".mp4";
        DownLoadManager.getInstance().download(url, new ProgressCallBack( IMSConfig.VIDEO_PATH,name) {
            @Override
            public void onSuccess(Object o) {
                if(processListener!=null){
                    processListener.onProcessListener(100,"");
                }
            }

            @Override
            public void progress(long progress, long total) {
                if(processListener!=null){
                    processListener.onProcessListener((int)(progress*1.0f/total *100),"");
                }
            }

            @Override
            public void onError(Throwable e) {
                if(processListener!=null){
                    processListener.onProcessListener(0,e.getMessage());
                }
            }
        });
    }

    public interface  ProcessListener{
        void  onProcessListener(int pross,String erro);
    }
    private  ProcessListener processListener;

    public void setProcessListener(ProcessListener processListener) {
        this.processListener = processListener;
    }
}
