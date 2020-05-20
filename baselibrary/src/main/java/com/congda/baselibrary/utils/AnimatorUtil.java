package com.congda.baselibrary.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;

public class AnimatorUtil {
    private Context context;
    private long lastTime=0;
    private long DuringTime=1000;
    public AnimatorUtil(Context context) {
        this.context=context;
    }

    public void performAnim(boolean show, View mTvStase){
        //属性动画对象
        ValueAnimator va ;
        //隐藏view，高度从height变为0()
        if(System.currentTimeMillis()-lastTime<DuringTime){
            return;
        }
        lastTime=System.currentTimeMillis();
        if(show){
            va = ValueAnimator.ofInt(0, IMDensityUtil.dip2px(context,35));
        }else{
            va = ValueAnimator.ofInt(IMDensityUtil.dip2px(context,35),0);
        }
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取当前的height值
                Log.e("-------",(Integer)valueAnimator.getAnimatedValue()+"");
                int h =(Integer)valueAnimator.getAnimatedValue();
                //动态更新view的高度
                mTvStase.getLayoutParams().height = h;
                mTvStase.requestLayout();
            }
        });

        va.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (show) {
                    mTvStase.setVisibility(View.VISIBLE);
                }
                super.onAnimationStart(animation);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!show) {
                    mTvStase.setVisibility(View.GONE);
                }
            }
        });
        va.setDuration(DuringTime);
        va.start();
    }

}