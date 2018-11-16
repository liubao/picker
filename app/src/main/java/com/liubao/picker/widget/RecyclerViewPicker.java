package com.liubao.picker.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.liubao.picker.R;

import java.util.List;

/**
 * * Created by liubao on 2018/11/16.
 */
public class RecyclerViewPicker<T> extends BaseRecyclerView {

    public RecyclerViewPicker(Context context) {
        super(context);
    }

    public RecyclerViewPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private RecyclerViewPicker.RecyclerViewPickerAdapter recyclerViewPickerAdapter;

    private LinearLayoutManager linearLayoutManager;
    private int height;
    private int width;
    private int childHeight;
    private int visibleChildCount;
    private Drawable coverDrawableTop;
    private Drawable coverDrawableBottom;
    private Rect coverDrawableTopRect;
    private Rect coverDrawableBottomRect;


    public void measure() {
        height = childHeight * visibleChildCount;
    }

    public int getTheHeight() {
        return height;
    }

    private int getPositionUnderCenter() {
        View view = findChildViewUnder(width, height / 2);
        return getChildLayoutPosition(view);
    }

    @Override
    protected void init() {
        super.init();
        coverDrawableTop = resources.getDrawable(R.drawable.choose_address_dialog_cover_top);
        coverDrawableBottom = resources.getDrawable(R.drawable.choose_address_dialog_cover_bottom);
        coverDrawableTopRect = new Rect();
        coverDrawableBottomRect = new Rect();
        setClipToPadding(false);
        setClipChildren(false);
        childHeight = PickerTextView.HEIGHT;
        setVisibleChildCount(5);
        linearLayoutManager = new LinearLayoutManager(context);
        setLayoutManager(linearLayoutManager);
        addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int scrollPosition = getPositionUnderCenter();
                    setSelectedPosition(scrollPosition);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        recyclerViewPickerAdapter = new RecyclerViewPicker.RecyclerViewPickerAdapter(context, getItemViewClass());
        setAdapter(recyclerViewPickerAdapter);
        recyclerViewPickerAdapter.setRecyclerViewItemClickListener(new BaseRecyclerViewAdapter.
                RecyclerViewItemClickListener<T>() {
            @Override
            public void onRecyclerViewItemClick(View v, T model) {
                int viewP = linearLayoutManager.getPosition(v);
                setSelectedPosition(viewP);
            }
        });
    }

    public void setChildHeight(int childHeight) {
        this.childHeight = childHeight;
    }

    public void setVisibleChildCount(int visibleChildCount) {
        if (visibleChildCount % 2 == 0) {
            visibleChildCount++;
        }
        this.visibleChildCount = visibleChildCount;
        measure();
        int padding = childHeight * (visibleChildCount / 2);
        setPadding(0, padding, 0, padding);
    }

    public int setSelectedPosition(int position) {
        List<IPedigree> list = recyclerViewPickerAdapter.getList();
        if (list == null || list.size() == 0) {
            dispatchItemSelected(null);
            return -1;
        }
        position = Math.max(0, position);
        position = Math.min(position, list.size() - 1);
        linearLayoutManager.scrollToPosition(position);
        dispatchItemSelected(list.get(position));
        return position;
    }

    protected Class getItemViewClass() {
        return PickerTextView.class;
    }

    public void dispatchItemSelected(int position) {
        if (onItemSelectedListener == null) {
            return;
        }
        List<IPedigree> list = recyclerViewPickerAdapter.getList();
        if (list == null || list.size() == 0) {
            onItemSelectedListener.onItemSelected(null);
            return;
        }
        position = Math.max(0, position);
        position = Math.min(position, list.size() - 1);
        onItemSelectedListener.onItemSelected(list.get(position));
    }

    public void dispatchItemSelected(IPedigree item) {
        if (onItemSelectedListener == null) {
            return;
        }
        onItemSelectedListener.onItemSelected(item);
    }

    public List<T> getList() {
        return recyclerViewPickerAdapter.getList();
    }

    public interface OnItemSelectedListener<T> {
        void onItemSelected(T item);
    }

    OnItemSelectedListener onItemSelectedListener;

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }


    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthSpec, heightSpec);
        width = getMeasuredWidth();
        int firstB = childHeight * (visibleChildCount - 1) / 2;
        coverDrawableTopRect.set(0, 0, width, firstB);
        coverDrawableTop.setBounds(coverDrawableTopRect);
        int secondT = childHeight + firstB;
        coverDrawableBottomRect.set(0, secondT, width, secondT + firstB);
        coverDrawableBottom.setBounds(coverDrawableBottomRect);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        coverDrawableTop.draw(canvas);
        coverDrawableBottom.draw(canvas);
    }

    @Override
    public boolean drawChild(Canvas canvas, View child, long drawingTime) {
        float halfH = height / 2f;
        int childT = child.getTop();
        int childB = child.getBottom();

        if (childT > halfH - childHeight && childB < halfH + childHeight) {
            child.setSelected(true);
        } else {
            child.setSelected(false);
        }
        return super.drawChild(canvas, child, drawingTime);
    }

    public class RecyclerViewPickerAdapter extends SimpleRecyclerViewAdapter<T> {

        public RecyclerViewPickerAdapter(Context context) {
            super(context);
        }

        public RecyclerViewPickerAdapter(Context context, Class clazz) {
            super(context, clazz);
        }

        public List<T> getList() {
            return mList;
        }

        @Override
        public void onBindViewHolder(MViewHolder holder, int position) {
            T listItem = (T) mList.get(position);
            if (listItem != null) {
                holder.setData(listItem);
                (holder.itemView).setTag(listItem);
            }
        }
    }

    public int findItemPosition(T item) {
        return recyclerViewPickerAdapter.getList().indexOf(item);
    }


    public void clearAndAddAll(List<T> list) {
        recyclerViewPickerAdapter.clearAndAddAll(list);
        setSelectedPosition(getPositionUnderCenter());
    }


}
