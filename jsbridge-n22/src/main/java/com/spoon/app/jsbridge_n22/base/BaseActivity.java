package com.spoon.app.jsbridge_n22.base;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.spoon.app.jsbridge_n22.uiInterface.MAInterface;

public class BaseActivity extends Activity {

    private MAInterface maInterface;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (maInterface != null) {
            maInterface.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void setMaInterface(MAInterface maInterface) {
        this.maInterface = maInterface;
    }
}
