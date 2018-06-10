package jsjh.king.com.jsdandroidn.model.interceptor;

import java.util.HashMap;
import java.util.Map;

import jsjh.king.com.jsdandroidn.BuildConfig;
import jsjh.king.com.jsdandroidn.common.Constant;
import jsjh.king.com.jsdandroidn.utils.Base64Utils;
import jsjh.king.com.jsdandroidn.utils.DateUtil;
import jsjh.king.com.jsdandroidn.utils.JsonUtil;
import jsjh.king.com.jsdandroidn.utils.Md5Util;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by xuhaosong on 2017/11/1.
 */

public class ParamsBuilder {
    private Map<String,Object> paramsMap = new HashMap<>();

    public ParamsBuilder add(String key,Object value){
        paramsMap.put(key,value);
        return this;
    }

    public RequestBody build(){

        String currentDate = DateUtil.getCurrentDate();
        paramsMap.put("ostype", Constant.OS_TYPE_ANDROID);
        paramsMap.put("ver", BuildConfig.VERSION_NAME);
        paramsMap.put("time", DateUtil.getCurrentDateL());
//        paramsMap.put("key", Md5Util.encode(Base64Utils.getBase64("ibangoo_xiangdull11l")+currentDate).substring(0,30));
        String json = JsonUtil.parseMapToJson(paramsMap);
        return RequestBody.create(MediaType.parse("application/json"),json);
    }

    public  Map<String,Object> getParamsMap(){
        String currentDate = DateUtil.getCurrentDate();
        paramsMap.put("ostype", Constant.OS_TYPE_ANDROID);
        paramsMap.put("ver", BuildConfig.VERSION_NAME);
        paramsMap.put("time", currentDate);
        paramsMap.put("key", Md5Util.encode(Base64Utils.getBase64("jsjh")+currentDate).substring(0,30));
        return paramsMap;
    }
}
