package com.example.ZhiKe.message;

import android.util.Log;

public class L {

    private static boolean debug=true;
    private static final String TAG = "okhttp";

    public static void e(String msg)
    {
        if(debug)
        Log.e(TAG,msg);
    }
}
