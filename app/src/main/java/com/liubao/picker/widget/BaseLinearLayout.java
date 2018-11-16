package com.liubao.picker.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * * Created by liubao on 2018/11/16.
 */

public class BaseLinearLayout<T> extends LinearLayout implements IBindDataView<T> {
    protected Context context;
    protected Resources resources;

    protected void init() {
        context = getContext();
        resources = getResources();
    }

    public BaseLinearLayout(Context context) {
        super(context);
        init();
    }

    public BaseLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setData(T t) {

    }
}
