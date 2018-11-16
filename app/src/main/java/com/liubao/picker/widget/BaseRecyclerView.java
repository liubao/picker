package com.liubao.picker.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * * Created by liubao on 2018/11/16.
 */

public class BaseRecyclerView<T> extends RecyclerView implements IBindDataView<T> {
    protected Context context;
    protected Resources resources;


    protected void init() {
        context = getContext();
        resources = getResources();
    }

    public BaseRecyclerView(Context context) {
        super(context);
        init();
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setData(T t) {

    }
}
