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

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.congda.baselibrary.utils.glide.IMImageLoadUtil
import com.congda.tianjianxin.R
import com.congda.tianjianxin.bean.TagsBean

class RecycleSecondadpter( data: MutableList<TagsBean>?) : BaseMultiItemQuickAdapter<TagsBean, BaseViewHolder>(data){
    private val TYPE_ONE :Int=1
    private val TYPE_TWO :Int=2
    private val TYPE_THREE :Int=3

    init {
        addItemType(TYPE_ONE, R.layout.item_recycle_type_first)
        addItemType(TYPE_TWO, R.layout.item_recycle_type_second)
        addItemType(TYPE_THREE, R.layout.item_recycle_type_three)
    }

    override fun convert(helper: BaseViewHolder, bean: TagsBean) {
        if (bean != null) {
            when(bean.itemType){
                TYPE_ONE->{
                    helper.setText(R.id.tv_content,"第一种布局")
                }
                TYPE_TWO->{
                    helper.setText(R.id.tv_content,"第二种布局")
                    IMImageLoadUtil.CommonImageLoadCp(context,bean.url,helper.getView(R.id.iv_content))
                }
                TYPE_THREE->{
                    helper.setText(R.id.tv_content,"第三种布局")
                    IMImageLoadUtil.CommonImageLoadCp(context,bean.url,helper.getView(R.id.iv_content))
                    IMImageLoadUtil.CommonImageLoadCp(context,bean.headurl,helper.getView(R.id.iv_content_2))
                }
            }
        }
    }

}