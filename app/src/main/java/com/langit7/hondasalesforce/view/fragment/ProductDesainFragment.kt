package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import java.util.ArrayList
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.widget.*
import com.bumptech.glide.request.RequestOptions
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.SliderTypes.TextSliderView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.TextSlider
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.view.activity.*


class ProductDesainFragment : Fragment(), BaseFragmentInterface {

    override var title: String="Desain"

    lateinit var act:ProductDetailActivity;

    var prod:product? =null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_desain, container, false)

        act=activity as ProductDetailActivity

        var slider=rootView.findViewById<SliderLayout>(R.id.slider)
        slider.setPresetTransformer(SliderLayout.Transformer.Default)
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        slider.setBackgroundColor(resources.getColor(R.color.white))
        slider.setDuration(4000)


        var ttitle=rootView.findViewById<TextView>(R.id.ttitle)
        var tdesc=rootView.findViewById<TextView>(R.id.tdesc)


        val requestOptions = RequestOptions().centerCrop()
        slider.removeAllSliders()
        if(prod !=null) {
            for (i in 0 until prod!!.variantProduct.size) {
                val sliderView = TextSlider(act)
                sliderView
                    .image(prod!!.variantProduct.get(i).image)

                    .setRequestOption(requestOptions)
                    .setBackgroundColor(Color.TRANSPARENT)
                    .setProgressBarVisible(true)
                    .description(prod!!.variantProduct.get(i).title+"\n")
                    .setOnSliderClickListener {
                        //should be to artist via web or activity
                    }

                slider.addSlider(sliderView)

            }


            ttitle.setText(prod!!.title)
            tdesc.setText(Html.fromHtml(prod!!.description))
        }

        return rootView
    }


    companion object {
        fun Instantiate(product:product): ProductDesainFragment {
            val sd = ProductDesainFragment()
            sd.prod=product
            return sd
        }
    }

}

