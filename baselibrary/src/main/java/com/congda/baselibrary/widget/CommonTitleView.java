package com.congda.baselibrary.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.congda.baselibrary.R;


public class CommonTitleView extends LinearLayout {
    private RelativeLayout mRlLeft;
    private RelativeLayout mRlRight;
    private ImageView mIvFinish;
    private TextView mTvLeft;
    private TextView mTvTotle;
    private ImageView mIvRight;
    private TextView mTvRight;
    private View mTopView;
    private RelativeLayout mRlTop;

    OnClickListener leftListener;
    OnClickListener rightListener;

    public CommonTitleView(Context context) {
        super(context);
        initView(context);
    }

    public CommonTitleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CommonTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view =  LayoutInflater.from(context).inflate(R.layout.layout_common_title,this);
        mTvTotle = view.findViewById(R.id.tv_top_title);
        mRlLeft = view.findViewById(R.id.re_top_finish);
        mRlRight = view.findViewById(R.id.re_top_right);
        mIvFinish = view.findViewById(R.id.iv_top_finish);
        mTvLeft = view.findViewById(R.id.tv_top_finish);
        mTvRight = view.findViewById(R.id.tv_top_right);
        mIvRight = view.findViewById(R.id.iv_top_right);
        mTopView = view.findViewById(R.id.topview);
        mRlTop = view.findViewById(R.id.rl_top);


        mRlLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)context).finish();
                ((Activity)context).overridePendingTransition( R.anim.anim_out_from_right, R.anim.anim_out_from_right);
            }
        });
        mIvRight.setOnClickListener(rightListener);
    }

    public void setTopViewVisiable(){
        mTopView.setVisibility(VISIBLE);
    }
    public void setTopBgColor(int color){
        mRlTop.setBackgroundColor(color);
    }
    public void setTopViewColor(int color){
        mTopView.setVisibility(VISIBLE);
        mTopView.setBackgroundColor(color);
    }
    public void setTopTitle(String title){
        mTvTotle.setText(title);
    }
    public void setTopTitleColor(int color){
        mTvTotle.setText(color);
    }
    public void setTopLeftGone(){
        mIvFinish.setVisibility(GONE);
    }
    public void setTopLeftIco(int drawable){
        mIvFinish.setImageResource(drawable);
        mIvFinish.setVisibility(VISIBLE);
        mTvLeft.setVisibility(GONE);
    }
    public void setTopLeftText(String content){
        mIvFinish.setVisibility(GONE);
        mTvLeft.setVisibility(VISIBLE);
        mTvLeft.setText(content);
    }
    public void setTopLeftTextColor(int Color){
        mIvFinish.setVisibility(GONE);
        mTvLeft.setVisibility(VISIBLE);
        mTvLeft.setTextColor(Color);
    }

    public void setTopRightIco(int drawable){
        mIvRight.setImageResource(drawable);
        mIvRight.setVisibility(VISIBLE);
        mTvRight.setVisibility(GONE);
    }
    public void setTopRightText(String content){
        mIvRight.setVisibility(GONE);
        mTvRight.setVisibility(VISIBLE);
        mTvRight.setText(content);
    }
    public void setTopRightColor(int Color){
        mIvRight.setVisibility(GONE);
        mTvRight.setVisibility(VISIBLE);
        mTvRight.setTextColor(Color);
    }

    public void setOnClickRightListener(View.OnClickListener listener){
        mRlRight.setOnClickListener(listener);
    }
    public void setOnClickLeftListener(View.OnClickListener listener){
        mRlLeft.setOnClickListener(listener);
    }
}
