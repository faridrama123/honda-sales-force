package com.langit7.hondasalesforce.presenter.viewInterface

import com.langit7.hondasalesforce.model.slider
import com.langit7.hondasalesforce.model.user

interface SliderInterface{

    fun onGetSliderSuccess(res : List<slider>)
    fun onGetSliderFailed(msg:String)


}