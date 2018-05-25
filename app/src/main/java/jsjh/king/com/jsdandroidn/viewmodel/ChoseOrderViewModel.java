package jsjh.king.com.jsdandroidn.viewmodel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

import jsjh.king.com.jsdandroidn.adapter.ChoseCarAdapter;
import jsjh.king.com.jsdandroidn.adapter.ChoseOrderAdapter;
import jsjh.king.com.jsdandroidn.base.BaseLoadListener;
import jsjh.king.com.jsdandroidn.base.BaseViewModel;
import jsjh.king.com.jsdandroidn.common.MainConstant;
import jsjh.king.com.jsdandroidn.model.bean.ChoseOrderBean;
import jsjh.king.com.jsdandroidn.ui.MainActivity;


/**
 * Created by ShaoGeng on 2018/5/21.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class ChoseOrderViewModel extends BaseViewModel implements BaseLoadListener<List<ChoseOrderBean>> {
    public final ObservableBoolean isAllSelectShop = new ObservableBoolean(false);
    public final ObservableField<String> differMoney = new ObservableField<>("0");
    public final ObservableField<String> payExpress = new ObservableField<>("0");
    public final ObservableField<String> totalMoney = new ObservableField<>("0");
    public final ObservableField<String> providerName = new ObservableField<>("poke商店");
    public final ObservableField<ChoseOrderBean> beanS = new ObservableField<>();


    private ChoseOrderAdapter mAdapter;
    private Context mContext;
    private List<ChoseOrderBean> shopList = new ArrayList<>();

    public ChoseOrderViewModel(Context context, ChoseOrderAdapter adapter) {
        mContext = context;
        mAdapter = adapter;
        getNewsData();
        isAllSelectShop.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                if (isAllSelectShop.get()) {
                    for (ChoseOrderBean bean : shopList) {
                        bean.isSelect.set(true);
                    }
                } else {
                    for (ChoseOrderBean bean : shopList) {
                        bean.isSelect.set(false);
                    }
                }
            }
        });

        mAdapter.setOnItemChangeListener(new ChoseOrderAdapter.OnItemChangeListener() {
            @Override
            public void onItemChangeClick(int position) {
                upDataSelect();
            }
        });
    }

    public void clickTruckSuccess(){
        Intent it = new Intent(mContext , MainActivity.class);
        mContext.startActivity(it);
    }

    public void clickAllShop() {
        if (isAllSelectShop.get()) {
            isAllSelectShop.set(false);
        } else {
            isAllSelectShop.set(true);
        }
    }

    private void upDataSelect() {
        Boolean isS = true;
        if (shopList.size() == 0) {
            isAllSelectShop.set(false);
            return;
        }

        for (ChoseOrderBean bean : shopList) {
            if (!bean.isSelect.get()) {
                isS = false;
            }
        }

        isAllSelectShop.set(isS);
    }

    /**
     * 第一次获取数据
     */
    private void getNewsData() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        shopList.clear();
        for (int i = 0; i < 4; i++) {
            ChoseOrderBean bean = new ChoseOrderBean();
            bean.orderAddress.set("浙江省温州市鹿城区暂不选择南亚汽配城");
            bean.orderCommodity.set("国长浙江省温州市鹿城区暂不选择南亚汽配城");
            bean.orderAmount.set("447件");
            bean.orderNumber.set("45613156416535156");
//            bean.name.set("poke2018年特别装伯克灌装啤酒");
//            bean.specification.set("330ml/12");
//            beanS.set(bean);
            shopList.add(bean);
        }
        mAdapter.refreshData(shopList);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = MainConstant.LoadData.REFRESH;
        currPage = 1;

    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = MainConstant.LoadData.LOAD_MORE;
        currPage++;

    }

    @Override
    public void loadSuccess(List<ChoseOrderBean> response) {
    }

    @Override
    public void loadFailure(String message) {
    }

    @Override
    public void loadStart() {
    }

    @Override
    public void loadComplete() {
    }
}
