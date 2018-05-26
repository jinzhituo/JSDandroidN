package jsjh.king.com.jsdandroidn.model.bean;

import android.databinding.ObservableField;

import jsjh.king.com.jsdandroidn.base.BaseBean;

/**
 * Created by ShaoGeng on 2018/5/25.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class WorkSendBean extends BaseBean {
    public final ObservableField<String> state = new ObservableField<>();
    public final ObservableField<String> carNum = new ObservableField<>();
    public final ObservableField<String> carType = new ObservableField<>();
    public final ObservableField<String> mercharName = new ObservableField<>();
    public final ObservableField<String> commodity = new ObservableField<>();
    public final ObservableField<String> cNumber = new ObservableField<>();
    public final ObservableField<String> address = new ObservableField<>();
}
