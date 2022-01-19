package com.langit7.hondasalesforce.presenter.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.Util.function.getPreverenceBool
import com.langit7.hondasalesforce.Util.function.getToday
import com.langit7.hondasalesforce.Util.function.getUser
import com.langit7.hondasalesforce.model.history
import com.langit7.hondasalesforce.model.video
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener

class VideoListAdapter(val items: List<video>, val context: Context, val lst: ItemClickListener<video>) : RecyclerView.Adapter<VideoViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(LayoutInflater.from(context).inflate(R.layout.item_video, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val v=items.get(position)
        holder.title?.text=v.title
//        holder.date?.text="Dilihat " +v.viewCounter + " kali | "+ v.createdDate
        holder.date?.text= v.createdDate

        val link : String = v.linkDownload

        holder.unduh.setOnClickListener {
//            println("link : " +link)
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            context.startActivity(i)
        }

        holder.yt.visibility=View.GONE

            (context as BaseActivity).lifecycle.addObserver(holder.yt)
            holder.yt.initialize({ initializedYouTubePlayer ->
                initializedYouTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                    override fun onStateChange(state: PlayerConstants.PlayerState) {
                        super.onStateChange(state)
                        if(state==PlayerConstants.PlayerState.PLAYING){
                            lst.onItemClick(v)
                        }
                        if(state==PlayerConstants.PlayerState.ENDED){


                        var count=function.getPreverence(context,function.getUser(context)!!.id+"videopoint")
                        if(count.length==0){
                            count="1"
                        }

                        if(!getPreverenceBool(context, function.getUser(context)!!.id+getToday()+v.id) ) {
                            function.savePreverence(context,function.getUser(context)!!.id+getToday()+v.id,true)
                            var pp = MainPresenter(context, context.APIServices)

//                            if(count.toInt()<=2) {//loss checking 2times
                                pp.savePoint("2", v.id, object : ObjectResponseInterface<history> {
                                    override fun onSuccess(res: history) {
                                        function.savePreverence(
                                            context,
                                            getUser(context)!!.id + "videopoint",
                                            (count.toInt() + 1).toString()
                                        )
                                        if (res.total_point.toInt() > 0) {
                                            DialogUtil.createScoreDialog(context as Activity, "+" + res.total_point)
                                        }
                                    }

                                    override fun onFailed(msg: String) {

                                    }

                                })
//                            }

                            pp.saveCounter("2", v.id, object : ObjectResponseInterface<history> {
                                override fun onSuccess(res: history) {
                                }
                                override fun onFailed(msg: String) {
                                }
                            })

                        }



                        }
                    }

                    override fun onReady() {
                        holder.yt.visibility=View.VISIBLE
                        var videoId = "6JYIGclVQdw"
//                    videoId = "https://www.youtube.com/watch?v=LEf27xuYcw4"

                        videoId = v.videoUrl
                            .replace("https://www.youtube.com/embed/", "")
                            .replace("http://www.youtube.com/embed/","")
                            .replace("?feature=player_detailpage","")
//                        Log.e("Videoid",videoId)
                        initializedYouTubePlayer.cueVideo(videoId, 0f)

                    }
                })
            }, true)




//        Glide.with(context).load(v.image).into(holder.img)
//        holder.main.setOnClickListener(View.OnClickListener {
//            lst.onItemClick(items.get(position))
//        })



    }
}


class VideoViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val main=view.findViewById<RelativeLayout>(R.id.main)
    val yt = view.findViewById<YouTubePlayerView>(R.id.yt)
    val title=view.findViewById<TextView>(R.id.ttitle)
    val date=view.findViewById<TextView>(R.id.tdate)
    val unduh= view.findViewById<ImageView>(R.id.imgunduh)
}