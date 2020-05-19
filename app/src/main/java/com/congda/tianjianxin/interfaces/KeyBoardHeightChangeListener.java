package com.congda.tianjianxin.interfaces;

/**
 * Created by cxf on 2018/11/8.
 */

public interface KeyBoardHeightChangeListener {
    void onKeyBoardHeightChanged(int visibleHeight, int keyboardHeight);

    boolean isSoftInputShowed();
}
