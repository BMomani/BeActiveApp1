package com.alcode.beactiveapp.Models;

import android.content.Context;

/**
 * Created by MOMANI on 2016/03/18.
 */
public class Item {

    private String url;
    private String name;

    public Item(String name, String url) {
        this.url = url;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getImageResourceId(Context context) {
        return context.getResources().getIdentifier(this.name, "drawable", context.getPackageName());
    }
}
