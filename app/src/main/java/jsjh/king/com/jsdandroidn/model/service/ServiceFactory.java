package jsjh.king.com.jsdandroidn.model.service;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import jsjh.king.com.jsdandroidn.BuildConfig;
import jsjh.king.com.jsdandroidn.common.Constant;
import jsjh.king.com.jsdandroidn.model.interceptor.HeaderJsonInterceptor;
import jsjh.king.com.jsdandroidn.model.interceptor.ParamsInterceptor;
import jsjh.king.com.jsdandroidn.utils.Base64Utils;
import jsjh.king.com.jsdandroidn.utils.DateUtil;
import jsjh.king.com.jsdandroidn.utils.Md5Util;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * @author youhei
 * @version 1.0.0, 2017/1/9
 */
public class ServiceFactory {

    private volatile static Retrofit requestRetrofit;

    //测试服
    public static final String BASE_URL_RELEASE = BuildConfig.BASE_URL;

    //默认链接超时时间
    private static final int DEFAULT_TIMEOUT = 5;
    //默认服务器响应时间
    private static final int DEFAULT_READ_TIMEOUT = 5;

    private ServiceFactory() {

    }

    public static ParamsInterceptor getBaseParamsInterceptor() {
        //添加默认公共参数
        String currentDate = DateUtil.getCurrentDate();
        return new ParamsInterceptor.Builder()
                .addParam("ostype", Constant.OS_TYPE_ANDROID)
                .addParam("key", Md5Util.encode(Base64Utils.getBase64("jsjh_jswq") + currentDate).substring(0, 30))
                .addParam("ver", BuildConfig.VERSION_NAME)
                .addParam("time", currentDate)
                .build();
    }

    //日志拦截器
    public static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return loggingInterceptor;
    }

    public static OkHttpClient getDefaultClient() {
        Map<String, String> map = new HashMap<>();
        // FIXME: 2017/1/22 这里其实只针对GET进行cache，应该自定义缓存
//        File httpCacheDirectory = new File(SystemState.getExternalPath(PandaApp.getAppContext(), "PandaCache"), "HttpCache");
        HeaderJsonInterceptor jsonInterceptor = new HeaderJsonInterceptor();
        return new OkHttpClient.Builder()
                .addInterceptor(getBaseParamsInterceptor())
                .addInterceptor(jsonInterceptor)
                .addNetworkInterceptor(getLoggingInterceptor())
//                .cache(new Cache(httpCacheDirectory, 10 * 1024 * 1024))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    private static Retrofit getDefaultRetrofit() {
        //使用Retrofit结合RXjava，并且添加一些工具方法
        if (requestRetrofit == null) {
            synchronized (ServiceFactory.class) {
                if (requestRetrofit == null) {
                    requestRetrofit = new Retrofit.Builder()
                            .addConverterFactory(ResponseConvertFactory.create())
//                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .baseUrl(BASE_URL_RELEASE)
                            .client(getDefaultClient())
                            .build();
                }
            }
        }
        return requestRetrofit;
    }

    //处理线程调度的变换
    public static ObservableTransformer schedulersTransformer = new ObservableTransformer() {
        @Override
        public ObservableSource apply(Observable upstream) {
            return ((Observable) upstream).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    public static PersonService getMainService() {
        return getDefaultRetrofit().create(PersonService.class);
    }

}