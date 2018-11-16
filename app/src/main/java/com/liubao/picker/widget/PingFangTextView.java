package com.liubao.picker.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * * Created by liubao on 2018/11/16.
 */
public class PingFangTextView extends AppCompatTextView {
    protected Context context;
    protected Resources resources;

    public PingFangTextView(Context context) {
        super(context);
        init();
    }

    public PingFangTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PingFangTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        context = getContext();
        resources = getResources();
    }

    public void setTextSizeDp(int size) {
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, size);
    }

    public void setTextColorById(int id) {
        setTextColor(resources.getColor(id));
    }

    public Drawable getDrawableById(int id) {
        return resources.getDrawable(id);
    }

}
