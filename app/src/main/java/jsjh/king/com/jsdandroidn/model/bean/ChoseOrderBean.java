package jsjh.king.com.jsdandroidn.model.bean;


import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import jsjh.king.com.jsdandroidn.base.BaseBean;

/**
 * Created by MQ on 2017/5/16.
 */

public class ChoseOrderBean extends BaseBean {

    public final ObservableField<String> id = new ObservableField<>();
    public final ObservableField<String> orderNumber = new ObservableField<>();
    public final ObservableField<String> orderCommodity = new ObservableField<>();
    public final ObservableField<String> orderAmount = new ObservableField<>();
    public final ObservableField<String> orderAddress = new ObservableField<>();
    public final ObservableBoolean isSelect = new ObservableBoolean(false);

}
