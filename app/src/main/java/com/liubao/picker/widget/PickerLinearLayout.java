package com.liubao.picker.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.liubao.picker.R;

import java.util.List;

/**
 * * Created by liubao on 2018/11/16.
 */

public class PickerLinearLayout extends BaseLinearLayout<List<IPedigree>> {
    public BasePickerLayout basePickerLayout;
    public TextView cancelTxt;
    public TextView titleTxt;
    public TextView selectTxt;

    public PickerLinearLayout(Context context) {
        super(context);
    }

    public PickerLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PickerLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setVisibleChildCount(int count) {
        basePickerLayout.setVisibleChildCount(count);
    }

    @Override
    protected void init() {
        super.init();
        setOrientation(VERTICAL);
        inflate(context, R.layout.layout_picker_relative, this);
        setBackgroundResource(R.drawable.top_corner10_solid_ffffff);
        titleTxt = findViewById(R.id.picker_rl_title);
        cancelTxt = findViewById(R.id.picker_rl_cancel);
        selectTxt = findViewById(R.id.picker_rl_select);
        basePickerLayout = findViewById(R.id.picker_rl_picker);
    }

    public IPedigree getFirstSelectedItem() {
        return basePickerLayout.getFirstSelectedItem();
    }

    public IPedigree getSecondSelectedItem() {
        return basePickerLayout.getSecondSelectedItem();
    }

    public IPedigree getThirdSelectedItem() {
        return basePickerLayout.getThirdSelectedItem();
    }

    public void selectDefault(String key1, final String key2, final String key3) {
        basePickerLayout.selectDefault(key1, key2, key3);
    }

    public void selectDefault(int key1, final int key2, final int key3) {
        basePickerLayout.selectDefault(String.valueOf(key1),
                String.valueOf(key2),
                String.valueOf(key3));
    }

    @Override
    public void setData(List<IPedigree> list) {
        basePickerLayout.clearAndAddAll(list);
    }

    public void setOnItemSelectedListener(BasePickerLayout.OnItemSelectedListener<IPedigree> onItemSelectedListener) {
        basePickerLayout.setOnItemSelectedListener(onItemSelectedListener);
    }

    public interface OnBtnClickListener {
        void onPositiveBtnClick(IPedigree item1, IPedigree item2, IPedigree item3);
    }
}
