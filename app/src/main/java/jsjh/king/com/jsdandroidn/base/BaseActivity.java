package jsjh.king.com.jsdandroidn.base;

import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import jsjh.king.com.jsdandroidn.global.AppManager;
import jsjh.king.com.jsdandroidn.helper.LoadingDialogHelper;
import jsjh.king.com.jsdandroidn.utils.statusbarutil.StatusBarCompat;


/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseFuncImpl, View.OnClickListener {

    private Dialog loadingDialog;

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

    public void showToast(String msg){
        Toast.makeText(BaseActivity.this , msg , Toast.LENGTH_SHORT).show();
    }
}
