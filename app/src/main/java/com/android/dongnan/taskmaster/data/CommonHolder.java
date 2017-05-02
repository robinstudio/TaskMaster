package com.android.dongnan.taskmaster.data;

import android.content.Context;

/**
 * Created by dream on 2017/5/1.
 */

public class CommonHolder {
    private static Context mContext;

    public static void setApplicationContext(Context context) {
        mContext = context;
    }

    public static Context getApplicationContext() {
        return mContext;
    }
}
