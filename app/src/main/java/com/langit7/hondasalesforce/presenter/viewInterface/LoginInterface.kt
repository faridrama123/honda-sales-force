package com.langit7.hondasalesforce.presenter.viewInterface

import com.langit7.hondasalesforce.model.user

interface LoginInterface{

    fun onLoginSuccess(res : user)
    fun onLoginFailed(msg:String)


}