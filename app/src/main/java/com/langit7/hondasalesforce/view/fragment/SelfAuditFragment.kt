package com.langit7.hondasalesforce.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.model.quiz
import com.langit7.hondasalesforce.presenter.adapter.QuizListAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.presenter.viewInterface.YesNoListener
import com.langit7.hondasalesforce.view.activity.MainActivity
import com.langit7.hondasalesforce.view.activity.QuizActivity


class SelfAuditFragment : Fragment(), BaseFragmentInterface {

    override var title: String="Self Audit Nos"
    lateinit var act:MainActivity;
    var lsData=ArrayList<quiz>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_set_audit, container, false)

        act=activity as MainActivity

        val imgback=rootView.findViewById<ImageView>(R.id.imgback)
        val ttile=rootView.findViewById<TextView>(R.id.tactionbartitle)
        imgback.visibility=View.INVISIBLE
        ttile.setText(title)




        val rv=rootView.findViewById<RecyclerView>(R.id.rv)

        // Creates a vertical Layout Manager
        rv.layoutManager = LinearLayoutManager(act)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

        if (lsData==null)
            lsData=ArrayList()


        var adp= QuizListAdapter(
            lsData,
            act,
            object : ItemClickListener<quiz> {
                override fun onItemClick(q: quiz) {

                    var menit= q.duration/60 as Int
                    var detik=q.duration % 60

                    var time=""

                    if(menit>0)
                        time=menit.toString() + " Menit "

                    if(detik>0)
                        time=time + detik.toString() + " Detik "

                    DialogUtil.createYesNoDialog(act,"kuis ini akan memakan waktu sekitar " + time + ", dan tidak dapat dipause, apakah anda akan mengambil kuis ini sekarang?",object :YesNoListener{
                        override fun onYes() {
                            var ii= Intent(act, QuizActivity::class.java)
                            ii.putExtra("q",q)
                            startActivity(ii)

                        }

                        override fun onNo() {

                        }

                    })


                }
            }
        )
        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = adp;


        return rootView
    }


    companion object {
        fun Instantiate(lsdata:List<quiz>): SelfAuditFragment {
            val sd = SelfAuditFragment()
            sd.lsData.clear()
            sd.lsData.addAll(lsdata)
            return sd
        }
    }

}

