package jsjh.king.com.jsdandroidn.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.adapter.WorkSendAdapter;
import jsjh.king.com.jsdandroidn.base.BaseFragment;
import jsjh.king.com.jsdandroidn.databinding.FragmentWorkBinding;
import jsjh.king.com.jsdandroidn.view.WorkSendView;
import jsjh.king.com.jsdandroidn.viewmodel.WorkFragmentViewModel;

/**
 * Created by ShaoGeng on 2018/5/21.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class WorkFragment extends BaseFragment implements WorkSendView {

    private View view;
    private FragmentWorkBinding mBinding;
    private WorkFragmentViewModel mViewModel;
    private WorkSendAdapter mAdapter;

    public static Fragment getInstance(String tagId) {
        WorkFragment fragment = new WorkFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tagId", tagId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View initLayout() {
        view = View.inflate(getActivity(), R.layout.fragment_work, null);
        view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mBinding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void initViewModel() {
        mViewModel = new WorkFragmentViewModel(view.getContext(), this, mAdapter);
        mBinding.setVm(mViewModel);
    }

    @Override
    public void initView() {
        initRecyclerView();
    }

    @Override
    public void initListener() {

    }

    private void initRecyclerView() {
        mBinding.recyclerview.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        mBinding.recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        mBinding.recyclerview.setArrowImageView(R.mipmap.ic_launcher);
        mBinding.recyclerview.setLoadingMoreEnabled(false);
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

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        mBinding.recyclerview.setLayoutManager(layoutManager);
        mAdapter = new WorkSendAdapter(view.getContext());
        mBinding.recyclerview.setAdapter(mAdapter);
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
