package com.congda.baselibrary.adapter.baseadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


/**
 * Created by lin on 16/12/28.
 */
public abstract class IMBaseRecycleViewAdapter_Complex<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private LayoutInflater inflater;
    List<T> datas;
    private View view;

    public IMBaseRecycleViewAdapter_Complex(Context context, List<T> datas){
        this.mContext=context;
        this.datas=datas;
        inflater=LayoutInflater. from(mContext);
    }




    @Override
    public int getItemCount() {
        return datas.size();
    }





    public void updaterall(List<T> vedioInfo)
    {
        Log.e("lbw",vedioInfo.toString());
        this.datas = vedioInfo;
        notifyDataSetChanged();
    }

    public void remove(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
        if (position != datas.size())
            notifyItemRangeChanged(position, datas.size() - position);
    }

    public void add(T t, int position) {
        datas.add(position,t);
        notifyItemInserted(position);
    }

    public void addMoreItem(List<T> newDatas) {
        datas.addAll(newDatas);
        notifyDataSetChanged();

    }
}
