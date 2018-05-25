package jsjh.king.com.jsdandroidn.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.base.BaseFragment;
import jsjh.king.com.jsdandroidn.databinding.FragmentAboutBinding;

/**
 * Created by ShaoGeng on 2018/5/21.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class AboutFragment extends BaseFragment{

    private View view;
    private FragmentAboutBinding mBinding;

    public static Fragment getInstance(String tagId) {
        AboutFragment fragment = new AboutFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tagId", tagId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View initLayout() {
        view = View.inflate(getActivity(), R.layout.fragment_about, null);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mBinding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void initViewModel() {

    }

    @Override
    public void initView() {
        initRecyclerView();
    }

    @Override
    public void initListener() {

    }

    private void initRecyclerView() {

    }
}
