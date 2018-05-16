package com.mtecc.mmp.invoicing.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by wll on 2018/1/5.
 * 自定义 Dalog
 */

public class ShowDalogUtils {

    /**
     * @param context  上下文
     * @param resuntId 布局文件
     * @return 返回自定义的view 用来findViewById,以及设置点击事件
     */
    public static View showPopupWindow(Context context, int resuntId) {
        //设置contentView
        View contentView = LayoutInflater.from(context).inflate(resuntId, null);

        return contentView;

    }

    /**
     * @param bgAlpha       背景透明度
     * @param windowManager 窗口管理器
     * @param window        窗口
     * @param view          设置给谁view
     * @param y_shang       y坐标的商
     * @param contentView
     */
    public static PopupWindow getPopWindows(float bgAlpha, WindowManager windowManager, final Window window, View view, int y_shang, View contentView) {
        PopupWindow mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        backgroundAlpha(bgAlpha, window);
        DisplayMetrics metric = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int heightPixels = metric.heightPixels;     // 屏幕宽度（像素）
        Rect frame = new Rect();
        window.getDecorView().getWindowVisibleDisplayFrame(frame);
        mPopWindow.showAtLocation(view, Gravity.BOTTOM, width, 0);
        //点击其他地方消失
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp1 = window.getAttributes();
                lp1.alpha = 1f;
                window.setAttributes(lp1);
            }
        });
        return mPopWindow;
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public static void backgroundAlpha(float bgAlpha, Window window) {
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        window.setAttributes(lp);
    }


    /**
     * 自定义对画框
     *
     * @param context 上下文
     * @param rouseId 资源文件
     * @return 返回自定义的view 用来findViewById,以及设置点击事件
     */
    public static View showCustomizeDialog(Context context, int rouseId) {

        final View dialogView = LayoutInflater.from(context)
                .inflate(rouseId, null);

        return dialogView;
    }

    /**
     * @param touchout   是否允许点击外部消失
     * @param context
     * @param dialogView
     * @return
     */
    public static AlertDialog showDialog(Context context, boolean touchout, View dialogView) {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(context);
        customizeDialog.setView(dialogView);
        final AlertDialog dialog = customizeDialog.show();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.width = (int) (width * 0.95);//宽高可设置具体大小
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(touchout);
        return dialog;
    }


    /**
     * @param touchout   是否允许点击外部消失
     * @param context
     * @param dialogView
     * @return
     */
    public static AlertDialog showDialog(Context context, boolean touchout, View dialogView, int gravity) {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(context);
        customizeDialog.setView(dialogView);
        final AlertDialog dialog = customizeDialog.show();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = gravity;
        lp.width = (int) (width * 0.95);//宽高可设置具体大小
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(touchout);
        return dialog;
    }


//    /**
//     * @param activity      Activity
//     * @param attachOnView  显示在这个View的下方
//     * @param popView       被显示在PopupWindow上的View
//     * @param popShowHeight 被显示在PopupWindow上的View的高度，可以传默认值defaultBotom
//     * @param popShowWidth  被显示在PopupWindow上的View的宽度，一般是传attachOnView的getWidth()
//     * @return PopupWindow
//     */
//    public static PopupWindow show(Context context,Activity activity, View attachOnView, View popView, final int popShowHeight, final int popShowWidth) {
//        if (popView != null && popView.getParent() != null) {
//            ((ViewGroup) popView.getParent()).removeAllViews();
//        }
//
//        PopupWindow popupWindow = null;
//        Rect frame = new Rect();
//        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//
//        int location[] = new int[2];
//        int x, y;
//        int popHeight = 0, popWidth = 0;
//
//        attachOnView.getLocationOnScreen(location);
//        x = location[0];
//        y = location[1];
//
//        int h = attachOnView.getHeight();
//        int screenHeight = context.DeviceInfoManager.getInstance().getScreenHeight();
//
//        if (popShowHeight == defaultBotom) {
//            popHeight = screenHeight / 6;
//            popHeight = Math.abs(screenHeight - (h + y)) - popHeight;
//        } else if (popHeight == ViewGroup.LayoutParams.WRAP_CONTENT) {
//            popHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
//        } else {
//            popHeight = popShowHeight;
//        }
//
//        if (popShowWidth == ViewGroup.LayoutParams.WRAP_CONTENT) {
//            popWidth = attachOnView.getWidth();
//        } else {
//            popWidth = popShowWidth;
//        }
//
//        popupWindow = new PopupWindow(popView, popWidth, popHeight, true);
//
//        //这行代码时用来让PopupWindow点击区域之外消失的，这个应该是PopupWindow的一个bug。
//        //但是利用这个bug可以做到点击区域外消失
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//
//        popupWindow.setAnimationStyle(R.style.PopupAnimationDown);
//        popupWindow.showAtLocation(attachOnView, Gravity.NO_GRAVITY, x, h + y);
//        popupWindow.update();
//        return popupWindow;
//    }

}
