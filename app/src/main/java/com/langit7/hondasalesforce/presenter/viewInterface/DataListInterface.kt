package com.langit7.hondasalesforce.presenter.viewInterface

import com.langit7.hondasalesforce.model.article
import com.langit7.hondasalesforce.model.user

interface DataListInterface<T>{

    fun onGetDataSuccess(res : List<T>)
    fun onGetDataFailed(msg:String)


}