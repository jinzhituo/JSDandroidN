package jsjh.king.com.jsdandroidn.ui.work;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.adapter.ChoseCarAdapter;
import jsjh.king.com.jsdandroidn.base.BaseActivity;
import jsjh.king.com.jsdandroidn.databinding.ActivityChoseCarBinding;
import jsjh.king.com.jsdandroidn.view.ChoseCarView;
import jsjh.king.com.jsdandroidn.viewmodel.ChoseCarViewModel;
import jsjh.king.com.jsdandroidn.viewmodel.ToolbarViewModel;

public class ChoseCarActivity extends BaseActivity implements ChoseCarView {

    private ActivityChoseCarBinding mBinding;
    private ChoseCarViewModel mViewModel;
    private ChoseCarAdapter adapter;

    @Override
    public void initLayoutView() {
        super.initLayoutView();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_chose_car);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initView() {
        super.initView();
        initRecyclerView();
        mViewModel = new ChoseCarViewModel(this, this, adapter);
        mBinding.setVm(mViewModel);
        mBinding.toolbarChoseCar.setVm(new ToolbarViewModel(getString(R.string.title_chose_car), new ToolbarViewModel.OnLeftListen() {
            @Override
            public void onClickLeft() {
                finish();
            }
        }));
//        setStatusBarColor(R.color.colorWhilt);
    }

    @Override
    public void initLoad() {
        super.initLoad();
    }

    private void initRecyclerView() {
        mBinding.recyclerview.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        mBinding.recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        mBinding.recyclerview.setLoadingMoreEnabled(false);
        mBinding.recyclerview.setArrowImageView(R.mipmap.ic_launcher);
        mBinding.recyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mViewModel.loadRefreshData();
            }

            @Override
            public void onLoadMore() {
                mViewModel.loadMoreData();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mBinding.recyclerview.setLayoutManager(layoutManager);
        adapter = new ChoseCarAdapter(this);
        mBinding.recyclerview.setAdapter(adapter);
    }

    @Override
    public void loadStart(int loadType) {

    }

    @Override
    public void loadComplete() {
        mBinding.recyclerview.loadMoreComplete(); //结束加载
        mBinding.recyclerview.refreshComplete(); //结束刷新
    }

    @Override
    public void loadFailure(String message) {
        mBinding.recyclerview.loadMoreComplete(); //结束加载
        mBinding.recyclerview.refreshComplete(); //结束刷新
    }
}

