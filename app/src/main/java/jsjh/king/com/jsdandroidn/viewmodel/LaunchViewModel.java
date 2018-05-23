package jsjh.king.com.jsdandroidn.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

import jsjh.king.com.jsdandroidn.base.BaseViewModel;
import jsjh.king.com.jsdandroidn.ui.login.LoginActivity;


/**
 * Created by ShaoGeng on 2018/5/12.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class LaunchViewModel extends BaseViewModel {

    private Context mContext;
    private CountDownTimer timer;

    public LaunchViewModel(Context context) {
        mContext = context;
        startTimeToMain();
    }

    private void startTimeToMain(){
        if (timer == null){
            timer = new CountDownTimer(3*1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    // TODO Auto-generated method stub
                }

                @Override
                public void onFinish() {
                    startActivity();
                }
            }.start();
        }
    }

    private void startActivity(){
        Intent it = new Intent(mContext , LoginActivity.class);
        mContext.startActivity(it);
        ((Activity)mContext).finish();
    }
}
