package com.congda.baselibrary.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 消息注册接收器
 */
public class BindServiceDemo extends Service implements Runnable{
    private Thread mThread;
    private  boolean isrunning=true;
    private final IBinder mBinder = new MyBind();

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
        if (mThread == null) {//如果线程为空 则创建一条
            mThread = new Thread(this);
            mThread.start();
        }
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        isrunning=false;
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("IMRecieve","服务关闭");
        super.onDestroy();
    }

    /**
     * 获取service实列
     */
    public class MyBind extends Binder {
        public BindServiceDemo getService() {
            return BindServiceDemo.this;
        }
    }


    @Override
    public void run() {
        while (isrunning){
            try {
                Thread.sleep(1000);
                Log.e("IMRecieve","服务后台运行中");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}