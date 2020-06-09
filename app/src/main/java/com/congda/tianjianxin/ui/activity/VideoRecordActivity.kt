package com.congda.tianjianxin.ui.activity

import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.View
import com.cjt2325.cameralibrary.listener.JCameraListener
import com.congda.baselibrary.base.BaseActivity
import com.congda.baselibrary.utils.IMStatusBarUtil
import com.congda.tianjianxin.R
import kotlinx.android.synthetic.main.activity_record_video.*
import java.io.File


class VideoRecordActivity : BaseActivity(), JCameraListener {

    private var mPlayStarted = false//播放是否开始了
    override fun getLayoutId(): Int {
        return R.layout.activity_record_video
    }
    override fun initStatusBar() {
        IMStatusBarUtil.setTranslucentForImageView(this, 0, null)
    }
    override fun initView() {
        //设置视频保存路径
        jCameraView.setSaveVideoPath(Environment.getExternalStorageDirectory().path + File.separator + "JCamera")
    }
    override fun initListener() {
        //JCameraView监听
        jCameraView.setJCameraLisenter(this)
        jCameraView.setLeftClickListener{
            finish()
        }
    }


    override fun initData() {
    }

    override fun recordSuccess(url: String?, firstFrame: Bitmap?) {
        Log.i("recordSuccess", "url = $url")
    }

    override fun captureSuccess(bitmap: Bitmap?) {
        //获取图片bitmap
        Log.i("JCameraView", "bitmap = " + bitmap!!.width)
    }
    override fun onResume() {
        super.onResume()
        jCameraView.onResume()
    }

    override fun onPause() {
        super.onPause()
        jCameraView.onPause()
    }
    override fun onStart() {
        super.onStart()
//        //全屏显示
//        if (Build.VERSION.SDK_INT >= 19) {
//            val decorView = window.decorView
//            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    or View.SYSTEM_UI_FLAG_FULLSCREEN
//                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
//        } else {
//            val decorView = window.decorView
//            val option = View.SYSTEM_UI_FLAG_FULLSCREEN
//            decorView.systemUiVisibility = option
//        }
    }

}