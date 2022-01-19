package com.langit7.hondasalesforce.Util

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.tabs.TabLayout

class CustomTabLayout : TabLayout {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

     override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        try {
            if (getTabCount() === 0)
                return
            val field = TabLayout::class.java.getDeclaredField("mTabMinWidth")
            field.setAccessible(true)
            field.set(this, (getMeasuredWidth() / getTabCount().toFloat()).toInt())
        } catch (e: Exception) {
            //e.printStackTrace();
        }

    }
}