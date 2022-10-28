package com.spoon.app.jsbridge_se.uiInterface;

import android.content.Intent;

public interface ActivityResultBackInterface {

  /**
   * 处理onActivityResult回调
   */
  void onActivityResult(int requestCode, int resultCode, Intent intent);

}
