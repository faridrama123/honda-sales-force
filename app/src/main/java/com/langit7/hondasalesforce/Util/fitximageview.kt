package com.langit7.hondasalesforce.Util

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

/**
 * Created by Donny Adiwilaga on 6/30/2016.
 */

class fitxImageView : ImageView {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        try {
            val width = View.MeasureSpec.getSize(widthMeasureSpec)
            val height = width * drawable.intrinsicHeight / drawable.intrinsicWidth
            setMeasuredDimension(width, height)
        } catch (e: Exception) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            //e.printStackTrace();
        }

    }
}