package com.congda.tianjianxin.ui.activity.mvp.ui

import SplashAdBean
import android.content.Intent
import com.congda.baselibrary.app.IMSConfig
import com.congda.baselibrary.base.BaseMvpActivity
import com.congda.baselibrary.net.BaseHttpResult
import com.congda.baselibrary.service.StartServiceDemo
import com.congda.baselibrary.utils.IMCutTimeDownView
import com.congda.baselibrary.utils.IMPreferenceUtil
import com.congda.baselibrary.utils.IMStatusBarUtil
import com.congda.baselibrary.utils.glide.IMImageLoadUtil
import com.congda.tianjianxin.R
import com.congda.tianjianxin.ui.activity.GuideActivity
import com.congda.tianjianxin.ui.activity.MainActivity
import com.congda.tianjianxin.ui.activity.mvp.contract.SplashContract
import com.congda.tianjianxin.ui.activity.mvp.presenter.SplashPresenter
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseMvpActivity<SplashPresenter>(), SplashContract.View, IMCutTimeDownView.OnFinishListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun createPresenter(): SplashPresenter {
        return SplashPresenter();
    }

    override fun initData() {
        //开启监听网络的服务，处理一开始不开网进去app的情况
        val intent = Intent(this, StartServiceDemo::class.java)
        startService(intent)

        if(isFirstOpen()){
            return
        }
        skipTv.setOnFinishListener(this)
        mPresenter.getSplashData()
    }

    override fun initStatusBar() {
        IMStatusBarUtil.setTranslucentForImageView(this, 0, null)
    }
    /**
     * 判断是不是第一次进去app
     */
    private fun isFirstOpen(): Boolean {
        if (IMPreferenceUtil.getPreference_Boolean(IMSConfig.FIRST_OPEN, true)) {
            IMPreferenceUtil.setPreference_Boolean(IMSConfig.FIRST_OPEN, false)
            startActivity(GuideActivity::class.java,true)
            return  true
        }
        return false
    }

    override fun setOnFinishListener() {
        startActivity(MainActivity::class.java,true)
    }

    override fun setSplashData(result: BaseHttpResult<List<SplashAdBean>>?) {
        skipTv.setTotalTime(3000)
        if(result==null){
            return
        }
        IMImageLoadUtil.CommonSplashImageLoadCp(this, result.data.get(0).adsImgUrl, iv_bg)
    }

}