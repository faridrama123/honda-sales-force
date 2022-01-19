package com.langit7.hondasalesforce.view.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.apparel
import com.langit7.hondasalesforce.model.apparelcategory
import com.langit7.hondasalesforce.presenter.adapter.ApparelGridAdapter
import com.langit7.hondasalesforce.presenter.logic.ApparelPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_apparelby_cat.*
import kotlinx.android.synthetic.main.activity_product_datail.*

class ApparelbyCatActivity : BaseActivity() {

    lateinit var presenter: ApparelPresenter
    lateinit var lsData: ArrayList<apparel>

    lateinit var cat: apparelcategory

    lateinit var adp : ApparelGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apparelby_cat)

        cat = intent.getSerializableExtra("apr") as apparelcategory

        if (cat == null)
            finish()

        tactionbartitle.setText(getString(R.string.apparel))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tactionbartitle.setTextColor(resources.getColor(R.color.white, null))
        } else {
            tactionbartitle.setTextColor(resources.getColor(R.color.white))
        }
        imgback.setImageResource(R.drawable.arrow_back_white)
        imgback.setOnClickListener({
            onBackPressed()
        })


        ttitle_.setText(cat.title)

        lsData = ArrayList()



        presenter = ApparelPresenter(this, APIServices)

        showLoadingDialog()
        presenter.getApparelbyCat(cat.id.toString(), object : DataListInterface<apparel> {
            override fun onGetDataSuccess(res: List<apparel>) {
                dismisLoadingDialog()
                lsData.clear()
                lsData.addAll(res)
                adp.notifyDataSetChanged()

            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }


        })

         adp = ApparelGridAdapter(ctx,
            lsData,
            object : ItemClickListener<apparel> {
                override fun onItemClick(res: apparel) {
                    var ii = Intent(ctx, ApparelDetailActivity::class.java)
                    ii.putExtra("apr", res)
                    startActivity(ii)
                }

            }
        )
        gridview.adapter = adp

    }
}
