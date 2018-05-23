package jsjh.king.com.jsdandroidn.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.blankj.utilcode.util.Utils;


/**
 * Created by ShaoGeng on 2018/5/14.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class MyApplication extends Application {

    public static Context context;
    private static MyApplication instance;
    private static Handler mainHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        instance = this;
        mainHandler = new Handler();
        Utils.init(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getMainHandler() {
        return mainHandler;
    }

    //static 代码段可以防止内存泄露
    static {
//        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
//            @Override
//            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.bg_ccc, android.R.color.white);//全局设置主题颜色
//                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
//            }
//        });
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
//            @Override
//            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
//                //指定为经典Footer，默认是 BallPulseFooter
//                return new ClassicsFooter(context).setDrawableSize(20);
//            }
//        });
    }
}
