package jsjh.king.com.jsdandroidn.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import jsjh.king.com.jsdandroidn.BR;
import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.base.BaseAdapter;
import jsjh.king.com.jsdandroidn.base.BaseViewHolder;
import jsjh.king.com.jsdandroidn.model.bean.ChoseOrderBean;

/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class ChoseOrderAdapter extends BaseAdapter<ChoseOrderBean, BaseViewHolder> {

    private Context mContext;
    private OnItemChangeListener mOnItemChangeListener;

    public void setOnItemChangeListener(OnItemChangeListener mOnItemChangeListener){
        this.mOnItemChangeListener = mOnItemChangeListener;
    }

    public ChoseOrderAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public BaseViewHolder onCreateVH(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_list_chose_order, parent, false);
        return new BaseViewHolder(dataBinding);
    }

    @Override
    public void onBindVH(BaseViewHolder baseViewHolder, final int position) {
        ViewDataBinding binding = baseViewHolder.getBinding();
        binding.setVariable(BR.adapter , this);
        binding.setVariable(BR.position , position);
        binding.setVariable(BR.bean , mList.get(position));
        binding.executePendingBindings(); //防止闪烁
    }

    public void clickItem(ChoseOrderBean bean , int position){
//        Toast.makeText(mContext , "company:" + position , Toast.LENGTH_SHORT).show();
//        Intent it = new Intent(mContext , VisitDetailActivity.class);
//        it.putExtra("bean" , bean);
//        ((Activity)mContext).startActivity(it);
//        ((Activity)mContext).startActivity(new Intent(mContext , OrderDetailActivity.class));
//        ((Activity)mContext).finish();
//        ToastUtils.showShort("company" + position);
    }

    public void clickIsCheck(ChoseOrderBean bean , int position){
        if (bean.isSelect.get()){
            bean.isSelect.set(false);
        }else {
            bean.isSelect.set(true);
        }
        mOnItemChangeListener.onItemChangeClick(position);
    }

    public interface OnItemChangeListener{
        void onItemChangeClick(int position);
    }
}
