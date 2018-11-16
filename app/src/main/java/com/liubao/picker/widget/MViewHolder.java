package com.liubao.picker.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * * Created by liubao on 2018/11/16.
 */

public class MViewHolder extends RecyclerView.ViewHolder {

    public MViewHolder(View itemView) {
        super(itemView);
    }

    public void setData(Object o) {
        if (itemView instanceof IBindDataView) {
            ((IBindDataView) itemView).setData(o);
        }
    }
}
