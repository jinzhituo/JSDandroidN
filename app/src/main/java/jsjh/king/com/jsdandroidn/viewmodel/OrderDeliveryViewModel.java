package jsjh.king.com.jsdandroidn.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.adapters.TextViewBindingAdapter;
import android.text.Editable;

import jsjh.king.com.jsdandroidn.base.BaseViewModel;


/**
 * Created by ShaoGeng on 2018/5/16.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class OrderDeliveryViewModel extends BaseViewModel {

    private Context mContext;

    public final ObservableField<String> eidtLenght = new ObservableField<>("0");
    public final ObservableField<String> editPurpose = new ObservableField<>();


    public OrderDeliveryViewModel(Context context) {
        mContext = context;
    }

    public TextViewBindingAdapter.AfterTextChanged getAfterTextChanged() {
        return new TextViewBindingAdapter.AfterTextChanged() {
            @Override
            public void afterTextChanged(Editable s) {
                eidtLenght.set(String.valueOf(s.length()));
            }
        };
    }

    public void finishActivity(){
        ((Activity)mContext).finish();
    }
}
