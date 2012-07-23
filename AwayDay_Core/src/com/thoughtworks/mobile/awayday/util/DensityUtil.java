package com.thoughtworks.mobile.awayday.util;

import android.content.res.Resources;

public class DensityUtil {
    public static int toPx(Resources resources, int dpValue) {
        return (int) (resources.getDisplayMetrics().density * dpValue + 0.5f);
    }
}
