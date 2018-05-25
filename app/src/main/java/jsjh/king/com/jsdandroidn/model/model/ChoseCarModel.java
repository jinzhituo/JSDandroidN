package jsjh.king.com.jsdandroidn.model.model;

import java.util.ArrayList;
import java.util.List;

import jsjh.king.com.jsdandroidn.base.BaseLoadListener;
import jsjh.king.com.jsdandroidn.model.bean.ChoseCarBean;
import jsjh.king.com.jsdandroidn.model.modelimpl.ChoseCarModelImpl;


/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class ChoseCarModel implements ChoseCarModelImpl {

    @Override
    public void getList(int currPage, BaseLoadListener<List<ChoseCarBean>> loadListener) {

        List<ChoseCarBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ChoseCarBean bean = new ChoseCarBean();
            if (i%3 == 0){
                bean.carType.set("货车");
            }else if (i%3 == 1){
                bean.carType.set("轿车");
            }else {
                bean.carType.set("面包车");
            }
            list.add(bean);
        }
        loadListener.loadSuccess(list);
        loadListener.loadComplete();
    }
}
