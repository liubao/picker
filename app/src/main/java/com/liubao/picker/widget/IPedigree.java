package com.liubao.picker.widget;

import java.util.List;

/**
 * * Created by liubao on 2018/11/16.
 */

public interface IPedigree<T> {
    String getText();

    String getKey();

    List<T> getChildren();
}

