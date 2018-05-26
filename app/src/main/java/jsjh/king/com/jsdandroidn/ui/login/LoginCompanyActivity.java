package jsjh.king.com.jsdandroidn.ui.login;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.adapter.LoginCompanyAdapter;
import jsjh.king.com.jsdandroidn.base.BaseActivity;
import jsjh.king.com.jsdandroidn.databinding.ActivityLoginCompanyBinding;
import jsjh.king.com.jsdandroidn.view.LoginCompanyView;
import jsjh.king.com.jsdandroidn.viewmodel.LoginCompanyViewModel;


/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class LoginCompanyActivity extends BaseActivity implements LoginCompanyView {

    private ActivityLoginCompanyBinding mBinding;
    private LoginCompanyViewModel mViewModel;
    private LoginCompanyAdapter adapter;

    @Override
    public void initLayoutView() {
        super.initLayoutView();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_company);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initView() {
        super.initView();
        initRecyclerView();
        mViewModel = new LoginCompanyViewModel(this, this, adapter);
        mBinding.setVm(mViewModel);
//        setStatusBarColor(R.color.colorWhilt);
    }

    @Override
    public void initLoad() {
        super.initLoad();
    }

    private void initRecyclerView() {
        mBinding.recyclerviewLoginCompany.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        mBinding.recyclerviewLoginCompany.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        mBinding.recyclerviewLoginCompany.setArrowImageView(R.mipmap.ic_launcher);
        mBinding.recyclerviewLoginCompany.setLoadingListener(new XRecyclerView.LoadingListener() {
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
        mBinding.recyclerviewLoginCompany.setLayoutManager(layoutManager);
        adapter = new LoginCompanyAdapter(this);
        mBinding.recyclerviewLoginCompany.setAdapter(adapter);
    }

    @Override
    public void loadStart(int loadType) {

    }

    @Override
    public void loadComplete() {
        mBinding.recyclerviewLoginCompany.loadMoreComplete(); //结束加载
        mBinding.recyclerviewLoginCompany.refreshComplete(); //结束刷新
    }

    @Override
    public void loadFailure(String message) {

        mBinding.recyclerviewLoginCompany.loadMoreComplete(); //结束加载
        mBinding.recyclerviewLoginCompany.refreshComplete(); //结束刷新
    }
}
