package jsjh.king.com.jsdandroidn.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ShaoGeng on 2018/5/25.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class BaseEntity<E> {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String msg;
    @SerializedName("data")
    private E data;

    public boolean isSuccess() {
        return code == 200;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
