package com.mtecc.mmp.invoicing.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by wll on 2018/4/17.
 *调用系统方法
 */

public class UseSystemUtils {
    private Context mContext;

    public UseSystemUtils(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 调用系统日历时间 不显示时分秒
     */
    public void useSystemTimeNoHms(final TextView textView) {
        final Calendar c = Calendar.getInstance();
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        DatePickerDialog dialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);
                int month = monthOfYear + 1;
                textView.setText(year + "-" + month + "-" + dayOfMonth);
//                new TimePickerDialog(EnterActivity.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        if (minute < 10) {
//                            String text = DateFormat.format("yyyy-MM-dd", c) + " " + hourOfDay + ":0" + minute;
//
//                            textView.setText(text);
//                        } else {
//                            String text1 = DateFormat.format("yyyy-MM-dd", c) + " " + hourOfDay + ":" + minute;
//                            textView.setText(text1);
//                        }
//
//                    }
//                }, hour, minute, false).show();
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    /**
     * 调用系统日历时间 显示时分秒
     * @param textView
     */
    public void useSystemTimeHms(final TextView textView) {
        final Calendar c = Calendar.getInstance();
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        DatePickerDialog dialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);
                int month = monthOfYear + 1;
//                textView.setText(year + "-" + month + "-" + dayOfMonth);
                new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute < 10) {
                            String text = DateFormat.format("yyyy-MM-dd", c) + " " + hourOfDay + ":0" + minute;

                            textView.setText(text);
                        } else {
                            String text1 = DateFormat.format("yyyy-MM-dd", c) + " " + hourOfDay + ":" + minute;
                            textView.setText(text1);
                        }

                    }
                }, hour, minute, false).show();
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
}
