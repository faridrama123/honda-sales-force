package com.langit7.hondasalesforce.Util



import android.R.attr.description
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.appcompat.widget.AppCompatImageView
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.glide.slider.library.SliderTypes.BaseSliderView
import com.langit7.hondasalesforce.R


class TextSlider(context: Context) : BaseSliderView(context) {

    override fun getView(): View {
        val v = LayoutInflater.from(context).inflate(R.layout.textslider, null)
        val target = v.findViewById(R.id.daimajia_slider_image) as AppCompatImageView
        val description = v.findViewById(R.id.description) as TextView

        description.text = getDescription()
        bindEventAndShow(v, target)
        return v
    }
}