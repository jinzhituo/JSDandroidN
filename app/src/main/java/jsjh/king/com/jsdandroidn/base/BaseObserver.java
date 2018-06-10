package jsjh.king.com.jsdandroidn.base;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;
import jsjh.king.com.jsdandroidn.global.AppManager;
import jsjh.king.com.jsdandroidn.global.MyApplication;
import jsjh.king.com.jsdandroidn.ui.login.LoginActivity;
import jsjh.king.com.jsdandroidn.utils.NetworkUtil;
import jsjh.king.com.jsdandroidn.utils.SpUtil;
import jsjh.king.com.jsdandroidn.utils.ToastUtil;

/**
 * BaseObserver
 * Created by jaycee on 2017/6/23.
 */
public abstract class BaseObserver<T> extends DisposableObserver<BaseEntity<T>> {

    private static final String TAG = "BaseObserver";
    private Context mContext;

//    protected BaseObserver(Context context) {
//        this.mContext = context.getApplicationContext();
//    }

    @Override
    public void onNext(BaseEntity<T> value) {
        if (value.isSuccess()) {
            T t = value.getData();
            onHandleSuccess(t);
        } else {
            onHandleError(value.getCode(), value.getMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        String message = "";
        Log.i("===", "===okhttp--Throwable===" + e.toString());
        if (!NetworkUtil.isNetworkAvailable()) {
            message = "请检查您的网络";
        } else {
            if (e instanceof ConnectException) {
                message = "网络不佳";
            } else if (e instanceof SocketException || e instanceof SocketTimeoutException) {
                message = "连接超时";
            } else {
                message = e.getMessage();
            }
        }
        onHandleError(-1001, message);

    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
        onHandleComplete();
    }

    protected abstract void onHandleSuccess(T t);

    protected void onHandleComplete() {

    }

    protected void onHandleError(int code, String msg) {
        Log.i("===", "===code===" + code);
        switch (code) {
            case -1:
                //未登录
                MyApplication.getContext().startActivity(new Intent(MyApplication.getContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case 400:
                //token
                AppManager.getAppManager().finishAllActivity();
                SpUtil.removeUser();
                MyApplication.getContext().startActivity(new Intent(MyApplication.getContext(), LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
        }

        ToastUtil.show(msg);
    }
}
