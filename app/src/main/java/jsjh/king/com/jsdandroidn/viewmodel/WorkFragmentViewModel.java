package jsjh.king.com.jsdandroidn.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;

import jsjh.king.com.jsdandroidn.base.BaseViewModel;
import jsjh.king.com.jsdandroidn.ui.work.ChoseCarActivity;

/**
 * Created by ShaoGeng on 2018/5/24.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class WorkFragmentViewModel extends BaseViewModel {

    public Context mContext;
    public final ObservableBoolean isWorkLoading = new ObservableBoolean(true);

    public WorkFragmentViewModel(Context context) {
        mContext = context;
    }

    public void clickToChoseCar() {
        Intent it = new Intent(mContext, ChoseCarActivity.class);
        mContext.startActivity(it);
    }
}
