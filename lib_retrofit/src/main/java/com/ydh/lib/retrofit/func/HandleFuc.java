/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ydh.lib.retrofit.func;

import com.ydh.lib.retrofit.exception.ServerException;
import com.ydh.lib.retrofit.model.ApiResult;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


/**
 * <p>描述：ApiResult<T>转换T</p>
 * 作者： zhouyou<br>
 * 日期： 2017/5/15 16:54 <br>
 * 版本： v1.0<br>
 * @author 13001
 */
public class HandleFuc<T> implements Function<ApiResult<T>, T> {
    @Override
    public T apply(@NonNull ApiResult<T> tApiResult) {
        if (isOk(tApiResult)) {
            return tApiResult.getData();
        } else {
            throw new ServerException(tApiResult.getCode(), tApiResult.getMsg());
        }
    }

    /**
     * @param tApiResult 接口返回
     * @return 是否为正常返回
     */
    private boolean isOk(ApiResult<T> tApiResult) {
        if (tApiResult==null){
            return false;
        }
        return tApiResult.isOk();
    }
}
