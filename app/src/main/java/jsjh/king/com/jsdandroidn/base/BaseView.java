package jsjh.king.com.jsdandroidn.base;

/**
 * Created by ShaoGeng on 2018/5/12.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public interface BaseView {
    /**
     * 开始加载
     *
     * @param loadType 加载的类型 0：第一次记载 1：下拉刷新 2：上拉加载更多
     */
    void loadStart(int loadType);

    /**
     * 加载完成
     */
    void loadComplete();

    /**
     * 加载失败
     *
     * @param message
     */
    void loadFailure(String message);
}
