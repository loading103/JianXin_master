package com.congda.baselibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.congda.baselibrary.R;
import com.suke.widget.SwitchButton;

/**
 * Created by Wolf on 2019/11/21.
 * Describe:设置选项
 */
public class ComLinearView extends RelativeLayout {
    private ImageView ivLeft;
    private TextView tvRight;
    private TextView tvContent;

    public SwitchButton getSwitchButton() {
        return switchButton;
    }

    private SwitchButton switchButton;
    private View vLine;
    private ImageView ivRight;

    public ComLinearView(Context context) {
        this(context, null);
    }

    public ComLinearView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ComLinearView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_common_linear,this);
        initView(view);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.SettingsOptionView);
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            initAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    private void initAttr(int attr, TypedArray typedArray) {
        if (attr == R.styleable.SettingsOptionView_left_img) {
            //设置图标
            setLeftImg(typedArray.getResourceId(attr,R.mipmap.im_icon_stub));
        }
        if (attr == R.styleable.SettingsOptionView_text_content) {
            //设置文本
            setContent(typedArray.getText(attr));
        }
        if (attr == R.styleable.SettingsOptionView_is_Check) {
            //设置状态
            setCheck(typedArray.getBoolean(attr,false));
        }

        if (attr == R.styleable.SettingsOptionView_is_show_line) {
            //设置状态
            setShowLine(typedArray.getBoolean(attr,true));
        }

        if (attr == R.styleable.SettingsOptionView_is_show_right_arr) {
            //设置显示箭头
            setShowArr(typedArray.getBoolean(attr,true));
        }
        if (attr == R.styleable.SettingsOptionView_text_right) {
            //设置显示箭头
            setRightText(typedArray.getText(attr));
        }

    }



    private void initView(View view) {
        ivLeft=view.findViewById(R.id.iv_left);
        tvContent=view.findViewById(R.id.tv_content);
        switchButton=view.findViewById(R.id.switch_button);
        vLine=view.findViewById(R.id.v_line);
        ivRight=view.findViewById(R.id.iv_right);
        tvRight=view.findViewById(R.id.tv_right);
    }

    public void setLeftImg(int resId){
        ivLeft.setImageResource(resId);
    }
    public void setContent(CharSequence  content){
        tvContent.setText(content);
    }
    public void setCheck(boolean isCheck){
        switchButton.setChecked(isCheck);
    }
    public void setShowLine(boolean isShow){
        vLine.setVisibility(isShow?View.VISIBLE:View.INVISIBLE);
    }
    public void setRightText(CharSequence  content) {
        tvRight.setText(content);
    }
    public void setShowArr(boolean isShow){
        switchButton.setVisibility(!isShow?View.VISIBLE:View.GONE);
        ivRight.setVisibility(isShow?View.VISIBLE:View.INVISIBLE);
    }

}
