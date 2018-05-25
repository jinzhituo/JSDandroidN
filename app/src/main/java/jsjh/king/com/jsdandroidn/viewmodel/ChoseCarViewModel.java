package jsjh.king.com.jsdandroidn.viewmodel;

import android.content.Context;

import java.util.List;

import jsjh.king.com.jsdandroidn.adapter.ChoseCarAdapter;
import jsjh.king.com.jsdandroidn.adapter.LoginCompanyAdapter;
import jsjh.king.com.jsdandroidn.base.BaseLoadListener;
import jsjh.king.com.jsdandroidn.base.BaseViewModel;
import jsjh.king.com.jsdandroidn.common.MainConstant;
import jsjh.king.com.jsdandroidn.model.bean.ChoseCarBean;
import jsjh.king.com.jsdandroidn.model.bean.LoginCompanyBean;
import jsjh.king.com.jsdandroidn.model.model.ChoseCarModel;
import jsjh.king.com.jsdandroidn.model.model.LoginCompanyModel;
import jsjh.king.com.jsdandroidn.view.ChoseCarView;
import jsjh.king.com.jsdandroidn.view.LoginCompanyView;


/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class ChoseCarViewModel extends BaseViewModel implements BaseLoadListener<List<ChoseCarBean>> {

    private Context mContext;
    private ChoseCarAdapter adapter;
    private ChoseCarModel mModel;
    private ChoseCarView mView;

    public ChoseCarViewModel(Context context, ChoseCarView view, ChoseCarAdapter adapter) {
        mContext = context;
        this.adapter = adapter;
        mModel = new ChoseCarModel();
        mView = view;
        getNewsData();
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
    public void loadSuccess(List<ChoseCarBean> response) {
        if (currPage > 1) {
            //上拉加载的数据
            adapter.loadMoreData(response);
        } else {
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
