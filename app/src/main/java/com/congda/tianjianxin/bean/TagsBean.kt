package com.congda.tianjianxin.bean

import com.chad.library.adapter.base.entity.MultiItemEntity
import java.io.Serializable
/**
 * 文章的标签
 */
data class TagsBean(var name:String,
                    var url:String,
                    var headurl: String,
                    var type:Int): Serializable,MultiItemEntity {
    override val itemType: Int
        get() = inttype()

    private fun inttype(): Int {
        return type
    }
}
