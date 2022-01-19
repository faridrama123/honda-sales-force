package com.langit7.hondasalesforce.presenter.viewInterface

import com.langit7.hondasalesforce.model.user

interface RegisterInterface{

    fun onRegisterSuccess(res : user)
    fun onRegisterFailed(msg:String)


}