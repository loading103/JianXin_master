package com.congda.tianjianxin.interfaces

/**
 * Created by cxf on 2018/11/8.
 */
interface KeyBoardHeightChangeListener {
    fun onKeyBoardHeightChanged(visibleHeight: Int, keyboardHeight: Int)
    val isSoftInputShowed: Boolean
}