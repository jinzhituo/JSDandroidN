package jsjh.king.com.jsdandroidn.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;
import android.widget.Toast;

import jsjh.king.com.jsdandroidn.BR;
import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.base.BaseAdapter;
import jsjh.king.com.jsdandroidn.base.BaseViewHolder;
import jsjh.king.com.jsdandroidn.model.bean.LoginCompanyBean;
import jsjh.king.com.jsdandroidn.ui.MainActivity;


/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class LoginCompanyAdapter extends BaseAdapter<LoginCompanyBean, BaseViewHolder> {

    public LoginCompanyAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_list_login_company, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.adapter , this);
        binding.setVariable(BR.position , position);
        binding.setVariable(BR.bean , mList.get(position));
        binding.executePendingBindings(); //防止闪烁
    }

    public void clickCompanyItem(LoginCompanyBean loginCompanyBean , int position){
        Toast.makeText(mContext , "company:" + position , Toast.LENGTH_SHORT).show();
        ((Activity)mContext).startActivity(new Intent(mContext , MainActivity.class));
        ((Activity)mContext).finish();
//        ToastUtils.showShort("company" + position);
    }
}
