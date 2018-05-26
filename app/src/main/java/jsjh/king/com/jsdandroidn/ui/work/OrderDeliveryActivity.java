package jsjh.king.com.jsdandroidn.ui.work;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.base.BaseActivity;
import jsjh.king.com.jsdandroidn.databinding.ActivityOrderDeliveryBinding;
import jsjh.king.com.jsdandroidn.viewmodel.OrderDeliveryViewModel;
import jsjh.king.com.jsdandroidn.viewmodel.ToolbarViewModel;

public class OrderDeliveryActivity extends BaseActivity {

    private ActivityOrderDeliveryBinding mBinding;
    private OrderDeliveryViewModel mViewModel;

    @Override
    public void initLayoutView() {
        super.initLayoutView();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_order_delivery);
    }

    @Override
    public void initView() {
        super.initView();
        mBinding.toolbarOrderDelivery.setVm(new ToolbarViewModel(getString(R.string.title_order_delivery), new ToolbarViewModel.OnLeftListen() {
            @Override
            public void onClickLeft() {
                finish();
            }
        }));
        mViewModel = new OrderDeliveryViewModel(this);
        mBinding.setVm(mViewModel);
    }
}
