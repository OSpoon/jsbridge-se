package com.spoon.app.jsbridge_n22.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OtherActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("msg", "你好,数据是Other页面返回的");
                setResult(RESULT_OK, intent);
                OtherActivity.this.finish();
            }
        });
    }
}
