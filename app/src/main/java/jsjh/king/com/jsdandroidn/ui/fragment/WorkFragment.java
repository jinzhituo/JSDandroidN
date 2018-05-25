package jsjh.king.com.jsdandroidn.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.base.BaseFragment;
import jsjh.king.com.jsdandroidn.databinding.FragmentWorkBinding;
import jsjh.king.com.jsdandroidn.viewmodel.WorkFragmentViewModel;

/**
 * Created by ShaoGeng on 2018/5/21.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class WorkFragment extends BaseFragment {

    private View view;
    private FragmentWorkBinding mBinding;
    private WorkFragmentViewModel mViewModel;

    public static Fragment getInstance(String tagId) {
        WorkFragment fragment = new WorkFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tagId", tagId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View initLayout() {
        view = View.inflate(getActivity(), R.layout.fragment_work, null);
        view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mBinding = DataBindingUtil.bind(view);
        return view;
    }

    @Override
    public void initViewModel() {
        mViewModel = new WorkFragmentViewModel(view.getContext());
        mBinding.setVm(mViewModel);
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
