package com.langit7.hondasalesforce.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.databinding.ActivityPartisipantDetailBinding
import com.langit7.hondasalesforce.databinding.FragmentSetAuditBinding
import com.langit7.hondasalesforce.model.quiz
import com.langit7.hondasalesforce.presenter.adapter.QuizListAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.presenter.viewInterface.YesNoListener
import com.langit7.hondasalesforce.view.activity.MainActivity
import com.langit7.hondasalesforce.view.activity.QuizActivity
import com.langit7.hondasalesforce.view.activity.nos.ListNosAuditActivity
import com.langit7.hondasalesforce.view.activity.teamreport.PartisipantQuizActivity


class SelfAuditFragment : Fragment(), BaseFragmentInterface {

    override var title: String="Self Audit Nos"
    lateinit var act:MainActivity;
    var lsData=ArrayList<quiz>()
    private lateinit var binding: FragmentSetAuditBinding
    var option = 0;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSetAuditBinding.inflate(inflater,container,false)


        act=activity as MainActivity

        binding.bar.imgback.visibility=View.INVISIBLE
        binding.bar.tactionbartitle       .setText(title)

        intentdetailList()

        return binding.root
    }


    companion object {
//        fun Instantiate(lsdata:List<quiz>): SelfAuditFragment {
//            val sd = SelfAuditFragment()
//            sd.lsData.clear()
//            sd.lsData.addAll(lsdata)
//            return sd
//        }
        fun Instantiate(): SelfAuditFragment {
            val sd = SelfAuditFragment()

            return sd
        }
    }

    fun intentdetailList () {
        binding.ck1.setOnCheckedChangeListener { _, isChecked ->
            Log.d("checkbox", "checked: $isChecked")
            goToPeserta("1", "H1 Premises")
        }

        binding.ck2.setOnCheckedChangeListener { _, isChecked ->
            Log.d("checkbox", "checked: $isChecked")
            goToPeserta("2", "H1 People")
        }

        binding.ck3.setOnCheckedChangeListener { _, isChecked ->
            Log.d("checkbox", "checked: $isChecked")
            goToPeserta("3", "H1 Process")
        }


    }
    fun goToPeserta(index: String, title : String
                    ) {
        var ii= Intent (getActivity(), ListNosAuditActivity::class.java)
        ii.putExtra("index", index)
        ii.putExtra("title", title)

        this.startActivity(ii)
    }



}

