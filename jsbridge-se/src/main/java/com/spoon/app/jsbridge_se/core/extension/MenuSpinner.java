package com.spoon.app.jsbridge_se.core.extension;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Spinner;

/**
 * author : zhangxin
 * date : 2020-04-02 18:00
 * description :
 */
@SuppressLint("AppCompatCustomView")
public class MenuSpinner extends Spinner {
    private OnItemSelectedListener listener;

    public MenuSpinner(Context context) {
        super(context);
    }

    @Override
    public void setSelection(int position) {
        super.setSelection(position);

        if (listener != null) {
            listener.onItemSelected(null, this, position, 0);
        }
    }

    @Override
    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.listener = listener;
    }
}
