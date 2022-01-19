package com.langit7.hondasalesforce.presenter.viewInterface

import com.langit7.hondasalesforce.model.cdb

interface CDBClickListener{
    fun onItemClick(res : cdb)
    fun onCallClick(res : cdb)
}