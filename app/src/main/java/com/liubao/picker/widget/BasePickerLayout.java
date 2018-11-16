package com.liubao.picker.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.liubao.picker.R;

import java.util.List;

/**
 * * Created by liubao on 2018/11/16.
 */
public class BasePickerLayout extends LinearLayout {
    private Context context;
    private Paint dividerPaint;
    private int dividerPadding = DPIUtil._16dp;

    public RecyclerViewPicker<IPedigree> recyclerViewPicker1;
    public RecyclerViewPicker<IPedigree> recyclerViewPicker2;
    public RecyclerViewPicker<IPedigree> recyclerViewPicker3;

    private IPedigree firstSelectedItem;
    private IPedigree secondSelectedItem;
    private IPedigree thirdSelectedItem;
    private OnItemSelectedListener<IPedigree> onItemSelectedListener;

    public BasePickerLayout(Context context) {
        super(context);
        init();
    }

    public BasePickerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BasePickerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    protected void init() {
        context = getContext();
        dividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dividerPaint.setColor(context.getResources().getColor(R.color.c_e3e5e8));

        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerViewPicker1 = newRecyclerViewPicker();
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp1.weight = 1;
        addView(recyclerViewPicker1, lp1);
        recyclerViewPicker2 = newRecyclerViewPicker();
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1;
        addView(recyclerViewPicker2, lp2);
        recyclerViewPicker3 = newRecyclerViewPicker();
        LinearLayout.LayoutParams lp3 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp3.weight = 1;
        addView(recyclerViewPicker3, lp3);

        recyclerViewPicker1.setOnItemSelectedListener(new RecyclerViewPicker.OnItemSelectedListener<IPedigree>() {
            @Override
            public void onItemSelected(IPedigree item) {
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onFirstItemSelected(item);
                }
                if (item != null) {
                    recyclerViewPicker2.clearAndAddAll(item.getChildren());
                }
                firstSelectedItem = item;
            }
        });
        recyclerViewPicker2.setOnItemSelectedListener(new RecyclerViewPicker.OnItemSelectedListener<IPedigree>() {
            @Override
            public void onItemSelected(IPedigree item) {
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onSecondItemSelected(item);
                }
                if (item != null) {
                    recyclerViewPicker3.clearAndAddAll(item.getChildren());
                }
                secondSelectedItem = item;

            }
        });
        recyclerViewPicker3.setOnItemSelectedListener(new RecyclerViewPicker.OnItemSelectedListener<IPedigree>() {
            @Override
            public void onItemSelected(IPedigree item) {
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onThirdItemSelected(item);
                }
                thirdSelectedItem = item;
            }
        });

    }

    protected RecyclerViewPicker<IPedigree> newRecyclerViewPicker() {
        return new RecyclerViewPicker<IPedigree>(context) {
            @Override
            protected Class getItemViewClass() {
                return BasePickerLayout.this.getItemViewClass();
            }
        };
    }

    protected Class getItemViewClass() {
        return PickerTextView.class;
    }

    public void setVisibleChildCount(int count) {
        recyclerViewPicker1.setVisibleChildCount(count);
        recyclerViewPicker2.setVisibleChildCount(count);
        recyclerViewPicker3.setVisibleChildCount(count);
    }

    public IPedigree getFirstSelectedItem() {
        return firstSelectedItem;
    }

    public IPedigree getSecondSelectedItem() {
        return secondSelectedItem;
    }

    public IPedigree getThirdSelectedItem() {
        return thirdSelectedItem;
    }

    public int findByKey(String key, List<IPedigree> list) {
        if (list == null) {
            return -1;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            IPedigree model = list.get(i);
            if (model == null) {
                continue;
            }
            if (TextUtils.equals(model.getKey(), key)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        drawDivider(canvas);
        super.dispatchDraw(canvas);
    }

    protected void drawDivider(Canvas canvas) {
        int w = getWidth();
        int h = getHeight();
        int itemH = (int) (h / 5f);
        int top1 = itemH * 2;
        canvas.drawLine(dividerPadding, top1, w - dividerPadding,
                top1 + 1, dividerPaint);
        int top2 = itemH * 3;
        canvas.drawLine(dividerPadding, top2, w - dividerPadding,
                top2 + 1, dividerPaint);
    }

    public void selectDefault(String key1, final String key2, final String key3) {
        List list1 = recyclerViewPicker1.getList();
        List list2 = recyclerViewPicker2.getList();
        List list3 = recyclerViewPicker3.getList();
        recyclerViewPicker1.setSelectedPosition(findByKey(key1, list1));
        recyclerViewPicker2.setSelectedPosition(findByKey(key2, list2));
        recyclerViewPicker3.setSelectedPosition(findByKey(key3, list3));
    }


    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public interface OnItemSelectedListener<T> {
        void onFirstItemSelected(T t);

        void onSecondItemSelected(T t);

        void onThirdItemSelected(T t);
    }

    public void clearAndAddAll(List<IPedigree> list) {
        recyclerViewPicker1.clearAndAddAll(list);
    }

}