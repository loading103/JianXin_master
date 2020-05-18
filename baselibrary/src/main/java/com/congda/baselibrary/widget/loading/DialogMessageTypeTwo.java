package com.congda.baselibrary.widget.loading;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.congda.baselibrary.R;

public class DialogMessageTypeTwo extends AlertDialog {

		private Context context;
		private ProgressBar progressBar;
		private View viewById;

		public DialogMessageTypeTwo(Context context) {
			super(context);
			this.createDialog(context);
			this.context = context;
		}

		public Dialog createDialog(Context context) {
			dialog = new Dialog(context, R.style.dialog);
			View view = View.inflate(context, R.layout.layout_loading_type_two, null);
			dialog.setContentView(view);
			viewById = view.findViewById(R.id.rootView);
			mTitle = (TextView) view.findViewById(R.id.loading_tv);
			progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
			this.context = context;
			return dialog;
		}

		public void setType(int  type){
			if (type == 1){
				viewById.setBackgroundResource(R.drawable.loading_bg);
				mTitle.setTextColor(Color.parseColor("#FFFFFF"));
				progressBar.setIndeterminateDrawable(getContext().getResources().getDrawable(R.drawable.loading_animation));
			}
		}

		public void  setProgress(final int  progress){
			if (progress == -1){
				mTitle.setVisibility(View.GONE);
				return;
			}
			mHandler.sendMessage(mHandler.obtainMessage(0,progress,0));
		}
		private Handler mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				int what = msg.what;
				if (what == 0) {    //update
					int progress = msg.arg1;
					if (progress >= 100){
						mTitle.setText("上传成功，正在重新加载数据");
						mHandler.postDelayed(new Runnable() {
							@Override
							public void run() {
								dissmissDialog();
							}
						},1000);
						return;
					}
					mTitle.setText("已上传:"+progress+"%");
				}
			}
		};

		private Dialog dialog;
		TextView mTitle;


		public void dissmissDialog() {
			if (dialog != null && dialog.isShowing()) {
				dialog.dismiss();
			}
		}
		public void setMessage(String msg){
			mTitle.setText(msg);
		}

		public void showDialog() {
			if (dialog != null) {
				dialog.setCanceledOnTouchOutside(false);
				dialog.show();
			}
		}
	}
