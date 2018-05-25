package jsjh.king.com.jsdandroidn.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */
public class ToolbarViewModel extends BaseObservable {

    public final ObservableField<String> title = new ObservableField<>("");
    public final ObservableField<String> subtitle = new ObservableField<>("");
    public final ObservableBoolean lefiImage = new ObservableBoolean(false);
    public final ObservableBoolean isRightSubtitle = new ObservableBoolean(false);

    private OnLeftListen mOnLeftListen;
    private OnRightSubtitleListen mOnRightSubtitleListen;

    public ToolbarViewModel(String title) {
        this.title.set(title);
        this.lefiImage.set(false);
        this.isRightSubtitle.set(false);
    }

    public ToolbarViewModel(String title, OnLeftListen onLeftListen) {
        this.title.set(title);
        this.isRightSubtitle.set(false);
        this.lefiImage.set(true);
        mOnLeftListen = onLeftListen;
    }

    public ToolbarViewModel(String title, String subtitle, OnLeftListen onLeftListen, OnRightSubtitleListen onRightSubtitleListen) {
        this.title.set(title);
        this.subtitle.set(subtitle);
        this.lefiImage.set(true);
        this.isRightSubtitle.set(true);
        mOnLeftListen = onLeftListen;
        mOnRightSubtitleListen = onRightSubtitleListen;
    }

    public void onLeftClick() {
        mOnLeftListen.onClickLeft();
    }

    public void onRightSubtitleClick() {
        mOnRightSubtitleListen.onClickRight();
    }

    public interface OnLeftListen {
        void onClickLeft();
    }

    public interface OnRightSubtitleListen {
        void onClickRight();
    }
}
