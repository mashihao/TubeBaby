package com.shsy.tubebaby.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.shsy.tubebaby.R;

/**
 * 弹出框
 */

public class DialogUtils {
  public static Dialog progressDialog(Context context) {
    if (context == null) {
      return null;
    }
    View layout = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null);
    final Dialog dialog = new Dialog(context, R.style.NoNetDialog);
    dialog.setCanceledOnTouchOutside(true);
    dialog.setContentView(layout);
    dialog.show();
    return dialog;
  }
}
