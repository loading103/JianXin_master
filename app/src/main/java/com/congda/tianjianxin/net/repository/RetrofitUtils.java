package com.congda.tianjianxin.net.repository;

import com.congda.baselibrary.net.BaseRetrofit;
import com.congda.tianjianxin.net.api.ApiService;

public class RetrofitUtils extends BaseRetrofit {
    private static ApiService httpService;

    /**
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public static ApiService getHttpService() {
        if (httpService == null) {
            httpService = getRetrofit().create(ApiService.class);
        }
        return httpService;
    }


}
