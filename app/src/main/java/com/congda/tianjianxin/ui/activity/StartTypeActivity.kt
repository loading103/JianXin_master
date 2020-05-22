package com.congda.tianjianxin.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.View
import com.congda.baselibrary.base.BaseActivity
import com.congda.tianjianxin.R
import kotlinx.android.synthetic.main.activity_start_type.*


class StartTypeActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutId(): Int {
        return R.layout.activity_start_type
    }

    override fun initView() {
    }

    override fun initListener() {
        btn_1.setOnClickListener(this)
        btn_2.setOnClickListener(this)
        btn_3.setOnClickListener(this)
        btn_4.setOnClickListener(this)

    }

    override fun initData() {
    }

    override fun onClick(p0: View) {
        when(p0.id) {
            R.id.btn_1 -> {
                startActivity(
                    Intent(this, StartTypeOneActivity::class.java),
                    ActivityOptions.makeSceneTransitionAnimation(this, iv_1, "iv_1").toBundle()
                );
            }

            R.id.btn_2 -> {
                val pair = Pair<View, String>(iv_1, "iv_1")
                val pairText = Pair<View, String>(btn_1, "btn_1")
                startActivity(
                    Intent(this, StartTypeOneActivity::class.java),
                    ActivityOptions.makeSceneTransitionAnimation(this, pair, pairText).toBundle()
                )
            }

            R.id.btn_3 -> {
                val intent = Intent(this, StartTypeOneActivity::class.java)
                intent.putExtra("type","3")
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                )
            }
            R.id.btn_4-> {
                val intent = Intent(this, StartTypeOneActivity::class.java)
                intent.putExtra("type","4")
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
                )
            }
        }
    }
}