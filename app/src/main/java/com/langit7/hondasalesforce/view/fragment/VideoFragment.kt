package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.*
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.history
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.model.video
import com.langit7.hondasalesforce.presenter.adapter.VideoListAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.*


class VideoFragment : Fragment(), BaseFragmentInterface {

    override var title: String=""

    lateinit var act: VideoActivity;
    var lsData=ArrayList<video>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_video, container, false)

        act=activity as VideoActivity

        val rv=rootView.findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(act)

        var adp= VideoListAdapter(lsData,
            act,
            object : ItemClickListener<video> {
                override fun onItemClick(res: video) {

//                    act.mainpresenter.savePoint("2",res.id,object:ObjectResponseInterface<history>{
//                        override fun onSuccess(res: history) {
//                        }
//
//                        override fun onFailed(msg: String) {
//                        }
//
//                    })
                }

            }
        )
        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = adp;

        return rootView
    }


    companion object {
        fun Instantiate(tit:String,lsdata:List<video>): VideoFragment {
            val sd = VideoFragment()
            sd.title=tit
            sd.lsData.clear()
            sd.lsData.addAll(lsdata)
            return sd
        }
    }

}

