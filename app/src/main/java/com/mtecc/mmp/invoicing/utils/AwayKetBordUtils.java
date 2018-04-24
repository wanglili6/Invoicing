package com.mtecc.mmp.invoicing.utils;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by wll on 2018/2/5.
 * 软键盘
 */

public class AwayKetBordUtils {
    private Context context;
    private Window window;

    public AwayKetBordUtils(Context context,Window window) {
        this.context = context;
        this.window = window;
    }

    /**
     * 收起软键盘
     */
    public void putAwayKetBord() {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(window.getDecorView().getWindowToken(),
                    0);
        }
    }

    /**
     * 展示软键盘
     */
    public  void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            view.requestFocus();
            imm.showSoftInput(view, 0);
        }
    }
}
