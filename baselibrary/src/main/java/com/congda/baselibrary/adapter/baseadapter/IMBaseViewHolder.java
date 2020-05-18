/**
 * Copyright 2013 Joan Zapata
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.congda.baselibrary.adapter.baseadapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class IMBaseViewHolder extends RecyclerView.ViewHolder {
    View convertView;
    Context context;
    private SparseArray<View> views;

    public IMBaseViewHolder(View itemView, Context context) {
        super(itemView);
        this.convertView = itemView;
        this.context = context;
        this.views = new SparseArray<View>();
    }


    public View getConvertView() {

        return convertView;
    }


    public <T extends View> T setViewBind(int viewId) {
        T view = (T) convertView.findViewById(viewId);
        return  view;
    }

    /**
     * textview
     * @param id
     * @param text
     */
    public TextView setText(int id, String text) {
        TextView tx = (TextView) convertView.findViewById(id);
        tx.setText(text);

        return tx;
    }

    /**
     * text 隐藏显示
     * @param id
     * @param state
     * @return
     */
    public TextView setTextVisibility(int id, int state) {
        TextView tx = (TextView) convertView.findViewById(id);
        if(state == 0)
        {
            tx.setVisibility(View.GONE);
        }else {
            tx.setVisibility(View.VISIBLE);
        }
        return tx;
    }

    /**
     * imageview  src
     * @param id
     * @param resouceId
     */
    public ImageView setImageResource(int id, int resouceId) {
        ImageView img= (ImageView) convertView.findViewById(id);
        img.setImageResource(resouceId);
        return img;
    }


    /**
     * imageview  background
     * @param id
     * @param resouceId
     */
    public ImageView setbackgrounpResource(int id, int resouceId) {
        ImageView img= (ImageView) convertView.findViewById(id);
        img.setBackgroundResource(resouceId);
        return img;
    }


    public ImageView setImageVisibility(int id,int state) {
        ImageView img= (ImageView) convertView.findViewById(id);
        if(state == 0)
        {
            img.setVisibility(View.GONE);
        }else {
            img.setVisibility(View.VISIBLE);
        }
        return img;
    }


    /**
     * gridLayout
     * @param id
     * @param IMBaseRecycleViewAdapter_complex
     * @return
     */
    public RecyclerView setRecycleView(int id, IMBaseRecycleViewAdapter_Complex IMBaseRecycleViewAdapter_complex)
    {
        RecyclerView recyclerView = (RecyclerView) convertView.findViewById(id);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(IMBaseRecycleViewAdapter_complex);

        return recyclerView;
    }

    /**
     * gridLayout
     * @param id
     * @param IMBaseRecycleViewAdapter_t
     * @param size
     * @return
     */
    public RecyclerView setRecycleView(int id, IMBaseRecycleViewAdapter_T IMBaseRecycleViewAdapter_t, int size)
    {
        RecyclerView recyclerView = (RecyclerView) convertView.findViewById(id);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, size);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(IMBaseRecycleViewAdapter_t);

        return recyclerView;
    }

    /**
     * recycle linearLayout
     * @param id
     * @param IMBaseRecycleViewAdapter_complex
     * @return
     */
    public RecyclerView setRecycleViewL(int id, IMBaseRecycleViewAdapter_Complex IMBaseRecycleViewAdapter_complex)
    {
        RecyclerView recyclerView = (RecyclerView) convertView.findViewById(id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(IMBaseRecycleViewAdapter_complex);

        return recyclerView;
    }

    /**
     * recycle linearLayout
     * @param id
     * @param IMBaseRecycleViewAdapter_t
     * @return
     */
    public RecyclerView setRecycleViewL(int id, IMBaseRecycleViewAdapter_T IMBaseRecycleViewAdapter_t)
    {
        RecyclerView recyclerView = (RecyclerView) convertView.findViewById(id);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(IMBaseRecycleViewAdapter_t);

        return recyclerView;
    }



    public RecyclerView setStaggeredRecycleView(int id, IMBaseRecycleViewAdapter_Complex IMBaseRecycleViewAdapter_complex, int num)
    {
        RecyclerView recyclerView = (RecyclerView) convertView.findViewById(id);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(num, StaggeredGridLayoutManager.HORIZONTAL));

        recyclerView.setAdapter(IMBaseRecycleViewAdapter_complex);

        return recyclerView;
    }



    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * T 的隐藏显示
     * @param viewId
     * @param state
     * @param <T>
     * @return
     */
    public <T extends View> T setViewVisibility(int viewId,int state) {
        T view = (T) convertView.findViewById(viewId);
        view.setVisibility(state);
        return  view;
    }


    /**
     * 按钮监听
     * @param viewId
     * @param onClickListener
     * @return
     */
    public Button setBottonOnClickListener(int viewId, View.OnClickListener onClickListener)
    {
        Button view = (Button) convertView.findViewById(viewId);
        view.setOnClickListener(onClickListener);
        return view;
    }

    /**
     * LinearLayout监听
     * @param viewId
     * @param onClickListener
     * @return
     */
    public LinearLayout setLinearLayoutOnClickListener(int viewId, View.OnClickListener onClickListener)
    {
        LinearLayout view = (LinearLayout) convertView.findViewById(viewId);
        view.setOnClickListener(onClickListener);
        return view;
    }


    /**
     * CheckBox 选框状态
     * @param viewId
     * @param type
     * @return
     */
    public CheckBox setCheckBox(int viewId,boolean type)
    {
        CheckBox checkBox = (CheckBox) convertView.findViewById(viewId);
        checkBox.setChecked(type);

        return checkBox;
    }

    /**
     * CheckBox 显示隐藏
     * @param viewId
     * @param state
     * @return
     */
    public CheckBox setCheckBoxVisibility(int viewId,int state) {
        CheckBox checkBox = (CheckBox) convertView.findViewById(viewId);
            checkBox.setVisibility(state);
        return checkBox;
    }
    /**
     * CheckBox 显示隐藏 动画
     * @param viewId
     * @param state
     * @return
     */
    public CheckBox setCheckBoxVisibility(int viewId, int state, Animation animationIn,Animation animationOut) {
        CheckBox checkBox = (CheckBox) convertView.findViewById(viewId);
        if (state == View.VISIBLE) {
            checkBox.setVisibility(state);
            checkBox.setAnimation(animationIn);
        }else {
            checkBox.setVisibility(state);
            checkBox.setAnimation(animationOut);
        }
        return checkBox;
    }


}
