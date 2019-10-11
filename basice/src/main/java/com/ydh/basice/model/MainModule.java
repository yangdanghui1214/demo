package com.ydh.basice.model;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    //第二步 使用Provider 注解 实例化对象
    @Provides
    A providerA() {
        return new A();
    }

}
