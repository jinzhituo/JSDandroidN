package jsjh.king.com.jsdandroidn.ui.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.base.BaseActivity;
import jsjh.king.com.jsdandroidn.databinding.ActivityLoginBinding;
import jsjh.king.com.jsdandroidn.view.LoginView;
import jsjh.king.com.jsdandroidn.viewmodel.LoginViewModel;


/**
 * Created by ShaoGeng on 2018/5/12.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class LoginActivity extends BaseActivity implements LoginView {

    public ActivityLoginBinding mBinding;
    private LoginViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initLayoutView() {
        super.initLayoutView();
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_login);
        mViewModel = new LoginViewModel(this , this);
        mBinding.setVm(mViewModel);
    }

    @Override
    public void loadStart(int loadType) {

    }

    @Override
    public void loadComplete() {

    }

    @Override
    public void loadFailure(String message) {

    }

}
