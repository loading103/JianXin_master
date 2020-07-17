package com.congda.tianjianxin.ui.activity.mvp.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.text.TextUtils
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.bigkoo.pickerview.view.OptionsPickerView
import com.bigkoo.pickerview.view.TimePickerView
import com.congda.baselibrary.app.IMSConfig
import com.congda.baselibrary.base.BaseMvpActivity
import com.congda.baselibrary.service.BindServiceDemo
import com.congda.baselibrary.service.DownLoadService
import com.congda.baselibrary.utils.IMSavePhotoUtil
import com.congda.baselibrary.utils.IMTimePickerUtils
import com.congda.baselibrary.utils.glide.IMImageLoadUtil
import com.congda.baselibrary.widget.dialog.IMIosCommonDiglog
import com.congda.baselibrary.widget.dialog.IMSheetViewDialog
import com.congda.baselibrary.widget.dialogfragment.LoginAgreeDialog
import com.congda.baselibrary.widget.loading.ShowLoadiongUtils
import com.congda.tianjianxin.R
import com.congda.tianjianxin.event.RecordEventEvent
import com.congda.tianjianxin.ui.activity.*
import com.congda.tianjianxin.ui.activity.mvp.contract.DemoContract
import com.congda.tianjianxin.ui.activity.mvp.presenter.DemoPresenter
import kotlinx.android.synthetic.main.activity_demo.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.util.*


class DemoActivity : BaseMvpActivity<DemoPresenter>(), DemoContract.View, View.OnClickListener, IMSheetViewDialog.Callback{
    lateinit  var pvTime: TimePickerView
    lateinit  var pvOptions: OptionsPickerView<Any>

    lateinit var mServiceDemo: BindServiceDemo
    lateinit var mConn: ServiceConnection
    private var isBind : Boolean=false
     var mdownService: DownLoadService?=null
    lateinit var mdownConn: ServiceConnection
    private var downBind : Boolean=false
    override fun getLayoutId(): Int {
        return R.layout.activity_demo
    }
    override fun createPresenter(): DemoPresenter {
        return DemoPresenter();
    }
    override fun initData() {

    }


    override fun initView() {
        common_top.setTopTitle("Demo界面")
        common_top.setTopRightText("保存")
        common_top.setOnClickRightListener {
            showMessage("保存")
        }
        //初始化时间选择器
        pvTime= IMTimePickerUtils.getPickView(this);
        //初始化内容联动
        val food: MutableList<String> = ArrayList()
        food.add("A")
        food.add("B")
        food.add("C")
        pvOptions= IMTimePickerUtils.getOptionsPickerView(this,food);
        //初始化下载服务
        initDownLoadService()
    }
    private fun initDownLoadService() {
        val intent = Intent(this, DownLoadService::class.java)
        mdownConn = object : ServiceConnection, DownLoadService.ProcessListener {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                val bind: DownLoadService.DownBind = service as DownLoadService.DownBind
                mdownService = bind.service
                mdownService?.setProcessListener(this)
            }
            override fun onServiceDisconnected(name: ComponentName) {
            }

            override fun onProcessListener(pross: Int, erro: String) {
                showProssDialog(pross,erro)
            }
        }
        downBind = bindService(intent, mdownConn, Context.BIND_AUTO_CREATE)

    }

    override fun initListener() {
        iv1.setOnClickListener(this)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
        btn3.setOnClickListener(this)
        btn4.setOnClickListener(this)
        btn5.setOnClickListener(this)
        btn6.setOnClickListener(this)
        btn7.setOnClickListener(this)
        btn8.setOnClickListener(this)
        btn9.setOnClickListener(this)
        btn10.setOnClickListener(this)
        btn11.setOnClickListener(this)
        btn12.setOnClickListener(this)
        btn13.setOnClickListener(this)
        btn14.setOnClickListener(this)
        btn15.setOnClickListener(this)
        btn16.setOnClickListener(this)
        btn17.setOnClickListener(this)
        btn18.setOnClickListener(this)
        btn19.setOnClickListener(this)
        btn20.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn1 -> {
                btn1OnClick()
            }
            R.id.btn2 -> {
                btn2OnClick()
            }
            R.id.btn3 -> {
                btn3OnClick()
            }
            R.id.btn4 -> {
                btn4OnClick()
            }
            R.id.btn5 -> {
                btn5OnClick()
            }
            R.id.btn6 -> {
                btn6OnClick()
            }
            R.id.btn7 -> {
                btn7OnClick()
            }
            R.id.btn8 -> {
                btn8OnClick()
            }
            R.id.btn9 -> {
                btn9OnClick()
            }
            R.id.btn10 -> {
                btn10OnClick()
            }
            R.id.btn11->{
                btn11OnClick()
            }
            R.id.btn12->{
                btn12OnClick()
            }
            R.id.btn13->{
                btn13OnClick()
            }
            R.id.btn14->{
                btn14OnClick()
            }
            R.id.btn15->{
                btn15OnClick()
            }
            R.id.btn16->{
                btn16OnClick()
            }
            R.id.btn17->{
                btn17OnClick()
            }
            R.id.btn18->{
                btn18OnClick()
            }
            R.id.btn19->{
                btn19OnClick()
            }
            R.id.btn20->{
                btn20OnClick()
            }
            R.id.iv1 -> {
                mPresenter.showSheetView(this)
            }
        }
    }


    /**
     * 点击处理
     */
    private fun btn1OnClick() {
        showLoadingDialog()
        btn2.postDelayed({ dissLoadingDialog()},2000)
    }

    private fun btn2OnClick() {
        mPresenter.showSheetView(this)
    }

    private fun btn3OnClick() {
        IMSheetViewDialog().shows(supportFragmentManager,this)
    }

    private fun btn4OnClick() {
        val diglog = IMIosCommonDiglog(this)
        diglog.showCommonDiglog("测试测试 心情好"){
            showToast("测试测试 心情好")
        }
    }

    private fun btn5OnClick() {
        mPresenter.showBigImageView(this)
    }

    private fun btn6OnClick() {
        var bundle= Bundle();
        bundle.putString("url","http://baidu.com");
        startActivity(ComWebViewActivity::class.java,bundle,false)
    }

    private fun btn7OnClick() {
        if(!File(IMSConfig.PICTURI_PATH+"app_logo.jpg").exists()){
            IMSavePhotoUtil.saveDrawableIcon(R.mipmap.app_logo,"app_logo")
        }else{
            showMessage("本地已有图片，不保存")
        }
    }
    private fun btn8OnClick() {
        pvTime.show(btn8)
    }
    private fun btn9OnClick() {
        pvOptions.show()
    }
    private fun btn10OnClick() {
        IMTimePickerUtils.initJsonData(this);
    }
    private fun btn11OnClick() {
        startActivity(RecycleDemoActivity::class.java,false)
    }
    private fun btn12OnClick() {
        showMessage("开启Service")
        val intent = Intent(this, BindServiceDemo::class.java)
        mConn = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName, service: IBinder) {
                val bind: BindServiceDemo.MyBind = service as BindServiceDemo.MyBind
                mServiceDemo = bind.getService()
            }

            override fun onServiceDisconnected(name: ComponentName) {}
        }
        isBind = bindService(intent, mConn, Context.BIND_AUTO_CREATE)
    }
    private fun btn13OnClick() {
        MaterialDialog(this, BottomSheet()).show {
            title(text = "温馨提示")
            customView(R.layout.layout_custom_view,scrollable = true,horizontalPadding = true)
            cornerRadius(16f)
        }
    }
    private fun btn14OnClick() {
        startActivity(KeyBoardActivity::class.java,false)
    }
    /**
     * service里面下载视屏资源
     */
    private fun btn15OnClick() {
        mdownService?.downLoadFile("data/upload/20200508/5eb5530f0019e.mp4")
    }
    private fun btn16OnClick() {
        startActivity(AnimationActivity::class.java,false)
    }
    private fun btn17OnClick() {
        startActivity(StartTypeActivity::class.java,false)

    }
    private fun btn18OnClick() {
        startActivity(PlayActivity::class.java,false)
    }
    private fun btn19OnClick() {
        startActivity(VideoRecordActivity::class.java,false)
    }
    private fun btn20OnClick() {
        val loginDiglog = LoginAgreeDialog()
        loginDiglog.show(supportFragmentManager, "LoginAgreeDialogFragment")
    }
    override fun onDestroy() {
        stopService()
        super.onDestroy()
    }

    /**
     * 关闭bind服务
     */
    private fun stopService() {
        if (isBind) {
            showMessage("关闭Service")
            unbindService(mConn)
            if (mServiceDemo != null) {
                mServiceDemo.stopSelf()
            }
            isBind = false
        }
        if (downBind) {
            unbindService(mdownConn)
            if (mdownService != null) {
                mdownService!!.stopSelf()
            }
            downBind = false
        }
    }

    /**
     * btn2_SheetView内部点击时间
     */
    override fun onClick(position: Int) {
        when (position) {
            0 -> {
                showToast("0")
            }
            1 -> {
                showToast("1")
            }
            2 -> {
                showToast("2")
            }
        }
    }

    /**
     * 显示下载进度
     */
    private fun showProssDialog(pross: Int, erro: String) {
        if(TextUtils.isEmpty(erro)){
            ShowLoadiongUtils.getInstance().showLoadingDialogProgress(this,pross,true)
        }else{
            showMessage(erro)
        }
    }

    override fun useEventBus(): Boolean {
        return true
    }


    /**
     * 转发图片视屏更新当前界面
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRecordEvent(event: RecordEventEvent) {
        IMImageLoadUtil.CommonImageLoad(this,event.url,iv1)
        if(!TextUtils.isEmpty(event.videourl)){

        }
    }
}