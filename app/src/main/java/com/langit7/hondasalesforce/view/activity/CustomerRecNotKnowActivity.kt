package com.langit7.hondasalesforce.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.presenter.adapter.ProductGridAdapter
import com.langit7.hondasalesforce.presenter.logic.ProductPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import kotlinx.android.synthetic.main.activity_customer_recnotknow.*

class CustomerRecNotKnowActivity : BaseActivity() {


    var gen=""
    var age=""
    var ikn="2"

    lateinit var adp:ProductGridAdapter
    var lsData=ArrayList<product>()

    lateinit var presenter:ProductPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_recnotknow)



        presenter= ProductPresenter(this,APIServices)

        tclose.setOnClickListener {
            finish()
        }

        tpria.setOnClickListener {


            if(!gen.equals("1")) {
                gen="1"
                getRecomendation()
            }

            tpria.setBackgroundResource(R.color.reds)
            tpria.setTextColor(ctx.resources.getColor(R.color.white))
            twanita.setBackgroundResource(R.drawable.border_red)
            twanita.setTextColor(ctx.resources.getColor(R.color.black))

        }
        twanita.setOnClickListener {
            if(!gen.equals("2")) {
                gen="2"
                getRecomendation()
            }

            twanita.setBackgroundResource(R.color.reds)
            twanita.setTextColor(ctx.resources.getColor(R.color.white))
            tpria.setBackgroundResource(R.drawable.border_red)
            tpria.setTextColor(ctx.resources.getColor(R.color.black))
        }

        tremaja.setOnClickListener {
            if(!age.equals("1")) {
                age="1"
                getRecomendation()
            }
            tremaja.setBackgroundResource(R.color.reds)
            tremaja.setTextColor(ctx.resources.getColor(R.color.white))
            tremajamuda.setBackgroundResource(R.drawable.border_red)
            tremajamuda.setTextColor(ctx.resources.getColor(R.color.black))
            tdewasa.setBackgroundResource(R.drawable.border_red)
            tdewasa.setTextColor(ctx.resources.getColor(R.color.black))

        }
        tremajamuda.setOnClickListener {
            if(!age.equals("2")) {
                age = "2"
                getRecomendation()

            }
            tremajamuda.setBackgroundResource(R.color.reds)
            tremajamuda.setTextColor(ctx.resources.getColor(R.color.white))
            tremaja.setBackgroundResource(R.drawable.border_red)
            tremaja.setTextColor(ctx.resources.getColor(R.color.black))
            tdewasa.setBackgroundResource(R.drawable.border_red)
            tdewasa.setTextColor(ctx.resources.getColor(R.color.black))

        }
        tdewasa.setOnClickListener {
            if(!age.equals("3")){
                age="3"
                getRecomendation()
            }

            tdewasa.setBackgroundResource(R.color.reds)
            tdewasa.setTextColor(ctx.resources.getColor(R.color.white))
            tremaja.setBackgroundResource(R.drawable.border_red)
            tremaja.setTextColor(ctx.resources.getColor(R.color.black))
            tremajamuda.setBackgroundResource(R.drawable.border_red)
            tremajamuda.setTextColor(ctx.resources.getColor(R.color.black))

        }


        tya.setOnClickListener {

            tya.setBackgroundResource(R.color.reds)
            tya.setTextColor(ctx.resources.getColor(R.color.white))
            ttidak.setBackgroundResource(R.drawable.border_red)
            ttidak.setTextColor(ctx.resources.getColor(R.color.black))

            var ii= Intent(ctx, CustomerRecKnowActivity::class.java)
            ii.putExtra("gen",gen)
            ii.putExtra("age",age)
            startActivity(ii)
            finish()
        }
        ttidak.setOnClickListener {

            ttidak.setBackgroundResource(R.color.reds)
            ttidak.setTextColor(ctx.resources.getColor(R.color.white))
            tya.setBackgroundResource(R.drawable.border_red)
            tya.setTextColor(ctx.resources.getColor(R.color.black))

        }



        gen=intent.getStringExtra("gen")?:""
        if(gen.isNotEmpty() && gen.equals("2")) {
            gen="2"
            twanita.post { twanita.performClick() }
        }else {
            gen="1"
            tpria.post { tpria.performClick() }
        }

        age=intent.getStringExtra("age")?:""
        if(age.isNotEmpty() && age.equals("2")) {
            age="2"
            tremajamuda.performClick()
        }else if(age.isNotEmpty() && age.equals("3")) {
            age="3"
            tdewasa.performClick()
        }else {
            age="1"
            tremaja.performClick()
        }

        ttidak.post { ttidak.performClick() }





        adp= ProductGridAdapter(ctx,
            lsData,
            object : ItemClickListener<product> {
                override fun onItemClick(res: product) {
                    var ii= Intent(ctx, ProductDetailActivity::class.java)
                    ii.putExtra("pr",res)
                    ii.putExtra("gen",gen)
                    ii.putExtra("age",age)
                    ii.putExtra("tid",2)

                    startActivity(ii)
                }

            }
        )
        // Access the RecyclerView Adapter and load the data into it
        gridview.adapter = adp;



        getRecomendation()

    }



    fun getRecomendation(){

        showLoadingDialog()
        presenter.getProductRecomendation(gen,age,ikn,object:DataListInterface<product>{
            override fun onGetDataSuccess(res: List<product>) {
                dismisLoadingDialog()

                lsData.clear()
                lsData.addAll(res)
//                Log.e("datarec", lsData.size.toString())

                adp.notifyDataSetChanged()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }

        })


    }
}
