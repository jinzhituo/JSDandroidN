package jsjh.king.com.jsdandroidn.ui.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.base.BaseActivity;
import jsjh.king.com.jsdandroidn.databinding.ActivityLaunchBinding;
import jsjh.king.com.jsdandroidn.view.LaunchView;
import jsjh.king.com.jsdandroidn.viewmodel.LaunchViewModel;


public class LaunchActivity extends BaseActivity implements LaunchView {

    private ActivityLaunchBinding mBinding;
    private LaunchViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initLayoutView() {
        super.initLayoutView();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_launch);
        mViewModel = new LaunchViewModel(this);
        mBinding.setVm(mViewModel);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initLoad() {
        super.initLoad();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
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
