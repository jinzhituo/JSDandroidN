package jsjh.king.com.jsdandroidn.model.model;


import jsjh.king.com.jsdandroidn.base.BaseLoadListener;
import jsjh.king.com.jsdandroidn.model.bean.UserBean;
import jsjh.king.com.jsdandroidn.model.modelimpl.LoginModelImpl;

/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class LoginModel implements LoginModelImpl {

    @Override
    public void toLogin(BaseLoadListener<UserBean> loadListener) {
        UserBean user = new UserBean();
        loadListener.loadSuccess(user);
    }

}
