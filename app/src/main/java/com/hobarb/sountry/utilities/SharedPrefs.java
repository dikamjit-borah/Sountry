package com.hobarb.sountry.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    SharedPreferences sharedpreferences;
    public SharedPrefs(Context context)
    {
        sharedpreferences = context.getSharedPreferences(constants.SHARED_PREFS_KEY, Context.MODE_PRIVATE);
    }

    public void writePrefs(String key, String value){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String readPrefs(String key ){
        if (sharedpreferences.contains(key))
            return sharedpreferences.getString(key, "");
        else
            return "404";
    }
}
