package jsjh.king.com.jsdandroidn.ui.mine;

import android.databinding.DataBindingUtil;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.base.BaseActivity;
import jsjh.king.com.jsdandroidn.databinding.ActivityChangePasswordBinding;
import jsjh.king.com.jsdandroidn.viewmodel.ToolbarViewModel;


public class ChangePasswordActivity extends BaseActivity {

    private ActivityChangePasswordBinding mBinding;

    @Override
    public void initLayoutView() {
        super.initLayoutView();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
    }

    @Override
    public void initView() {
        super.initView();
        mBinding.toolbarChangePassword.setVm(new ToolbarViewModel(getString(R.string.title_change_password), new ToolbarViewModel.OnLeftListen() {
            @Override
            public void onClickLeft() {
                finish();
            }
        }));
    }

}
