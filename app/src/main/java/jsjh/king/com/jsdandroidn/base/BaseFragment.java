package jsjh.king.com.jsdandroidn.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.helper.LoadingDialogHelper;
import jsjh.king.com.jsdandroidn.utils.ViewUtil;


/**
 * Created by xuhaosong on 2017/9/4.
 */

public abstract class BaseFragment extends Fragment {
    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;

    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */
    private LayoutInflater inflater;
    private RelativeLayout rootView;
    private View mContentView;
    protected LinearLayout mLoadView = null;
    private View mEmptyView;
    protected Dialog loadingDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        if (rootView == null) {
            rootView = new RelativeLayout(this.getActivity());
            mContentView = initLayout();
            rootView.addView(mContentView);
            showContentView();
            this.initView();
            this.initViewModel();
            this.initListener();
        }
        ViewUtil.removeSelfFromParent(rootView);
        return rootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
    }

    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (rootView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    /**
     * 显示内容View
     */
    public void showContentView() {
        if (rootView.getChildCount() > 0) {
            if (mContentView == rootView.getChildAt(0)) {
                return;
            }
        }
        rootView.removeAllViews();
        ViewUtil.removeSelfFromParent(mContentView);
        rootView.addView(mContentView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * 显示自定义Dialog、loading   - view_dialog_loading
     */
    protected void showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialogHelper.getLoading(rootView.getContext());
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
     * 初始化加载View
     */
    public void initLoadView() {
        mLoadView = (LinearLayout) inflater.inflate(R.layout.view_dialog_loading, null);
        mLoadView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    /**
     * 显示加载View
     */
    public void showLoadView() {
        if (rootView.getChildCount() > 0) {
            if (mLoadView == rootView.getChildAt(0)) {
                return;
            }
        }

        rootView.removeAllViews();
        if (mLoadView == null) {
            initLoadView();
        }
        ViewUtil.removeSelfFromParent(mLoadView);
        rootView.addView(mLoadView);
    }
    /**
     * 显示空View
     */
    public static final int EmptyType_NETWORK = 2003;
    public static final int EmptyType_SHOUCANG = 2004;
    public static final int EmptyType_SEARCH = 2005;
    public static final int EmptyType_BOOK = 2006;
    public static final int EmptyType_MSG = 2007;

    public void showEmptyView(int type){
        if (rootView.getChildCount() > 0) {
            if (mEmptyView == rootView.getChildAt(0)) {
                return;
            }
        }
        rootView.removeAllViews();

        if (mEmptyView == null) {
            initEmptyView();
        }
        ImageView emptyImg = mEmptyView.findViewById(R.id.iv_empty);
        switch (type){
            case EmptyType_NETWORK:
                emptyImg.setImageResource(R.drawable.ic_loading_rotate);
                break;
            case EmptyType_BOOK:
                emptyImg.setImageResource(R.drawable.ic_loading_rotate);
                break;
            case EmptyType_SHOUCANG:
                emptyImg.setImageResource(R.drawable.ic_loading_rotate);
                break;
            case EmptyType_SEARCH:
                emptyImg.setImageResource(R.drawable.ic_loading_rotate);
                break;
            case EmptyType_MSG:
                emptyImg.setImageResource(R.drawable.ic_loading_rotate);
                break;
        }

        ViewUtil.removeSelfFromParent(mEmptyView);
        rootView.addView(mEmptyView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void showEmptyView(int type,int width,int hight){
        if (rootView.getChildCount() > 0) {
            if (mEmptyView == rootView.getChildAt(0)) {
                return;
            }
        }
        rootView.removeAllViews();

        if (mEmptyView == null) {
            initEmptyView();
        }
        ImageView emptyImg = mEmptyView.findViewById(R.id.iv_empty);
        emptyImg.setLayoutParams(new LinearLayout.LayoutParams(width,hight));
        switch (type){
            case EmptyType_NETWORK:
                emptyImg.setImageResource(R.drawable.ic_loading_rotate);
                break;
            case EmptyType_BOOK:
                emptyImg.setImageResource(R.drawable.ic_loading_rotate);
                break;
            case EmptyType_SHOUCANG:
                emptyImg.setImageResource(R.drawable.ic_loading_rotate);
                break;
            case EmptyType_SEARCH:
                emptyImg.setImageResource(R.drawable.ic_loading_rotate);
                break;
            case EmptyType_MSG:
                emptyImg.setImageResource(R.drawable.ic_loading_rotate);
                break;
        }
        ViewUtil.removeSelfFromParent(mEmptyView);
        rootView.addView(mEmptyView, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void initEmptyView(){
        mEmptyView = inflater.inflate(R.layout.view_empty, null);
    }

    /**************************************************************
     *  自定义的回调方法，子类可根据需求重写
     *************************************************************/

    /**
     * 当前fragment可见状态发生变化时会回调该方法
     * 如果当前fragment是第一次加载，等待onCreateView后才会回调该方法，其它情况回调时机跟 {@link #setUserVisibleHint(boolean)}一致
     * 在该回调方法中你可以做一些加载数据操作，甚至是控件的操作，因为配合fragment的view复用机制，你不用担心在对控件操作中会报 null 异常
     *
     * @param isVisible true  不可见 -> 可见
     *                  false 可见  -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
    }

    //获取布局文件
    public abstract View initLayout();

    //简单页面无需mvvm就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initViewModel();

    //初始化view
    public abstract void initView();

    //初始化view 监听
    public abstract void initListener();
}
