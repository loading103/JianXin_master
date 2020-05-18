package com.congda.baselibrary.widget.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.congda.baselibrary.R;


public class IMSheetViewDialog extends DialogFragment {

    private View view;
    private AlertDialog alertDialog;

    public interface Callback {
        void onClick(int position);
    }

    private Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void shows(FragmentManager fragmentManager, Callback callback) {
        this.callback=callback;
        show(fragmentManager, "ViewDialogFragment");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimations;
        alertDialog.setView(new EditText(getContext()));
        if (!(getActivity()).isFinishing()) {
            alertDialog.show();
        }
        final Window window = alertDialog.getWindow();
        window.getDecorView().setBackgroundColor(getActivity().getResources().getColor(R.color.translate));
        window.getDecorView().setPadding(0, 0, 0, 0);//去掉padding

        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.layout_sheet_view, null);

        window.setContentView(view);
        window.setLayout(window.getContext().getResources().getDisplayMetrics().widthPixels, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM);

        alertDialog.setCanceledOnTouchOutside(true);

        initView();
        return alertDialog;
    }

    private void initView() {
        LinearLayout llHome = view.findViewById(R.id.ll_home);
        LinearLayout llCollection = view.findViewById(R.id.ll_collection);
        LinearLayout llShare = view.findViewById(R.id.ll_share);

        llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMSheetViewDialog.this.dismiss();
                if(callback!=null){
                    callback.onClick(0);
                }

            }
        });
        llCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMSheetViewDialog.this.dismiss();
                if(callback!=null){
                    callback.onClick(1);
                }
            }
        });
        llShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMSheetViewDialog.this.dismiss();
                if(callback!=null){
                    callback.onClick(2);
                }
            }
        });
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        callback = null;
    }
}
