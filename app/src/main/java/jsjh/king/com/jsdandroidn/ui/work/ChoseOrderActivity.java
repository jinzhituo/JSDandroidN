package jsjh.king.com.jsdandroidn.ui.work;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.adapter.ChoseOrderAdapter;
import jsjh.king.com.jsdandroidn.base.BaseActivity;
import jsjh.king.com.jsdandroidn.databinding.ActivityChoseOrderBinding;
import jsjh.king.com.jsdandroidn.viewmodel.ChoseOrderViewModel;
import jsjh.king.com.jsdandroidn.viewmodel.ToolbarViewModel;

public class ChoseOrderActivity extends BaseActivity {

    private ChoseOrderViewModel mViewModel;
    private ActivityChoseOrderBinding mBinding;
    private ChoseOrderAdapter mAdapter;

    @Override
    public void initLayoutView() {
        super.initLayoutView();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chose_order);
    }

    @Override
    public void initView() {
        super.initView();
        mBinding.toolbarShopCat.setVm(new ToolbarViewModel(getString(R.string.title_chose_order), new ToolbarViewModel.OnLeftListen() {
            @Override
            public void onClickLeft() {
                finish();
            }
        }));
        initRecyclerView();
        mViewModel = new ChoseOrderViewModel(this, mAdapter);
        mBinding.setVm(mViewModel);
    }

    private void initRecyclerView() {
        mBinding.recyclerview.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        mBinding.recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        mBinding.recyclerview.setLoadingMoreEnabled(false);
        mBinding.recyclerview.setPullRefreshEnabled(false);
        mBinding.recyclerview.setArrowImageView(R.mipmap.ic_launcher);
        mBinding.recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.recyclerview.setLayoutManager(layoutManager);
        mAdapter = new ChoseOrderAdapter(this);
        mBinding.recyclerview.setAdapter(mAdapter);
    }
}
