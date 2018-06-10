package jsjh.king.com.jsdandroidn.base;


import android.content.Intent;
import android.util.Log;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import jsjh.king.com.jsdandroidn.global.AppManager;
import jsjh.king.com.jsdandroidn.global.MyApplication;
import jsjh.king.com.jsdandroidn.ui.login.LoginActivity;
import jsjh.king.com.jsdandroidn.utils.JsonUtil;
import jsjh.king.com.jsdandroidn.utils.NetworkUtil;
import jsjh.king.com.jsdandroidn.utils.SpUtil;
import jsjh.king.com.jsdandroidn.utils.ToastUtil;

/**
 * Created by xuhaosong on 2017/11/2.
 */

public abstract class BaseObserverForJson extends DisposableObserver<String> {


    @Override
    public void onNext(String json) {
        if (JsonUtil.isSuccess(json)) {
            onHandleSuccess(json);
        } else {
            onHandleError(JsonUtil.getFieldValue(json, "code"), JsonUtil.getFieldValue(json, "message"));
        }

    }

    @Override
    public void onError(Throwable e) {
        String message = "";
        Log.i("===", "===e===" + e.toString());
        if (!NetworkUtil.isNetworkAvailable()) {
            message = "请检查您的网络";
        } else {
            if (e instanceof ConnectException) {
                message = "网络不佳";
            } else if (e instanceof SocketException || e instanceof SocketTimeoutException) {
                message = "连接超时";
            } else {
                message = "好像出问题了";
            }
        }
        onHandleError("-1000", message);
    }

    @Override
    public void onComplete() {

    }

    protected abstract void onHandleSuccess(String json);

    protected void onHandleError(String code, String msg) {
        switch (code){
            case "-1":
                //未登录
                MyApplication.getContext().startActivity(new Intent(MyApplication.getContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case "400":
                //token
                AppManager.getAppManager().finishAllActivity();
                SpUtil.removeUser();
                MyApplication.getContext().startActivity(new Intent(MyApplication.getContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }
        ToastUtil.show(msg);
    }
}
