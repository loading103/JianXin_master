package com.congda.baselibrary.widget.loading;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.congda.baselibrary.R;


public class ShowLoadiongUtils {
    private static ProgressDialog pd;
    private static DialogMessageTypeOne loadingDialog;
    private static DialogMessageTypeTwo dialogMessage;
    private static DialogMessageTypeTwo progressDialog;


    /**
     * 通用Dialog弹窗
     */
    public static  void showDialog(Context context,String title, String message, DialogInterface.OnClickListener listener ){
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(context);
        normalDialog.setTitle(title);
        normalDialog.setMessage(message);
        normalDialog.setPositiveButton("确定", listener);
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        // 显示
        normalDialog.show();
    }
    /**
     * 显示加载界面loading(自带)
     */
    public static void showLoadingTypeZero(Context context) {
        if(pd!=null&&pd.isShowing()){
            pd.dismiss();
        }
        pd = new ProgressDialog(context);
        pd.setMessage(context.getResources().getString(R.string.im_loading_text));
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setCancelable(true);
        pd.show();
    }

    public static void dissloadingTypeZero() {
        if(pd!=null&&pd.isShowing()){
            pd.dismiss();
        }
    }
    /**
     * 显示加载界面loading(第1种自定义)
     */
    public static void showLoadingDialogTypeOne(Context context) {
        if (loadingDialog!=null && loadingDialog.isshow()) {
            loadingDialog.dismiss();
            loadingDialog=null;
        }
        loadingDialog = new DialogMessageTypeOne(context);
        loadingDialog.set_progress(context.getResources().getString(R.string.im_loading_text));
        loadingDialog.show();
    }

    public static void dismissLoadingDialogTypeOne() {
        if (loadingDialog != null && loadingDialog.isshow()) {
            loadingDialog.dismiss();
            loadingDialog=null;
        }
    }
    /**
     * 显示加载界面loading(第2种自定义)
     */
    public static void showLoadingDialogTypeTwo(Context context,String msg) {
        if (dialogMessage != null) {
            dialogMessage.dissmissDialog();
            dialogMessage = null;
        }
        dialogMessage = new DialogMessageTypeTwo(context);
        dialogMessage.setType(1);
        if(!TextUtils.isEmpty(msg)){
            dialogMessage.setMessage(msg);
        }
        dialogMessage.showDialog();
    }

    public static void dismissLoadingDialogTypeTwo() {
        if (dialogMessage != null) {
            dialogMessage.dissmissDialog();
            dialogMessage = null;
        }
    }
    /**
     * 显示加载界面loading(带进度条)
     */
    public static void showLoadingDialogProgress(Context context,int progeress) {
        if (progressDialog == null) {
            progressDialog = new DialogMessageTypeTwo(context);
            progressDialog.setType(1);
            progressDialog.showDialog();
        }
        if(!progressDialog.isShowing()){
            progressDialog.showDialog();
        }
        progressDialog.setProgress(progeress);
    }
    public static void dismissProgress() {
        progressDialog.dissmissDialog();
        progressDialog = null;
    }
}
