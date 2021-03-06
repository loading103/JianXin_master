package com.congda.tianjianxin.ui.activity

import com.congda.baselibrary.base.BaseActivity
import com.congda.baselibrary.utils.AnimatorUtil
import com.congda.baselibrary.utils.IMStopClickFast
import com.congda.tianjianxin.R
import kotlinx.android.synthetic.main.activity_animation.*
import kotlinx.android.synthetic.main.activity_settings.common_top

class AnimationActivity : BaseActivity(){
    var   lastpositon :Int=0
    lateinit var animtor : AnimatorUtil

    override fun getLayoutId(): Int {
        return R.layout.activity_animation
    }
    override fun initView() {
        common_top.setTopTitle("基本动画")
    }
    override fun initListener() {
        animtor= AnimatorUtil(this);
    }

    override fun initData() {
        tv_anim1.setOnClickListener{
            if(IMStopClickFast.isFastClick()){
                animtor.performAnim(tv_anim1.isChecked,tv_anim1)
                tv_anim1.isChecked=!tv_anim1.isChecked
            }
        }
        tv_anim2.setOnClickListener{
            animtor.objectAnimation(tv_anim2.isChecked,lin1)
            tv_anim2.isChecked=!tv_anim2.isChecked
        }

        tv_anim3.setOnClickListener{
            animtor.FrameAnimation(im_iv_open)
        }
        tv_anim4.setOnClickListener{
            animtor.roteAnimation(im_iv_add,tv_anim4.isChecked)
        }
        //空间看似移除屏幕  但是实际还在
        tv_anim5.setOnClickListener{
            animtor.translAnimation(lin1,tv_anim5.isChecked)
            tv_anim5.isChecked=!tv_anim5.isChecked
        }
        tv1.setOnClickListener{
            showMessage("tv1")
        }
    }

}