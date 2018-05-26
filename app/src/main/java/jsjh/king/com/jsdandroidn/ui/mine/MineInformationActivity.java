package jsjh.king.com.jsdandroidn.ui.mine;

import android.databinding.DataBindingUtil;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.base.BaseActivity;
import jsjh.king.com.jsdandroidn.databinding.ActivityMineInformationBinding;
import jsjh.king.com.jsdandroidn.viewmodel.ToolbarViewModel;


public class MineInformationActivity extends BaseActivity {

    private ActivityMineInformationBinding mBinding;

    @Override
    public void initLayoutView() {
        super.initLayoutView();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_mine_information);
    }

    @Override
    public void initView() {
        super.initView();
        mBinding.toolbarMineSetting.setVm(new ToolbarViewModel(getString(R.string.title_activity_mine_information), new ToolbarViewModel.OnLeftListen() {
            @Override
            public void onClickLeft() {
                finish();
            }
        }));
    }

}
