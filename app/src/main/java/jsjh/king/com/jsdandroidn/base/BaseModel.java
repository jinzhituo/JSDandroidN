package jsjh.king.com.jsdandroidn.base;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import jsjh.king.com.jsdandroidn.model.service.ServiceFactory;
import okhttp3.ResponseBody;

/**
 * Created by xuhaosong on 2017/11/27.
 */

public class BaseModel {


    /**
     * 处理返回值为Bean对象接口数据
     * @param observable
     * @param observer
     * @param <T>
     */
    protected  <T extends BaseEntity>void addApiSubscribe(Observable<T> observable, DisposableObserver<T> observer) {
        observable.compose(ServiceFactory.schedulersTransformer).subscribe(observer);
    }

    /**
     * 处理返回值json接口数据
     * @param observable
     * @param observer
     */
    protected String addApiSubscribeForJson(Observable<ResponseBody> observable, DisposableObserver<String> observer){
        observable.map(new Function<ResponseBody, String>() {
            @Override
            public String apply(ResponseBody responseBody) throws Exception {
                String string = responseBody.string();
                return string;
            }
        }).compose(ServiceFactory.schedulersTransformer).subscribe(observer);
        return "";
    }
}
