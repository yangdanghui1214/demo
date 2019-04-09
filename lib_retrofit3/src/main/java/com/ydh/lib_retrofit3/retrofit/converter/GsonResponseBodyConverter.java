package com.ydh.lib_retrofit3.retrofit.converter;

import com.google.gson.TypeAdapter;
import com.ydh.lib_retrofit3.exception.ServerException;
import com.ydh.lib_retrofit3.model.BasicResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, Object> {

    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public Object convert(ResponseBody value) throws IOException {
        try {
            BasicResponse response = (BasicResponse) adapter.fromJson(value.charStream());
            if (response.getCode()==200) {
                return response.getData();
            } else {
                // 特定 API 的错误，在相应的 DefaultObserver 的 onError 的方法中进行处理
                throw new ServerException(response.getCode(), response.getMsg());
            }
        } finally {
            value.close();
        }
    }
}