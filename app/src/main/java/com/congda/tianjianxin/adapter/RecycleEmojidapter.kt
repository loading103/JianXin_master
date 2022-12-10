/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.congda.tianjianxin.adapter

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.congda.tianjianxin.R
import com.congda.tianjianxin.utils.FaceUtil

class RecycleEmojidapter(datas:MutableList<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_emoji,datas) {
    var  page : Int  =-1
    var  pageSize : Int =-1
    var  datas:MutableList<String> = mutableListOf()
    lateinit var  mcontext: Context

    constructor(context: Context,page :Int,pageSize: Int,datas:MutableList<String>) : this(datas) {
        this.page=page
        this.pageSize=pageSize
        this.datas=datas
        mcontext=context
    }

    override fun convert(helper: BaseViewHolder, bean: String) {
    }

    override fun getItemCount(): Int {
        return if (datas.size > (page + 1) * pageSize) pageSize else datas.size - page * pageSize
    }
    override fun getItemId(position: Int): Long {
        return (position + page * pageSize).toLong()
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        /**
         * 在给View绑定显示的数据时，计算正确的position = position + mIndex * mPageSize，
         */
        val pos: Int = position + page * pageSize
        holder.getView<ImageView>(R.id.entrance_image).setImageResource(FaceUtil.getFaceImageRes(datas[pos]))
        holder.itemView.setOnClickListener{
            emojilistener?.emojiChoosed(datas[pos],FaceUtil.getFaceImageRes(datas[pos]))
        }
    }

    public interface  emojiClickListener{
        fun emojiChoosed(text :String,res:Int)
    }
      var emojilistener : emojiClickListener? =null
    public fun setEmojiClickListener(emojilistener :emojiClickListener){
        this.emojilistener=emojilistener
    }
}
