package jsjh.king.com.jsdandroidn.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;

import jsjh.king.com.jsdandroidn.base.BaseLoadListener;
import jsjh.king.com.jsdandroidn.base.BaseViewModel;
import jsjh.king.com.jsdandroidn.common.MainConstant;
import jsjh.king.com.jsdandroidn.model.bean.UserBean;
import jsjh.king.com.jsdandroidn.model.model.LoginModel;
import jsjh.king.com.jsdandroidn.ui.login.LoginCompanyActivity;
import jsjh.king.com.jsdandroidn.view.LoginView;


/**
 * Created by ShaoGeng on 2018/5/12.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class LoginViewModel extends BaseViewModel implements BaseLoadListener<UserBean> {

    private Context mContext;
    private LoginModel mLoginModel;
    private LoginView mLoginView;
    public ObservableField<String> userAccout = new ObservableField<>("13587872587");
    public ObservableField<String> userPasswoed = new ObservableField<>("123456");
    public ObservableBoolean isEnabledLoginBtn = new ObservableBoolean(false);


    public LoginViewModel(Context context, LoginView loginView) {
        mContext = context;
        mLoginModel = new LoginModel();
        mLoginView = loginView;
        userAccout.set("13587872587");
        userPasswoed.set("13587872587");
        userAccout.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (TextUtils.isEmpty(userAccout.get()) || TextUtils.isEmpty(userPasswoed.get())) {
                    isEnabledLoginBtn.set(false);
                } else {
                    isEnabledLoginBtn.set(true);
                }
            }
        });

        userPasswoed.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (TextUtils.isEmpty(userAccout.get()) || TextUtils.isEmpty(userPasswoed.get())) {
                    isEnabledLoginBtn.set(false);
                } else {
                    isEnabledLoginBtn.set(true);
                }
            }
        });
    }


    public void toLoginClick() {
//        Toast.makeText(mContext , userAccout.get() + userPasswoed.get() , Toast.LENGTH_SHORT).show();
        loadType = MainConstant.LoadData.FIRST_LOAD;
        mLoginModel.toLogin(this);
    }

    @Override
    public void loadSuccess(UserBean response) {
        ((Activity) mContext).startActivity(new Intent(mContext, LoginCompanyActivity.class));
        ((Activity)mContext).finish();
    }

    @Override
    public void loadFailure(String message) {
        mLoginView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mLoginView.loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mLoginView.loadComplete();
    }
}
