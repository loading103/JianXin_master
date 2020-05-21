package com.congda.baselibrary.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.congda.baselibrary.R;

public class AnimatorUtil {
    private Context context;
    private long lastTime=0;
    private long DuringTime=2000;
    public AnimatorUtil(Context context) {
        this.context=context;
    }

    /**
     * 这个属性动画中的ValueAnimator动画   还有一个ObjectAnimator动画
     */
    public void performAnim(boolean show, View mTvStase){
        //属性动画对象
        ValueAnimator va ;
        //隐藏view，高度从height变为0()
        if(System.currentTimeMillis()-lastTime<DuringTime){
            return;
        }
        lastTime=System.currentTimeMillis();
        if(show){
            va = ValueAnimator.ofInt(IMDensityUtil.dip2px(context,35), IMDensityUtil.dip2px(context,100));
        }else{
            va = ValueAnimator.ofInt(IMDensityUtil.dip2px(context,100),IMDensityUtil.dip2px(context,35));
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
//                if (show) {
//                    mTvStase.setVisibility(View.VISIBLE);
//                }
                super.onAnimationStart(animation);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
//                if (!show) {
//                    mTvStase.setVisibility(View.GONE);
//                }
            }
        });
        va.setDuration(DuringTime);
        va.start();
    }

    /**
     * ObjectAnimator动画
     */

    @SuppressLint("WrongConstant")
    public void  objectAnimation(View view) {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.3f, 1.0F);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.5f);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(view, "translationX", 0.0f, 350.0f, 0.0f);
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(view, "rotationX", 0.0f, 180.0f,0.0F,90f);
        //监听变化用
        objectAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

            }
        });
        objectAnimator1.setDuration(2000);
        objectAnimator2.setDuration(2000);
        objectAnimator3.setDuration(2000);
        objectAnimator4.setDuration(2000);

        objectAnimator1.setRepeatCount(Animation.INFINITE);
        objectAnimator2.setRepeatCount(Animation.INFINITE);
        objectAnimator3.setRepeatCount(Animation.INFINITE);
        objectAnimator4.setRepeatCount(Animation.INFINITE);

        objectAnimator1.setRepeatMode(Animation.RESTART);
        objectAnimator2.setRepeatMode(Animation.RESTART);
        objectAnimator3.setRepeatMode(Animation.RESTART);
        objectAnimator4.setRepeatMode(Animation.RESTART);

        objectAnimator1.start();
        objectAnimator2.start();
        objectAnimator3.start();
        objectAnimator4.start();
    }

    /**
     * 旋转动画
     */
    @SuppressLint("WrongConstant")
    public void  roteAnimation(View view, boolean istrue) {
        Animation operatingAnim  = AnimationUtils.loadAnimation(context, R.anim.anim_rotate_360);
        operatingAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        operatingAnim.setRepeatCount(10);
        operatingAnim.setFillAfter(true);
        view.startAnimation(operatingAnim);
    }

    /**
     * 帧动画
     */
    @SuppressLint("WrongConstant")
    public void  FrameAnimation(View view) {
        AnimationDrawable animationDrawable = (AnimationDrawable) ((ImageView)view).getDrawable();
        //判断是否在运行
        if(!animationDrawable.isRunning()){
            //开启帧动画
            animationDrawable.setOneShot(true); //为true时 转一次  停留在最后一帧
            animationDrawable.start();
        }else {
            animationDrawable.stop();
            animationDrawable=null;
        }
    }
}