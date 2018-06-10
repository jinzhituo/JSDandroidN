package jsjh.king.com.jsdandroidn.model.interceptor;


import java.io.IOException;

import jsjh.king.com.jsdandroidn.common.Constant;
import jsjh.king.com.jsdandroidn.utils.SpUtil;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ShaoGeng on 2018/5/25.
 * email 1002919029@qq.com
 * GitHub https://github.com/shaogeng1994
 */

public class HeaderJsonInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String s = SpUtil.getString(Constant.USER_TOKEN);
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", SpUtil.getString(Constant.USER_TOKEN))
                .build();
        return chain.proceed(request);
    }
}
