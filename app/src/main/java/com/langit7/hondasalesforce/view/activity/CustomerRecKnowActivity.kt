package com.langit7.hondasalesforce.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.presenter.adapter.ProductGridAdapter
import com.langit7.hondasalesforce.presenter.logic.ProductPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import kotlinx.android.synthetic.main.activity_customer_recknow.*



class CustomerRecKnowActivity : BaseActivity() {


    var gen=""
    var age=""
    var ikn="1"

    lateinit var adp: ProductGridAdapter
    var lsData=ArrayList<product>()
    var lsData_=ArrayList<product>()

    lateinit var presenter: ProductPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_recknow)

        presenter= ProductPresenter(this,APIServices)


        tclose.setOnClickListener {
            finish()
        }

        tpria.setOnClickListener {
            gen="1"
            tpria.setBackgroundResource(R.color.reds)
            tpria.setTextColor(ctx.resources.getColor(R.color.white))
            twanita.setBackgroundResource(R.drawable.border_red)
            twanita.setTextColor(ctx.resources.getColor(R.color.black))

        }
        twanita.setOnClickListener {
            gen="2"
            twanita.setBackgroundResource(R.color.reds)
            twanita.setTextColor(ctx.resources.getColor(R.color.white))
            tpria.setBackgroundResource(R.drawable.border_red)
            tpria.setTextColor(ctx.resources.getColor(R.color.black))
        }

        tremaja.setOnClickListener {
            age="1"
            tremaja.setBackgroundResource(R.color.reds)
            tremaja.setTextColor(ctx.resources.getColor(R.color.white))
            tremajamuda.setBackgroundResource(R.drawable.border_red)
            tremajamuda.setTextColor(ctx.resources.getColor(R.color.black))
            tdewasa.setBackgroundResource(R.drawable.border_red)
            tdewasa.setTextColor(ctx.resources.getColor(R.color.black))

        }
        tremajamuda.setOnClickListener {
            age="2"
            tremajamuda.setBackgroundResource(R.color.reds)
            tremajamuda.setTextColor(ctx.resources.getColor(R.color.white))
            tremaja.setBackgroundResource(R.drawable.border_red)
            tremaja.setTextColor(ctx.resources.getColor(R.color.black))
            tdewasa.setBackgroundResource(R.drawable.border_red)
            tdewasa.setTextColor(ctx.resources.getColor(R.color.black))

        }
        tdewasa.setOnClickListener {
            age="3"
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


        }
        ttidak.setOnClickListener {

            ttidak.setBackgroundResource(R.color.reds)
            ttidak.setTextColor(ctx.resources.getColor(R.color.white))
            tya.setBackgroundResource(R.drawable.border_red)
            tya.setTextColor(ctx.resources.getColor(R.color.black))

            var ii= Intent(ctx, CustomerRecNotKnowActivity::class.java)
            ii.putExtra("gen",gen)
            ii.putExtra("age",age)
            ii.putExtra("tid",2)
            startActivity(ii)
            finish()
        }




        gen=intent.getStringExtra("gen")?:""
        if(gen.isNotEmpty() && gen.equals("2"))
            twanita.post { twanita.performClick() }
        else
            tpria.post{tpria.performClick()}


        age=intent.getStringExtra("age")?:""
        if(age.isNotEmpty() && age.equals("2"))
            tremajamuda.post { tremajamuda.performClick() }
        else if(age.isNotEmpty() && age.equals("3"))
            tdewasa.post { tdewasa.performClick() }
        else
            tremaja.post { tremaja.performClick() }


        tya.post { tya.performClick() }








        adp= ProductGridAdapter(ctx,
            lsData_,
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
        getProduct()



        etsearch.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

                if(etsearch.text.toString().isNotEmpty()){
                    lsData_.clear()
                    for (p in lsData){
                        var key=etsearch.text.toString().toLowerCase()
                        if(p.title.toLowerCase().contains(key) || p.description.toLowerCase().contains(key)|| p.category.name.toLowerCase().contains(key)){
                            lsData_.add(p)
                        }
                    }
                    adp.notifyDataSetChanged()
                }


            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }


        })
    }




    fun getProduct(){


            showLoadingDialog()
            presenter.getProductbyCat("",object:
                DataListInterface<product> {
                override fun onGetDataSuccess(res: List<product>) {
                    dismisLoadingDialog()

                    lsData.clear()
                    lsData.addAll(res)
                    lsData_.clear()
                    lsData_.addAll(res)

                    adp.notifyDataSetChanged()
                }

                override fun onGetDataFailed(msg: String) {
                    dismisLoadingDialog()
                }

            })




    }
}
