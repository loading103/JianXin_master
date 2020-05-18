package com.congda.tianjianxin.ui.activity

import android.view.View
import android.widget.CheckedTextView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.congda.baselibrary.base.BaseActivity
import com.congda.baselibrary.utils.IMStatusBarUtil
import com.congda.tianjianxin.R
import com.congda.tianjianxin.adapter.MyViewPagerAdapter
import com.congda.tianjianxin.ui.fragment.mvp.ui.FirstFragment
import com.congda.tianjianxin.ui.fragment.mvp.ui.FourthFragment
import com.congda.tianjianxin.ui.fragment.mvp.ui.ListFirstFragment
import com.congda.tianjianxin.ui.fragment.mvp.ui.ThirdeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), ViewPager.OnPageChangeListener, View.OnClickListener {
    var exitTime: Long = 0
    var mLastIndex = 0
    var mFragments: MutableList<Fragment> = mutableListOf()
    lateinit var mAdapter: MyViewPagerAdapter
    lateinit var mLinearLayout: Array<LinearLayout?>

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        setSwipeBackEnable(false)
    }
    override fun initListener() {
    }

    override fun initStatusBar() {
        IMStatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null)
        IMStatusBarUtil.setLightMode(this)
    }

    override fun initData() {
        viewpage.isCanScrollble = true
        viewpage.offscreenPageLimit = 3
        mFragments.add(FirstFragment())
        mFragments.add(ListFirstFragment())
        mFragments.add(ThirdeFragment())
        mFragments.add(FourthFragment())
        mAdapter = MyViewPagerAdapter(getSupportFragmentManager(), mFragments)
        viewpage.setAdapter(mAdapter)
        viewpage.addOnPageChangeListener(this)
        initTabs()
    }

    private fun initTabs() {
        setCheckPosition(0)
        mLinearLayout = arrayOfNulls<LinearLayout>(tabs.childCount)
        for (i in 0 until tabs.childCount) {
            mLinearLayout[i] = tabs.getChildAt(i) as LinearLayout
            mLinearLayout[i]?.setOnClickListener(this)
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }
    override fun onPageSelected(position: Int) {
        if (position == mLastIndex) {
            return
        }
        (mLinearLayout[position]!!.getChildAt(1) as CheckedTextView).isChecked = true
        (mLinearLayout[mLastIndex]!!.getChildAt(1) as CheckedTextView).isChecked = false
        setCheckPosition(position)
        mLastIndex = position
    }
    override fun onClick(v: View?) {
        for (i in mLinearLayout.indices) {
            if (v === mLinearLayout[i]) {
                if (i == mLastIndex) {
                    break
                }
                (mLinearLayout[i]!!.getChildAt(1) as CheckedTextView).isChecked = true
                (mLinearLayout[mLastIndex]!!.getChildAt(1) as CheckedTextView).isChecked = false
                mLastIndex = i
                viewpage.setCurrentItem(i, false)
                setCheckPosition(i)
                break
            }
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show()
            exitTime = System.currentTimeMillis()
        } else {
            finish()
        }
    }


    /**
     * 设置选定状态
     * @param position
     */
    private fun setCheckPosition(position: Int) {
        when (position) {
            0 -> {
                iv_tab2.setImageResource(R.mipmap.im_main_contact)
                iv_tab3.setImageResource(R.mipmap.im_main_find)
                iv_tab4.setImageResource(R.mipmap.im_main_mine)
                iv_tab1.setAnimation("huihuajson.json")
                iv_tab1.playAnimation()
            }
            1 -> {
                iv_tab1.setImageResource(R.mipmap.im_main_conver)
                iv_tab3.setImageResource(R.mipmap.im_main_find)
                iv_tab4.setImageResource(R.mipmap.im_main_mine)
                iv_tab2.setAnimation("txunl.json")
                iv_tab2.playAnimation()
            }
            2 -> {
                iv_tab1.setImageResource(R.mipmap.im_main_conver)
                iv_tab2.setImageResource(R.mipmap.im_main_contact)
                iv_tab4.setImageResource(R.mipmap.im_main_mine)
                iv_tab3.setAnimation("faxian.json")
                iv_tab3.playAnimation()
            }
            3 -> {
                iv_tab1.setImageResource(R.mipmap.im_main_conver)
                iv_tab2.setImageResource(R.mipmap.im_main_contact)
                iv_tab3.setImageResource(R.mipmap.im_main_find)
                iv_tab4.setAnimation("mine.json")
                iv_tab4.playAnimation()
            }
        }
    }
}