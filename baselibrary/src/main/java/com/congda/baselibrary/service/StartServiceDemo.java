package com.congda.baselibrary.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.congda.baselibrary.utils.IMNetworkUtil;


public class StartServiceDemo extends Service implements Runnable{
    private Thread netThread;
    private boolean  isconnected=true;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        netThread=new Thread(this);
        netThread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 监听第一次来网
     */
    @Override
    public void run() {
        while (isconnected) {
            try {
                Thread.sleep(1000);
                if (!IMNetworkUtil.isConnected()) {
                    isconnected = true;
                    Log.e("---------","无网了");
                } else {
                    isconnected = false;
                    Log.e("---------","有网了");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
