package com.ydh.live_data.utils;

import android.arch.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LiveDataBusBeta {

    private final Map<String, MutableLiveData<Object>> bus;

    private LiveDataBusBeta() {
        bus = new ConcurrentHashMap<>();
    }

    private static class SingleHolder {
        private static final LiveDataBusBeta DATA_BUS=new LiveDataBusBeta();
    }

    public static LiveDataBusBeta getInstance(){
        return SingleHolder.DATA_BUS;
    }

    public <T> MutableLiveData<T> with(String target ,Class<T> type){
        if (!bus.containsKey(target)){
            bus.put(target,new MutableLiveData<Object>());
        }
        return (MutableLiveData<T>) bus.get(target);
    }

    public MutableLiveData<Object> with(String target){
        return with(target,Object.class);
    }


}
