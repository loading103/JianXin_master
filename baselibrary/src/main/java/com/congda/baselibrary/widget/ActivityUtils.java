package com.congda.baselibrary.widget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import com.congda.baselibrary.R;

public class ActivityUtils {

    public static void next(Activity activity, Class<?> cls) {
        activity.startActivity(new Intent(activity, cls));
        activity.overridePendingTransition(R.anim.anim_in_from_right, R.anim.fade_out);
    }

    public static void next(Activity activity, Class<?> cls, Bundle extras, boolean isFinish) {
        Intent intent = new Intent(activity, cls);
        intent.putExtras(extras);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.anim_in_from_right, R.anim.fade_out);
        if (isFinish) {
            activity.finish();
        }
    }

    public static void next(Activity activity, Class<?> cls, boolean isFinish) {
        activity.startActivity(new Intent(activity, cls));
        activity.overridePendingTransition(R.anim.anim_in_from_right, R.anim.fade_out);
        if (isFinish) {
            activity.finish();
        }
    }

    public static void next(Activity activity, Class<?> cls, int reqCode, boolean isFinish) {
        activity.startActivityForResult(new Intent(activity, cls), reqCode);
        activity.overridePendingTransition(R.anim.anim_in_from_right, R.anim.fade_out);
        if (isFinish) {
            activity.finish();
        }
    }

    public static void next(Fragment fragment, Class<?> cls) {
        fragment.startActivity(new Intent(fragment.getActivity(), cls));
        fragment.getActivity().overridePendingTransition(R.anim.anim_in_from_right, R.anim.fade_out);
    }

    public static void next(Fragment fragment, Class<?> cls, Bundle extras, boolean isFinish) {
        Intent intent = new Intent(fragment.getActivity(), cls);
        intent.putExtras(extras);
        fragment.startActivity(intent);
        fragment.getActivity().overridePendingTransition(R.anim.anim_in_from_right, R.anim.fade_out);
        if (isFinish) {
            fragment.getActivity().finish();
        }
    }

    public static void next(Fragment fragment, Class<?> cls, boolean isFinish) {
        fragment.startActivity(new Intent(fragment.getActivity(), cls));
        fragment.getActivity().overridePendingTransition(R.anim.anim_in_from_right, R.anim.fade_out);
        if (isFinish) {
            fragment.getActivity().finish();
        }
    }


    public static void next(Fragment fragment, Class<?> cls, int reqCode, boolean isFinish) {
        fragment.startActivityForResult(new Intent(fragment.getActivity(), cls), reqCode);
        fragment.getActivity().overridePendingTransition(R.anim.anim_in_from_right, R.anim.fade_out);
        if (isFinish) {
            fragment.getActivity().finish();
        }
    }
}
