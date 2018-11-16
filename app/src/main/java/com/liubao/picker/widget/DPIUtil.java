package com.liubao.picker.widget;

/**
 * * Created by liubao on 2018/11/16.
 */
class DPIUtil {
    public static final int _16dp = dip2px(16);
    public static final int _48dp = dip2px(48);

    public static int dip2px(float dipValue) {
        return (int) (dipValue * Common.getDensity() + 0.5f);
    }

    public static int dip2FloorPx(float dipValue) {
        return (int) (dipValue * Common.getDensity());
    }

}
