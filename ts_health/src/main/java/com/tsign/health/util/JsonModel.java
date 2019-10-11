package com.tsign.health.util;


import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by haru on 2017/7/19.
 * data为简单对象或嵌套数组
 */

public class JsonModel<T> implements Serializable {

    private int code;
    private T data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static JsonModel fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(JsonModel.class, clazz);
        try {
            return gson.fromJson(json, objectType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(JsonModel.class, clazz);
        return gson.toJson(this, objectType);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            @Override
            public Type getRawType() {
                return raw;
            }

            @Override
            public Type[] getActualTypeArguments() {
                return args;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }
}
