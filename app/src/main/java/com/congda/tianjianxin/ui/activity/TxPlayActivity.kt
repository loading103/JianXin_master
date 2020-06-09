package com.congda.tianjianxin.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import cc.shinichi.library.tool.ui.ToastUtil
import com.congda.baselibrary.base.BaseActivity
import com.congda.tianjianxin.R
import com.tencent.rtmp.ITXLivePlayListener
import com.tencent.rtmp.TXLiveConstants
import com.tencent.rtmp.TXLivePlayConfig
import com.tencent.rtmp.TXLivePlayer
import kotlinx.android.synthetic.main.activity_tx_video.*


class TxPlayActivity : BaseActivity(), ITXLivePlayListener {

    private var mCurrentRenderMode = 0// player 渲染模式
    private var mCurrentRenderRotation = 0
    var  mLivePlayer: TXLivePlayer?=null
    lateinit var  mPlayConfig: TXLivePlayConfig
    private val mActivityType = 0
    private var mPlayType =  TXLivePlayer.PLAY_TYPE_LOCAL_VIDEO // player 播放链接类型
    val ACTIVITY_TYPE_PUBLISH = 1
    val ACTIVITY_TYPE_LIVE_PLAY = 2
    val ACTIVITY_TYPE_VOD_PLAY = 3
    val ACTIVITY_TYPE_LINK_MIC = 4
    val ACTIVITY_TYPE_REALTIME_PLAY = 5

    private var mPlayStarted = false//播放是否开始了
    override fun getLayoutId(): Int {
        return R.layout.activity_tx_video
    }

    override fun initView() {
        mCurrentRenderMode = TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION
        mCurrentRenderRotation = TXLiveConstants.RENDER_ROTATION_PORTRAIT
        mPlayConfig = TXLivePlayConfig()
    }
    override fun initListener() {

        btnOrientation.setOnClickListener(View.OnClickListener {
            if (mLivePlayer == null) {
                return@OnClickListener
            }
            if (mCurrentRenderRotation == TXLiveConstants.RENDER_ROTATION_PORTRAIT) {
                mCurrentRenderRotation = TXLiveConstants.RENDER_ROTATION_LANDSCAPE
            } else if (mCurrentRenderRotation == TXLiveConstants.RENDER_ROTATION_LANDSCAPE) {
                mCurrentRenderRotation = TXLiveConstants.RENDER_ROTATION_PORTRAIT
            }

            mLivePlayer!!.setRenderRotation(mCurrentRenderRotation)
        })
    }
    override fun initData() {
        startPlay()
    }

    private fun startPlay():Boolean {
        val  playUrl="http://47.75.111.156/data/upload/video/1.mp4"
        mLivePlayer = TXLivePlayer(this)
        mLivePlayer?.setConfig(TXLivePlayConfig())
        mLivePlayer?.setPlayerView(mPlayerView)
        mLivePlayer?.enableHardwareDecode(false)
        mLivePlayer?.setRenderRotation(TXLiveConstants.RENDER_ROTATION_PORTRAIT)
        mLivePlayer?.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN)
        mLivePlayer?.setPlayListener(this)
        var result = mLivePlayer?.startPlay( playUrl, mPlayType) // result返回值：0 success;  -1 empty url; -2 invalid url; -3 invalid playType;
        if (result == 0) {
            ToastUtil.getInstance()._short(this, "播放成功$result")
            mPlayStarted = true
            return false
        }
        return true
    }



    override fun onPlayEvent(p0: Int, p1: Bundle?) {
        Log.e("------p0",p0.toString())
        when (p0) {
            TXLiveConstants.PLAY_EVT_PLAY_END ->
                onReplay()
            TXLiveConstants.PLAY_EVT_CHANGE_RESOLUTION ->
                onVideoSizeChanged(0f, 0f)
        }
    }

    override fun onNetStatus(p0: Bundle?) {
    }
    /**
     * 循环播放
     */
    private fun onReplay() {
        if (mPlayStarted && mLivePlayer != null) {
            mLivePlayer?.seek(0)
            mLivePlayer?.resume()
        }
    }

    /**
     * 获取到视频宽高回调
     */
    fun onVideoSizeChanged(videoWidth: Float, videoHeight: Float) {
        if (mPlayerView != null && videoWidth > 0 && videoHeight > 0) {
            val params = mPlayerView.layoutParams as FrameLayout.LayoutParams
            if (videoWidth / videoHeight > 0.5625f) { //横屏 9:16=0.5625
                params.height = ((mPlayerView.width / videoWidth * videoHeight).toInt())
                params.gravity = Gravity.CENTER
                mPlayerView.requestLayout()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayStarted = false
        if (mLivePlayer != null) {
            mLivePlayer?.stopPlay(true)
            mLivePlayer?.setPlayListener(null)
            mLivePlayer=null
        }
        mPlayerView.onDestroy()
    }
}