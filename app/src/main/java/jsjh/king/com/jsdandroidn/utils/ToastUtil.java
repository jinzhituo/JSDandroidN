package jsjh.king.com.jsdandroidn.utils;


import android.view.Gravity;
import android.widget.Toast;

import jsjh.king.com.salesmanapp.global.MyApplication;


/**
 * Created by TC on 15/8/12.
 */
public class ToastUtil {

    private static Toast sToast;

    public static void show(final String text) {
        MyApplication.getMainHandler().post(new Runnable() {
            @Override
            public void run() {

                if (sToast == null) {
                    sToast = Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_SHORT);
                }
                sToast.setText(text);
                sToast.show();
            }
        });
    }
    
    public static void showAtCenter(final String text) {
        MyApplication.getMainHandler().post(new Runnable() {
            @Override
            public void run() {

                if (sToast == null) {
                    sToast = Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_SHORT);
                    sToast.setGravity(Gravity.CENTER, 0, 0);
                }
                sToast.setText(text);
                sToast.show();
            }
        });

    }
}
