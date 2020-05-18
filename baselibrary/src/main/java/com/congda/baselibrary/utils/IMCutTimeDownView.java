package com.congda.baselibrary.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.congda.baselibrary.R;

import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("AppCompatCustomView")
public class IMCutTimeDownView extends TextView {
    //等待总时长
    private  long totalTime = 5000;
    //广告时长
    private long adTime = 0;

    private Timer timer;

    private Context  context;
    public IMCutTimeDownView(Context context) {
        this(context, null);
    }

    public IMCutTimeDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IMCutTimeDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context=context;
        setBackground(context.getResources().getDrawable(R.drawable.im_shape_bg_gray_c6));
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
        setVisibility(View.VISIBLE);
        countDownNext(totalTime);
    }
    public void setOnDestoryed() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    private void countDownNext(final long times){
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (adTime >= times) {
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                    }
                    ((Activity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(onFinishListener!=null){
                                onFinishListener.setOnFinishListener();
                            }
                        }
                    });
                }
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setText((totalTime - adTime)/1000+" | 跳过");
                        adTime= adTime+1000;
                    }
                });
            }
        }, 0, 1000);
    }

    public  interface  OnFinishListener{
        void   setOnFinishListener();
    }
    private OnFinishListener onFinishListener;

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }
}
