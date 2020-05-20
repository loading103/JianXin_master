package com.congda.tianjianxin.widget

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import android.widget.TextView.OnEditorActionListener
import cc.shinichi.library.tool.ui.ToastUtil
import com.congda.baselibrary.utils.IMDensityUtil
import com.congda.baselibrary.widget.DrawableCheckBox
import com.congda.tianjianxin.R
import com.congda.tianjianxin.utils.FaceUtil
import com.congda.tianjianxin.utils.ImTextRender
import com.congda.tianjianxin.utils.KeyBoardHeightUtil
import kotlinx.android.synthetic.main.layout_emoji_keyboard.view.*

/**
 * 在接入的activity中stop()周期调用clickHomeView()  ，处理点home遇到的不正常问题
 */
class EmojiKeyBoard : LinearLayout, View.OnClickListener, ViewPagerEmoji.emojiChooseListener {
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
    private var mFaceView : View? =null  //表情控件
    private var mMoreView : View? = null //更多控件

    private var heights :Int=0  //输入框高度   处理文字多行时的高度  与切换语音
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
        mBtnVoice = view.findViewById(R.id.btn_voice_record)
        mBtnEmoji= view.findViewById(R.id.btn_face)
        mBtnAdd= view.findViewById(R.id.btn_add)
        mEtContent= view.findViewById(R.id.edit)
        mTvVoice= view.findViewById(R.id.btn_voice_record_edit)
        mEmojiLayout= view.findViewById(R.id.face_container)
        mMoreLayout= view.findViewById(R.id.more_container)
        initListener()
    }

    private fun initListener() {
        mBtnVoice.setOnClickListener{
            voiceOnClick()
        }
        mBtnEmoji.setOnClickListener{
            emojiOnClick()
        }
        mEtContent.setOnClickListener{
            hideFace()
            hideMore()
        }
        mBtnAdd.setOnClickListener{
            addOnClick()
        }
        mEtContent.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendText()
                return@OnEditorActionListener true
            }
            false
        })

    }

    /**
     * 点击更多按钮
     */
    private fun addOnClick() {
        hideFace()
        hideSoftInput()
        hideVoiceRecord()
        if (mHandler != null) {
            mHandler.postDelayed({ showMore() }, 200)
        }
    }
    /**
     * 显示更多弹窗
     */
    private fun showMore() {
        if (isMoreShowing()) {
            return
        }
        hideFace()
        if (mMoreView == null) {
            mMoreView = initMoreView()
            mMoreLayout.addView(mMoreView)
        }
        mMoreLayout.visibility = View.VISIBLE
    }
    /**
     * 隐藏更多弹窗
     */
    private fun hideMore(): Boolean {
        if (isMoreShowing()) {
            mMoreLayout.visibility = View.GONE
            return true
        }
        return false
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
            hideMore()
            if (mEtContent.visibility == View.VISIBLE) {
                mEtContent.visibility = View.INVISIBLE
            }

            val layoutParams = framelayout.layoutParams
            heights = layoutParams.height
            layoutParams.height=IMDensityUtil.dip2px(mContext,52f)
            framelayout.layoutParams=layoutParams

            if (mTvVoice != null && mTvVoice.visibility != View.VISIBLE) {
                mTvVoice.visibility = View.VISIBLE
            }
        } else {
            if (mTvVoice != null && mTvVoice.visibility == View.VISIBLE) {
                mTvVoice.visibility = View.INVISIBLE
            }
            if(heights!=0){
                val layoutParams = framelayout.layoutParams
                layoutParams.height=heights
                framelayout.layoutParams=layoutParams
            }
            if (mEtContent.visibility != View.VISIBLE) {
                mEtContent.visibility = View.VISIBLE
                mEtContent.requestFocus()
                showSoftInput()
            }
        }
    }
    /**
     * 点击表情
     */
    private fun emojiOnClick() {
        hideMore()
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
        hideMore()
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
     * 更多弹窗是否显示
     */
    private fun isMoreShowing(): Boolean {
        return mMoreLayout != null && mMoreLayout.visibility != View.GONE
    }

    /**
     * 初始化表情控件
     */
    private fun initFaceView(): View {
        val inflater = LayoutInflater.from(mContext)
        val v: View = inflater.inflate(R.layout.layout_view_emoji, mEmojiLayout, false)
        v.findViewById<View>(R.id.btn_send).setOnClickListener(this)
        val vp_title = v.findViewById<View>(R.id.vp_title) as ViewPagerEmoji
        val faceList: MutableList<String> = FaceUtil.getFaceList()
        vp_title.setData(mContext,faceList)
        vp_title.setEmojiClickListener(this)
        return v
    }

    /**
     * 初始化更多
     */
    private fun initMoreView(): View {
        val v: View = LayoutInflater.from(mContext).inflate(R.layout.layout_view_more, null)
        v.findViewById<View>(R.id.btn_img).setOnClickListener(this)
        v.findViewById<View>(R.id.btn_camera).setOnClickListener(this)
        v.findViewById<View>(R.id.btn_voice).setOnClickListener(this)
        v.findViewById<View>(R.id.btn_location).setOnClickListener(this)
        return  v
    }


    override fun onClick(p0: View) {
        when(p0.id){
            R.id.btn_img->{
                ToastUtil.getInstance()._short(mContext,"图片")
            }
            R.id.btn_camera->{
                ToastUtil.getInstance()._short(mContext,"照相")
            }
            R.id.btn_voice->{
                ToastUtil.getInstance()._short(mContext,"语音")
            }
            R.id.btn_location->{
                ToastUtil.getInstance()._short(mContext,"定位")
            }
            R.id.btn_send->{
                sendText()
            }
        }
    }



    /**
     * 发送消息
     */
    private fun sendText() {
        if(TextUtils.isEmpty(mEtContent.text.toString())){
            ToastUtil.getInstance()._short(mContext,"请输入内容")
            return
        }
        sendlistener?.sendContent(mEtContent.text.toString(),1)
        mEtContent.text.clear()
    }

    /**
     * 在接入的activity中stop()周期调用  ，处理点home遇到的不正常问题
     */
    fun clickHomeView() {
        hideFace()
        hideMore()
    }

    /**
     * 选中表情
     */
    override fun emojiChoosed(text: String, res: Int) {
        if (mEtContent != null) {
            val editable: Editable = mEtContent.text
            editable.insert(
                mEtContent.selectionStart,
                ImTextRender.getFaceImageSpan(text, res)
            )
        }
    }

    /**
     * type=1是文字表情
     */
    public interface  sendContentListener{
        fun sendContent(text :String,type :Int )
    }
    var sendlistener : sendContentListener? =null
    public fun setSendContentListener(sendlistener :sendContentListener){
        this.sendlistener=sendlistener
    }
}