package com.langit7.hondasalesforce.presenter.viewInterface

import com.langit7.hondasalesforce.model.cdb

interface CDBDialogListener{
    fun onUpdate(date : String,time:String,status:String)
    fun ondeal(id : String)
    fun onnodeal(id : String)
}