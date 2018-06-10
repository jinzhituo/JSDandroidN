package jsjh.king.com.jsdandroidn.base;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.maning.mndialoglibrary.MProgressDialog;
import com.maning.mndialoglibrary.MStatusDialog;
import com.maning.mndialoglibrary.config.MDialogConfig;
import com.maning.mndialoglibrary.listeners.OnDialogDismissListener;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.global.AppManager;
import jsjh.king.com.jsdandroidn.helper.LoadingDialogHelper;
import jsjh.king.com.jsdandroidn.utils.DisplayUtils;
import jsjh.king.com.jsdandroidn.utils.statusbarutil.StatusBarCompat;


/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseFuncImpl, View.OnClickListener {

    private Dialog loadingDialog;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        initLayoutView();
        initData();
        initView();
        initLoad();
        initListener();
    }

    /**
     * 显示自定义Dialog、loading   - view_dialog_loading
     */
    protected void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialogHelper.getLoading(this.getApplicationContext());
        }
        loadingDialog.show();
    }

    /**
     * 隐藏自定义Dialog、loading   - view_dialog_loading
     */
    protected void hideLoading() {
        if (loadingDialog != null) loadingDialog.cancel();
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }


    @Override
    public void initParams(Bundle bundle) {

    }

    @Override
    public void initLayoutView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initLoad() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (DisplayUtils.isShouldHideInputB(v, event)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(event);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(event)) {
            return true;
        }
        return onTouchEvent(event);
    }


    /**
     * 着色状态栏（4.4以上系统有效）
     *
     * @param color 颜色
     */
    protected void setStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     *
     * @param color 颜色
     * @param alpha 透明度
     */
    protected void setStatusBarColor(int color, int alpha) {
        StatusBarCompat.setStatusBarColor(this, color, alpha);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void setTranslanteBar() {
//        StatusBarSetting.setTranslucent(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    public void setToolbar(Toolbar toolbar) {
        setToolbar(toolbar, true);
    }

    public void setToolbar(Toolbar toolbar, boolean hasNav) {
        setSupportActionBar(toolbar);
        if (!hasNav) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void showToast(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void showProgressDialog(String text) {
        MProgressDialog.showProgress(this, text);
    }

    public void hideProgressDialog() {
        if (MProgressDialog.isShowing()) {
            MProgressDialog.dismissProgress();
        }
    }

    public void showDismissProgressDialog(String text) {
        MProgressDialog.showProgress(this, text);
        //延时关闭
        delayDismissProgressDialog();
    }

    public void showStatusDialog01(String text) {
        new MStatusDialog(this).show(text, this.getResources().getDrawable(R.mipmap.mn_icon_dialog_ok));
    }

    public void showStatusDialog02(String text, OnDialogDismissListener listener) {
        MDialogConfig mDialogConfig = new MDialogConfig.Builder()
//            //全屏背景窗体的颜色
//            .setBackgroundWindowColor(getMyColor(R.color.colorDialogWindowBg))
//            //View背景的颜色
//            .setBackgroundViewColor(getMyColor(R.color.colorDialogViewBg2))
//            //字体的颜色
//            .setTextColor(getMyColor(R.color.colorAccent))
//            //View边框的颜色
//            .setStrokeColor(getMyColor(R.color.white))
                //View边框的宽度
                .setStrokeWidth(0)
                //View圆角大小
                .setCornerRadius(10)
                //关闭的监听
                .setOnDialogDismissListener(listener)
                .build();
        new MStatusDialog(this, mDialogConfig).show(text, this.getResources().getDrawable(R.mipmap.mn_icon_dialog_ok), 1000);
    }

    private void delayDismissProgressDialog() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                MProgressDialog.dismissProgress();
            }
        }, 3000);
    }

    public void hideInputManager() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }

}
