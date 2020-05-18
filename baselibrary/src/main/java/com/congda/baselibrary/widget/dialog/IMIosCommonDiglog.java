package com.congda.baselibrary.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.congda.baselibrary.R;
import com.congda.baselibrary.utils.IMDensityUtil;

public class IMIosCommonDiglog {
    private Context context;
    private AlertDialog shareDialog;
    private TextView mTvTitle;
    private TextView mTvContent;
    private TextView mTvCancle;
    private TextView mTvSure;
    private View line;

    public IMIosCommonDiglog(Context context) {
        this.context=context;
    }

    public void showCommonDiglog(String content, View.OnClickListener listener) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setCancelable(false);
        final View dialogView =  View.inflate(context, R.layout.layout_dialog_common, null);
        mTvTitle = dialogView.findViewById(R.id.im_tv_title);
        mTvContent = dialogView.findViewById(R.id.im_tv_content);
        mTvCancle = dialogView.findViewById(R.id.tv_cancle);
        mTvSure = dialogView.findViewById(R.id.tv_ensure);
        if(!TextUtils.isEmpty(content)){
            mTvContent.setText(content);
        }
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareDialog.dismiss();
                listener.onClick(mTvSure);
            }
        });
        mTvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareDialog.dismiss();
            }
        });
        builder.setView(dialogView);
        shareDialog =  builder.show();
        Window window = shareDialog.getWindow();
        window.setWindowAnimations(R.style.ActionSheetDialogAnimations);  //添加动画
        WindowManager.LayoutParams params = window.getAttributes();

        params.width = IMDensityUtil.getScreenWidth(context)- IMDensityUtil.dip2px(context,70);
        shareDialog.getWindow().setAttributes(params);
        shareDialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.im_shape_bg_white_c12));
    }

    public void showSingleCommonDiglog(String content, View.OnClickListener listener) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setCancelable(false);
        final View dialogView =  View.inflate(context, R.layout.layout_dialog_common, null);
        mTvTitle = dialogView.findViewById(R.id.im_tv_title);
        mTvContent = dialogView.findViewById(R.id.im_tv_content);
        mTvCancle = dialogView.findViewById(R.id.tv_cancle);
        mTvSure = dialogView.findViewById(R.id.tv_ensure);
        line = dialogView.findViewById(R.id.views);
        mTvCancle.setVisibility(View.GONE);
        line.setVisibility(View.GONE);

        if(!TextUtils.isEmpty(content)){
            mTvContent.setText(content);
        }
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareDialog.dismiss();
                listener.onClick(mTvSure);
            }
        });
        builder.setView(dialogView);
        shareDialog =  builder.show();
        Window window = shareDialog.getWindow();
        window.setWindowAnimations(R.style.ActionSheetDialogAnimations);  //添加动画
        WindowManager.LayoutParams params = window.getAttributes();

        params.width = IMDensityUtil.getScreenWidth(context)- IMDensityUtil.dip2px(context,70);
        shareDialog.getWindow().setAttributes(params);
        shareDialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.im_shape_bg_white_c12));
    }
    public void showSingleCommonDiglog(String content) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setCancelable(false);
        final View dialogView =  View.inflate(context, R.layout.layout_dialog_common, null);
        mTvTitle = dialogView.findViewById(R.id.im_tv_title);
        mTvContent = dialogView.findViewById(R.id.im_tv_content);
        mTvCancle = dialogView.findViewById(R.id.tv_cancle);
        mTvSure = dialogView.findViewById(R.id.tv_ensure);
        line = dialogView.findViewById(R.id.views);
        mTvCancle.setVisibility(View.GONE);
        line.setVisibility(View.GONE);

        if(!TextUtils.isEmpty(content)){
            mTvContent.setText(content);
        }
        mTvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
            }
        });
        mTvCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog.dismiss();
            }
        });
        builder.setView(dialogView);
        shareDialog =  builder.show();
        Window window = shareDialog.getWindow();
        window.setWindowAnimations(R.style.ActionSheetDialogAnimations);  //添加动画
        WindowManager.LayoutParams params = window.getAttributes();

        params.width = IMDensityUtil.getScreenWidth(context)- IMDensityUtil.dip2px(context,70);
        shareDialog.getWindow().setAttributes(params);
        shareDialog.getWindow().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.im_shape_bg_white_c12));
    }
    public void dismissCommonDiglog() {
        if(shareDialog!=null && shareDialog.isShowing()){
            shareDialog.dismiss();
        }
    }

}
