package jsjh.king.com.jsdandroidn.base;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * Created by ShaoGeng on 2018/5/12.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class BaseViewModel {

    public final ObservableBoolean isLoading = new ObservableBoolean(false);//加载框是否弹出
    public final ObservableField<String> titleToolbar = new ObservableField<>();//改变导航栏title
    public final ObservableField<String> snackText = new ObservableField<>();//底部通知需要的msg
    public final ObservableField<String> toastText = new ObservableField<>();//toast 需要的msg
    public final ObservableField<String> title = new ObservableField<>();
    public final ObservableField<String> subtitle = new ObservableField<>();

    public int currPage = 1; //当前页数
    public int loadType; //加载数据的类型

}
