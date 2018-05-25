package jsjh.king.com.jsdandroidn.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import jsjh.king.com.jsdandroidn.R;
import jsjh.king.com.jsdandroidn.model.bean.MenuBean;

/**
 * Created by ShaoGeng on 2018/5/24.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class MenuAdapter extends BaseAdapter {

    List<MenuBean> list;
    Context context;

    public MenuAdapter(Context context, List<MenuBean> list) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public Object getItem(int i) {
        return list.get(i);
    }


    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.item_list_menu, null);
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.menu_imageview);
            holder.imageInto = view.findViewById(R.id.menu_into);
            holder.textView = view.findViewById(R.id.menu_tv);
            holder.view = view.findViewById(R.id.menu_div);
            holder.linearLayout = view.findViewById(R.id.menu_layout);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.imageView.setImageResource(list.get(i).getMenuImage());
        holder.textView.setText(list.get(i).getMenuName());
        if (list.get(i).getSelect()) {
            holder.imageView.setImageResource(list.get(i).getMenuSelectImage());
            holder.textView.setTextColor(context.getResources().getColor(R.color.text_menu));
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.bg_menu));
            holder.view.setVisibility(View.VISIBLE);
        } else {
            holder.imageView.setImageResource(list.get(i).getMenuImage());
            holder.textView.setTextColor(context.getResources().getColor(R.color.text_666));
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorWhilt));
            holder.view.setVisibility(View.INVISIBLE);
        }
        if (list.get(i).getMenuName().equals(context.getString(R.string.title_menu_exit))) {
            holder.imageInto.setVisibility(View.GONE);
        }else {
            holder.imageInto.setVisibility(View.VISIBLE);
        }
        return view;
    }

    class ViewHolder {
        ImageView imageView;
        ImageView imageInto;
        View view;
        TextView textView;
        LinearLayout linearLayout;
    }
}