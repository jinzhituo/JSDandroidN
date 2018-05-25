package jsjh.king.com.jsdandroidn.model.modelimpl;

import java.util.List;

import jsjh.king.com.jsdandroidn.base.BaseLoadListener;
import jsjh.king.com.jsdandroidn.model.bean.ChoseCarBean;

/**
 * Created by ShaoGeng on 2018/5/24.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public interface ChoseCarModelImpl {
    void getList(int currPage, BaseLoadListener<List<ChoseCarBean>> loadListener);
}
