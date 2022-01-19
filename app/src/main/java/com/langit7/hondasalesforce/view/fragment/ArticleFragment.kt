package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.article
import com.langit7.hondasalesforce.presenter.adapter.ArticleListAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.ArticleActivity
import com.langit7.hondasalesforce.view.activity.ArticleDetailActivity


class ArticleFragment : Fragment(), BaseFragmentInterface {

    override var title: String=""

    lateinit var act:ArticleActivity;
    var lsArticle=ArrayList<article>()

    lateinit var adp:ArticleListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_article, container, false)

        act=activity as ArticleActivity

        val rv=rootView.findViewById<RecyclerView>(R.id.rv)
        val tn=rootView.findViewById<TextView>(R.id.tnodate)

        // Creates a vertical Layout Manager
        rv.layoutManager = LinearLayoutManager(act)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

        adp= ArticleListAdapter(
            lsArticle,
            act,
            object : ItemClickListener<article> {
                override fun onItemClick(art: article) {
                    gotoArticle(art)

                }
            }
        )
        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = adp;

        if(lsArticle.size>0)
            tn.visibility=View.GONE
        else
            tn.visibility=View.VISIBLE

        return rootView
    }

    fun gotoArticle(art:article){
        if(art.is_read.equals("1",true)) {
            var ii = Intent(act, ArticleDetailActivity::class.java)
            ii.putExtra("art", art)
            startActivity(ii)
        }else{
            act.showLoadingDialog()
            act.presenter.readArticle(art.id, object :ObjectResponseInterface<article>{
                override fun onSuccess(res: article) {
                    act.dismisLoadingDialog()
                    art.is_read="1"
                    adp.notifyDataSetChanged()
                    var ii = Intent(act, ArticleDetailActivity::class.java)
                    ii.putExtra("art", art)
                    startActivity(ii)
                }

                override fun onFailed(msg: String) {
                    act.dismisLoadingDialog()
                }


            })
        }
    }

    companion object {
        fun Instantiate(tit:String,lsartc:List<article>): ArticleFragment {
            val sd = ArticleFragment()
            sd.title=tit
            sd.lsArticle.clear()
            sd.lsArticle.addAll(lsartc)
            return sd
        }
    }

}

