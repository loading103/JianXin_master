package com.congda.tianjianxin.ui.fragment.mvp.model

import com.congda.baselibrary.mvp.BaseModel
import com.congda.baselibrary.net.TypeOneBaseHttpResult
import com.congda.tianjianxin.bean.BannerBean
import com.congda.tianjianxin.net.repository.RetrofitUtils
import com.congda.tianjianxin.ui.fragment.mvp.contract.ListFirstContract
import com.congda.tianjianxin.ui.fragment.mvp.contract.SecondContract
import io.reactivex.Observable

class ListFirstModel : BaseModel(), ListFirstContract.Model{

    override fun getBannList(): Observable<TypeOneBaseHttpResult<MutableList<BannerBean>>> {
        return RetrofitUtils.getHttpService().getBanner()
    }

}