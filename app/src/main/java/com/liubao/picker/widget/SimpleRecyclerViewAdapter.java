package com.liubao.picker.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * * Created by liubao on 2018/11/16.
 */
public class SimpleRecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T> {
    public SimpleRecyclerViewAdapter(Context context) {
        super(context);
    }

    private Class clazz;

    public SimpleRecyclerViewAdapter(Context context, Class clazz) {
        super(context);
        this.clazz = clazz;
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        Constructor con = null;
        try {
            con = clazz.getDeclaredConstructor(Context.class);
            con.setAccessible(true);
            view = (View) con.newInstance(mContext);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onRecyclerViewItemClick(v, (T) v.getTag());
                    }
                }
            });
        }
        return new MViewHolder(view);
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
