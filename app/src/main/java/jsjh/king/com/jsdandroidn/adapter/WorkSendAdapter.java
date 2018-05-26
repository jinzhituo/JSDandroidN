package jsjh.king.com.jsdandroidn.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;
import android.widget.ImageView;

import jsjh.king.com.jsdandroidn.BR;
import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.base.BaseAdapter;
import jsjh.king.com.jsdandroidn.base.BaseViewHolder;
import jsjh.king.com.jsdandroidn.model.bean.ChoseCarBean;
import jsjh.king.com.jsdandroidn.model.bean.WorkSendBean;
import jsjh.king.com.jsdandroidn.ui.work.ChoseOrderActivity;
import jsjh.king.com.jsdandroidn.ui.work.OrderDeliveryActivity;


/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class WorkSendAdapter extends BaseAdapter<WorkSendBean, BaseViewHolder> {

    public WorkSendAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_list_work_send, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.adapter, this);
        binding.setVariable(BR.position, position);
        binding.setVariable(BR.bean, mList.get(position));
        if (mList.get(position).state.get().equals("已装载订单")) {
            binding.setVariable(BR.isSend, false);
        } else {
            binding.setVariable(BR.isSend, true);
        }
        binding.executePendingBindings(); //防止闪烁
    }

    public void clickRightBtn(WorkSendBean loginCompanyBean, int position, Boolean isSend) {
        if (isSend) {
            ((Activity) mContext).startActivity(new Intent(mContext, OrderDeliveryActivity.class));
        } else {

        }
//        Toast.makeText(mContext , "company:" + position , Toast.LENGTH_SHORT).show();
//        ToastUtils.showShort("company" + position);
    }
}
