package jsjh.king.com.jsdandroidn.model.modelimpl;

import java.util.List;

import jsjh.king.com.jsdandroidn.base.BaseLoadListener;
import jsjh.king.com.jsdandroidn.model.bean.ChoseCarBean;
import jsjh.king.com.jsdandroidn.model.bean.WorkSendBean;

/**
 * Created by ShaoGeng on 2018/5/24.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public interface WorkSendModelImpl {
    void getList(int currPage, BaseLoadListener<List<WorkSendBean>> loadListener);
}
