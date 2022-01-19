package com.langit7.hondasalesforce.presenter.viewInterface

import com.langit7.hondasalesforce.model.user

interface ObjectResponseInterface<T>{

    fun onSuccess(res : T)
    fun onFailed(msg:String)


}