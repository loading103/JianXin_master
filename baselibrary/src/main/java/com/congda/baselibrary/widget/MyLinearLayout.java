package com.congda.baselibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.congda.baselibrary.R;

/**
 * Created by cxf on 2018/9/25.
 * 动态加载间隔LinearLayout
 */

public class MyLinearLayout extends LinearLayout {

    private float mScreenWidth;
    private int mSpanCount;

    public MyLinearLayout(Context context) {
        this(context, null);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenWidth = context.getResources().getDisplayMetrics().widthPixels;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyLinearLayout);
        mSpanCount = ta.getInteger(R.styleable.MyLinearLayout_mll_span_count, 4);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) (mScreenWidth / mSpanCount), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
