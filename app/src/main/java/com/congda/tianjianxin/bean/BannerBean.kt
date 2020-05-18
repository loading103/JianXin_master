package com.congda.tianjianxin.bean

import java.io.Serializable
/**
 * 轮播图
 */
data class BannerBean(
        var desc: String,
        var id: Int,
        var imagePath: String,
        var isVisible: Int,
        var order: Int,
        var title: String,
        var type: Int,
        var url: String):Serializable


