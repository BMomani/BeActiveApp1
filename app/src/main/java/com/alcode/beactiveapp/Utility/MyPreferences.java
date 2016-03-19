package com.alcode.beactiveapp.Utility;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MOMANI on 2016/03/19.
 */
public class MyPreferences {


    private static final String MY_PREFERENCES = "my_preferences";

    private static final String IsFirstKey = "is_first";

    public static boolean isFirst(Context context) {
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        final boolean first = reader.getBoolean(IsFirstKey, true);
        if (first) {
            final SharedPreferences.Editor editor = reader.edit();
            editor.putBoolean(IsFirstKey, false);
            editor.commit();
        }
        return first;
    }

}