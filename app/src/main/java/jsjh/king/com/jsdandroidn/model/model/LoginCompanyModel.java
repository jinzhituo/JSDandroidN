package jsjh.king.com.jsdandroidn.model.model;

import java.util.ArrayList;
import java.util.List;

import jsjh.king.com.jsdandroidn.base.BaseLoadListener;
import jsjh.king.com.jsdandroidn.model.bean.LoginCompanyBean;
import jsjh.king.com.jsdandroidn.model.modelimpl.LoginCompanyModelImpl;


/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class LoginCompanyModel implements LoginCompanyModelImpl {
    @Override
    public void getLoginCompany(int currPage, BaseLoadListener<List<LoginCompanyBean>> loadListener) {
        LoginCompanyBean bean = new LoginCompanyBean();
        bean.id.set("123");
        bean.companyName.set("我是一个公司名字");
        List<LoginCompanyBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(bean);
        }
        loadListener.loadSuccess(list);
        loadListener.loadComplete();
    }
}
