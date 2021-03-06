package jsjh.king.com.jsdandroidn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.model.bean.AddressBookBean;
import jsjh.king.com.jsdandroidn.utils.ColorGenerator;
import jsjh.king.com.jsdandroidn.utils.TextDrawable;


/**
 * Created by MQ on 2017/5/8.
 */

public class AddressBookAdapter extends RecyclerView.Adapter<AddressBookAdapter.MyRecycleHolder> {

    private List<AddressBookBean> contactBeanList;
    private Context mContext;
    // declare the color generator and drawable builder
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable.IBuilder mDrawableBuilder = TextDrawable.builder().round();

    public AddressBookAdapter(Context context) {
        this.mContext = context;
        contactBeanList = new ArrayList<>();
    }

    public void addAll(List<AddressBookBean> beans) {
        if (contactBeanList.size() > 0) {
            contactBeanList.clear();
        }
        contactBeanList.addAll(beans);
        notifyDataSetChanged();
    }

    public void add(AddressBookBean bean, int position) {
        contactBeanList.add(position, bean);
        notifyItemInserted(position);
    }

    public void add(AddressBookBean bean) {
        contactBeanList.add(bean);
        notifyItemChanged(contactBeanList.size() - 1);
    }

    @Override
    public MyRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_address_book, parent, false);
        return new MyRecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecycleHolder holder, int position) {
        if (contactBeanList == null || contactBeanList.size() == 0 || contactBeanList.size() <= position)
            return;
        AddressBookBean bean = contactBeanList.get(position);
        if (bean != null) {
            holder.tv_name.setText(bean.getName());
            TextDrawable drawable = mDrawableBuilder.build(String.valueOf(bean.getName().charAt(0)), mColorGenerator.getColor(bean.getName()));
            holder.iv_img.setImageDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() {
        return contactBeanList.size();
    }

    public static class MyRecycleHolder extends RecyclerView.ViewHolder {
        public final TextView tv_name;
        public final ImageView iv_img;

        public MyRecycleHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
        }
    }
}
