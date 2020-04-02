package com.spoon.app.jsbridge_n22.core.extension;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * author : zhangxin
 * date : 2020-04-02 18:02
 * description :
 */
public class HideSelectedAdapter <T> extends ArrayAdapter {

    public HideSelectedAdapter(Context context, int resource, T[] objects) {
        super(context, resource, objects);
    }

    public View getView (int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        v.setVisibility(View.GONE);
        return v;
    }
}