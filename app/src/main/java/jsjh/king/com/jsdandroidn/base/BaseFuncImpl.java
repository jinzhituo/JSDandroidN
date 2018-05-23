package jsjh.king.com.jsdandroidn.base;

import android.os.Bundle;

/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public interface BaseFuncImpl {
    void initParams(Bundle bundle);
    /*
    * 初始化页面布局layout-id
    * */
    void initLayoutView();
    /*
    * 初始化数据 （数据库 ， Bundle等）
    * */
    void initData();
    /*
    * 初始化视图
    * */
    void initView();
    /*
    * 加载一系列等待
    * */
    void initLoad();
    /*
    * 加载监听
    * */
    void initListener();

}
