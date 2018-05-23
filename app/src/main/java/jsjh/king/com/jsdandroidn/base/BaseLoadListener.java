package jsjh.king.com.jsdandroidn.base;

/**
 * Created by ShaoGeng on 2018/5/12.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public interface BaseLoadListener<T> {
    /**
     * 加载数据成功
     *
     * @param response list
     */
    void loadSuccess(T response);

    /**
     * 加载失败
     *
     * @param message
     */
    void loadFailure(String message);

    /**
     * 开始加载
     */
    void loadStart();

    /**
     * 加载结束
     */
    void loadComplete();
}
