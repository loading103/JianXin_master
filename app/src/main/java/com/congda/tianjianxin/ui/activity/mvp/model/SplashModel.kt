package com.congda.tianjianxin.ui.activity.mvp.model

import CommonBean
import SplashAdBean
import VersonBeanData
import com.congda.baselibrary.mvp.BaseModel
import com.congda.baselibrary.net.BaseHttpResult
import com.congda.tianjianxin.net.repository.RetrofitUtils
import com.congda.tianjianxin.ui.activity.mvp.contract.SplashContract
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.RequestBody

class SplashModel : BaseModel(), SplashContract.Model{


    override fun getGetAdJson(): Observable<BaseHttpResult<List<SplashAdBean>>> {
        return RetrofitUtils.getHttpService().httpGetAdJson()
    }

    override fun CheckedVersion(systemType: String):Observable<BaseHttpResult<VersonBeanData>> {
        val commonBean = CommonBean()
        commonBean.systemType=systemType;
        val toJson = Gson().toJson(commonBean)
        val body: RequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), toJson)
        return  RetrofitUtils.getHttpService().getVersonBean(body)
    }

}