package jsjh.king.com.jsdandroidn.helper;

import android.app.Dialog;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import jsjh.king.com.jsdandroidn.R;


/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class LoadingDialogHelper {
    public static Dialog getLoading(Context context) {
        Dialog dialog = new Dialog(context, R.style.loading_dialog);
        dialog.setContentView(R.layout.view_dialog_loading);//此处布局为一个progressbar
        dialog.setCancelable(false); // 可以取消

        ImageView imageView = (ImageView) dialog.findViewById(R.id.iv_view_dialog_loading);
        Glide.with(context)
                .load(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
        return dialog;
    }
}
