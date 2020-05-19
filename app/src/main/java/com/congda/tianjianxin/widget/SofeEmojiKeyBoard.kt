package com.congda.tianjianxin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.congda.baselibrary.widget.DrawableCheckBox
import com.congda.tianjianxin.R
import com.congda.tianjianxin.utils.FaceUtil
import com.congda.tianjianxin.utils.KeyBoardHeightUtil

class SofeEmojiKeyBoard : LinearLayout {
    lateinit var mBtnVoice :DrawableCheckBox
    lateinit var mBtnEmoji :DrawableCheckBox
    lateinit var mBtnAdd :ImageView
    lateinit var mEtContent :EditText
    lateinit var mTvVoice :TextView
    lateinit var mEmojiLayout : FrameLayout
    lateinit var mMoreLayout :FrameLayout
    lateinit var mContext: Context
    lateinit var imm: InputMethodManager
    lateinit var mKeyBoardHeightUtil: KeyBoardHeightUtil
     private var mHandler = android.os.Handler()
    lateinit var mFaceView:View
    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }
    private fun initView(context: Context) {
        this.mContext=context
        imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view: View = LayoutInflater.from(context).inflate(R.layout.layout_emoji_keyboard, this)
        mBtnVoice = view.findViewById<DrawableCheckBox>(R.id.btn_voice_record)
        mBtnEmoji= view.findViewById<DrawableCheckBox>(R.id.btn_face)
        mBtnAdd= view.findViewById<ImageView>(R.id.btn_add)
        mEtContent= view.findViewById<EditText>(R.id.edit)
        mTvVoice= view.findViewById<TextView>(R.id.btn_voice_record_edit)
        mEmojiLayout= view.findViewById<FrameLayout>(R.id.face_container)
        mMoreLayout= view.findViewById<FrameLayout>(R.id.more_container)
        initListener()
    }

    private fun initListener() {
        mBtnVoice.setOnClickListener{
            voiceOnClick()
        }
        mEmojiLayout.setOnClickListener{
            emojiOnClick()
        }

    }


    /**
     * 点击录音
     */
    private fun voiceOnClick() {
        if (mBtnVoice == null) {
            return
        }
        if (mBtnVoice.isChecked) {
            hideSoftInput()
            hideFace()
//            hideMore()
            if (mEtContent.visibility == View.VISIBLE) {
                mEtContent.visibility = View.INVISIBLE
            }
            if (mTvVoice != null && mTvVoice.visibility != View.VISIBLE) {
                mTvVoice.visibility = View.VISIBLE
            }
        } else {
            if (mTvVoice != null && mTvVoice.visibility == View.VISIBLE) {
                mTvVoice.visibility = View.INVISIBLE
            }
            if (mEtContent.visibility != View.VISIBLE) {
                mEtContent.visibility = View.VISIBLE
                mEtContent.requestFocus()
            }
        }
    }
    /**
     * 点击表情
     */
    private fun emojiOnClick() {
//        hideMore()
        if (mBtnEmoji.isChecked) {
            hideSoftInput()
            hideVoiceRecord()
            if (mHandler != null) {
                mHandler.postDelayed(Runnable { showFace() }, 200)
            }
        } else {
            hideFace()
            showSoftInput()
        }
    }


    /**
     * 显示表情弹窗
     */
    private fun showFace() {
        if (isFaceShowing()) {
            return
        }
//        hideMore()
        if (mFaceView == null) {
            mFaceView = initFaceView()
            mEmojiLayout.addView(mFaceView)
        }
        mEmojiLayout.visibility = View.VISIBLE
    }

    /**
     * 隐藏表情弹窗
     */
    private fun hideFace(): Boolean {
        if (isFaceShowing()) {
            mEmojiLayout.visibility = View.GONE
            if (mBtnEmoji != null) {
                mBtnEmoji.isChecked = false
            }
            return true
        }
        return false
    }


    /**
     * 隐藏录音
     */
    private fun hideVoiceRecord() {
        if (mBtnVoice != null && mBtnVoice.isChecked) {
            mBtnVoice.isChecked = false
            if (mEtContent.visibility != View.VISIBLE) {
                mEtContent.visibility = View.VISIBLE
                mEtContent.requestFocus()
            }
            if (mTvVoice.visibility == View.VISIBLE) {
                mTvVoice.visibility = View.INVISIBLE
            }
        }
    }



    /**
     * 显示软键盘
     */
    private fun showSoftInput() {
        if ( imm != null && mEtContent != null) {
            imm.showSoftInput(mEtContent, InputMethodManager.SHOW_FORCED)
            mEtContent.requestFocus()
        }
    }

    /**
     * 隐藏键盘
     */
    private fun hideSoftInput(): Boolean {
        if ( imm != null && mEtContent != null) {
            imm.hideSoftInputFromWindow(mEtContent.getWindowToken(), 0)
            return true
        }
        return false
    }

    /**
     * 表情弹窗是否显示
     */
    private fun isFaceShowing(): Boolean {
        return mEmojiLayout != null && mEmojiLayout.visibility != View.GONE
    }


    /**
     * 初始化表情控件
     */
    private fun initFaceView(): View {
        val inflater = LayoutInflater.from(mContext)
        val v: View = inflater.inflate(R.layout.layout_view_emoji, mEmojiLayout, false)
        v.findViewById<View>(R.id.btn_send).setOnClickListener{}
        val vp_title = v.findViewById<View>(R.id.vp_title) as ViewPagerEmoji
        vp_title.setpageSize(7)
        val faceList: MutableList<String> = FaceUtil.getFaceList()
        vp_title.setData(mContext,faceList)
        return v
    }

}