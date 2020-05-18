package com.congda.baselibrary.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.congda.baselibrary.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

public class MyChatHeadView extends LinearLayout implements RefreshHeader {

    private TextView mHeaderText;//标题文本
    private ImageView miv;//标题文本
    private ObjectAnimator anim;

    public MyChatHeadView(Context context) {
        super(context);
        initView(context);
    }
    public MyChatHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }
    public MyChatHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }
    private void initView(Context context) {
        setGravity(Gravity.CENTER);
        View view = LayoutInflater.from(context).inflate(R.layout.layout_header_type_one, this);
        mHeaderText=view.findViewById(R.id.tv_time);
        miv=view.findViewById(R.id.anim_loding);
        anim = ObjectAnimator.ofInt(miv, "ImageLevel", 0, 10000);
        anim.setDuration(2000);
        anim.setRepeatCount(ObjectAnimator.INFINITE);
    }
    @NonNull
    public View getView() {
        return this;//真实的视图就是自己，不能返回null
    }
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }
    @Override
    public void onStartAnimator(RefreshLayout layout, int headHeight, int maxDragHeight) {
    }
    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        miv.setVisibility(GONE);//开始动画
        anim.cancel();
        clearFocus();
        return 100;//延迟500毫秒之后再弹回
    }
    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                miv.setVisibility(VISIBLE);//开始动画
                anim.start();
                break;
            case Refreshing:
                break;
            case ReleaseToRefresh:
                break;
        }
    }
    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }
    @Override
    public void onInitialized(RefreshKernel kernel, int height, int maxDragHeight) {
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }
    @Override
    public void setPrimaryColors(@ColorInt int ... colors){
    }
}