package jsjh.king.com.jsdandroidn.model.service;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Charles on 2016/3/17.
 */
class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Log.d("Network", "response>>" + response);

//        BaseEntity baseResult = gson.fromJson(response, BaseEntity.class);
//        if (!baseResult.getStatus()) {
//            if (baseResult.getData() != null && !StringUtil.isEmpty(baseResult.getData().toString())) {
//                throw new ApiException(baseResult.getData().toString());
//            } else {
//                throw new ApiException(baseResult.getInfo());
//            }
//        }
        return gson.fromJson(response, type);
    }
}
