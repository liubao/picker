package com.liubao.picker.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * * Created by liubao on 2018/11/16.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<MViewHolder> {
    public interface RecyclerViewItemClickListener<T> {
        void onRecyclerViewItemClick(View v, T model);
    }

    protected List<T> mList = new ArrayList<>();
    protected Context mContext;
    protected RecyclerViewItemClickListener<T> itemClickListener;

    public BaseRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    public void setRecyclerViewItemClickListener(RecyclerViewItemClickListener<T> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setList(List<T> mList) {
        this.mList = mList;
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    public void clearAndAddAll(List<T> list) {
        mList.clear();
        if (list != null) {
            mList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void pointToList(List<T> list) {
        if (list != null) {
            mList = list;
        } else {
            mList.clear();
        }
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        if (list == null) {
            return;
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<T> getList() {
        return mList;
    }

    public T getData(int index) {
        if (index >= 0 && index < getItemCount()) {
            return mList.get(index);
        }
        return null;
    }
}
