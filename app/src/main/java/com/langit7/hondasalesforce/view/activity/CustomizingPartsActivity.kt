package com.langit7.hondasalesforce.view.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.request.RequestOptions
import com.github.aakira.expandablelayout.ExpandableRelativeLayout
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.SliderTypes.DefaultSliderView
import com.glide.slider.library.SliderTypes.TextSliderView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.cuspart
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_customizing_parts.*

class CustomizingPartsActivity : BaseActivity() {

    lateinit var cuspart: cuspart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customizing_parts)


        cuspart= intent.getSerializableExtra("pr") as cuspart

        if(cuspart==null)
            finish()


        tactionbartitle.setText(getString(R.string.customizingparts))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tactionbartitle.setTextColor(resources.getColor(R.color.white,null))
        }else{
            tactionbartitle.setTextColor(resources.getColor(R.color.white))
        }
        imgback.setImageResource(R.drawable.arrow_back_white)
        imgback.setOnClickListener({
            onBackPressed()
        })



        var slider=findViewById<SliderLayout>(R.id.slider)
        slider.setPresetTransformer(SliderLayout.Transformer.Default)
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        slider.setDuration(4000)

        val requestOptions = RequestOptions().centerCrop()
        slider.removeAllSliders()
        for (i in 0 until cuspart.customizingPartsImage.size) {
            val sliderView = DefaultSliderView(ctx)
            sliderView
                .image(cuspart.customizingPartsImage.get(i).image)
                .setRequestOption(requestOptions)
                .setProgressBarVisible(true)
                .setOnSliderClickListener {
                    //should be to artist via web or activity
                }

            slider.addSlider(sliderView)

        }


        ttitle.setText(cuspart.title)
        tbrand.setText(cuspart.title)


        lldetailcontainer.removeAllViews()
        for(det in cuspart.customizingPartsDetail){

            var v=layoutInflater.inflate(R.layout.item_expand,null)
            var exlayout=v.findViewById<ExpandableRelativeLayout>(R.id.exlayout)
            var rlheader=v.findViewById<RelativeLayout>(R.id.rlheader)
            var imgheader=v.findViewById<ImageView>(R.id.imgheader)
            var ttitle=v.findViewById<TextView>(R.id.ttitle)
            var tbody=v.findViewById<TextView>(R.id.tbody)

            rlheader.setOnClickListener {
                exlayout.toggle()
                if(exlayout.isExpanded)
                    imgheader.setImageResource(R.drawable.icon_menu_plus)
                else
                    imgheader.setImageResource(R.drawable.icon_menu_minus)
            }

            exlayout.collapse()
            ttitle.setText(det.title)
            tbody.setText(det.body)
            exlayout.isExpanded=false
            lldetailcontainer.addView(v)
        }





    }
}
