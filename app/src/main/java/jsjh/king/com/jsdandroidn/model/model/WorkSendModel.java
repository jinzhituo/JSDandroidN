package jsjh.king.com.jsdandroidn.model.model;

import java.util.ArrayList;
import java.util.List;

import jsjh.king.com.jsdandroidn.base.BaseLoadListener;
import jsjh.king.com.jsdandroidn.model.bean.ChoseCarBean;
import jsjh.king.com.jsdandroidn.model.bean.WorkSendBean;
import jsjh.king.com.jsdandroidn.model.modelimpl.ChoseCarModelImpl;
import jsjh.king.com.jsdandroidn.model.modelimpl.WorkSendModelImpl;


/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class WorkSendModel implements WorkSendModelImpl {

    @Override
    public void getList(int currPage, BaseLoadListener<List<WorkSendBean>> loadListener) {

        List<WorkSendBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            WorkSendBean bean = new WorkSendBean();
            bean.address.set("鹿城区龙方工业区东龙路28号鹿城区龙方工业区东龙路28号");
            bean.carNum.set("123D3");
            bean.carType.set("货车");
            bean.mercharName.set("马云的超市");
            bean.commodity.set("马云的货品马云的货品马云的货品马云的货品");
            bean.cNumber.set("400");
            bean.state.set("已装载订单");
            list.add(bean);
        }
        loadListener.loadSuccess(list);
        loadListener.loadComplete();
    }
}
