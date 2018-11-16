package com.liubao.picker.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;

import com.liubao.picker.R;

/**
 * * Created by liubao on 2018/11/16.
 */
public class PickerTextView extends PingFangTextView implements IBindDataView<IPedigree> {
    public static final int HEIGHT = DPIUtil._48dp;

    public PickerTextView(Context context) {
        super(context);
    }

    public PickerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PickerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                HEIGHT
        );
        setGravity(Gravity.CENTER);
        setLayoutParams(layoutParams);
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_selected};
        states[1] = new int[]{android.R.attr.state_enabled};
        int[] colors = new int[]{resources.getColor(R.color.c_242629),
                resources.getColor(R.color.c_242629)};
        ColorStateList colorStateList1 = new ColorStateList(states, colors);
        setTextColor(colorStateList1);
    }


    @Override
    public void setData(IPedigree iPedigree) {
        if (iPedigree == null) {
            setText(null);
        } else {
            setText(iPedigree.getText());
        }
    }
}
