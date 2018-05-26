package jsjh.king.com.jsdandroidn.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;

import java.util.ArrayList;
import java.util.List;

import jsjh.king.com.jsdandroidn.adapter.WorkSendAdapter;
import jsjh.king.com.jsdandroidn.base.BaseLoadListener;
import jsjh.king.com.jsdandroidn.base.BaseViewModel;
import jsjh.king.com.jsdandroidn.common.MainConstant;
import jsjh.king.com.jsdandroidn.model.bean.LoginCompanyBean;
import jsjh.king.com.jsdandroidn.model.bean.WorkSendBean;
import jsjh.king.com.jsdandroidn.model.model.WorkSendModel;
import jsjh.king.com.jsdandroidn.ui.work.ChoseCarActivity;
import jsjh.king.com.jsdandroidn.view.WorkSendView;

/**
 * Created by ShaoGeng on 2018/5/24.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class WorkFragmentViewModel extends BaseViewModel implements BaseLoadListener<List<WorkSendBean>> {

    public Context mContext;
    public final ObservableBoolean isWorkLoading = new ObservableBoolean(true);
    public final ObservableBoolean isWorkSend = new ObservableBoolean(true);
    private WorkSendAdapter adapter;
    private WorkSendModel mModel;
    private WorkSendView mView;
    private List<WorkSendBean> list = new ArrayList<>();

    public WorkFragmentViewModel(Context context, WorkSendView view, WorkSendAdapter adapter) {
        mContext = context;
        this.adapter = adapter;
        mModel = new WorkSendModel();
        mView = view;
        getNewsData();
    }

    public void clickToSend() {
        isWorkSend.set(false);
        for (WorkSendBean bean : list) {
            bean.state.set("配送中");
        }
        adapter.refreshData(list);
    }

    /**
     * 第一次获取数据
     */
    private void getNewsData() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        mModel.getList(currPage, this);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = MainConstant.LoadData.REFRESH;
        currPage = 1;
        mModel.getList(currPage, this);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = MainConstant.LoadData.LOAD_MORE;
        currPage++;
        mModel.getList(currPage, this);
    }

    @Override
    public void loadSuccess(List<WorkSendBean> response) {
        if (currPage > 1) {
            //上拉加载的数据
            list.addAll(response);
            adapter.loadMoreData(list);
        } else {
            list.clear();
            list.addAll(response);
            //第一次加载或者下拉刷新的数据
            adapter.refreshData(response);
        }
    }

    @Override
    public void loadFailure(String message) {
        mView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mView.loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mView.loadComplete();
    }
}
