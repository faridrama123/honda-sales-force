package com.langit7.hondasalesforce.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.apparelcategory
import com.langit7.hondasalesforce.presenter.adapter.AdapterAparelCat
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.view.activity.ApparelbyCatActivity
import com.langit7.hondasalesforce.view.activity.ProductDetailActivity
import com.langit7.hondasalesforce.view.activity.ScanActivity

class Aparel_CategoryFragment : Fragment(), BaseFragmentInterface {

    override var title: String = ""

    lateinit var act: ProductDetailActivity
    lateinit var lsData_: List<apparelcategory>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_aparel__category, container, false)

        act = activity as ProductDetailActivity

        val scan = rootView.findViewById<ImageView>(R.id.imgscan)
        scan.setOnClickListener {
            var ii = Intent(act, ScanActivity::class.java)
            startActivityForResult(ii, 1)
        }

        val rv = rootView.findViewById<GridView>(R.id.gridview)

        var adp = AdapterAparelCat(act,
            lsData_,
            object : ItemClickListener<apparelcategory> {
                override fun onItemClick(res: apparelcategory) {
                    var ii = Intent(act, ApparelbyCatActivity::class.java)
                    ii.putExtra("apr", res)
                    startActivity(ii)

                }

            }
        )
        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = adp;

        return rootView
    }


    companion object {
        fun Instantiate(tit: String, lsData: List<apparelcategory>): Aparel_CategoryFragment {
            val sd = Aparel_CategoryFragment()
            sd.title = tit
            sd.lsData_ = lsData
            return sd
        }
    }
}




