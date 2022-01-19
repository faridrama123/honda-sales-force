package com.langit7.hondasalesforce.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.notif
import com.langit7.hondasalesforce.presenter.adapter.NotifAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.ArticleActivity
import com.langit7.hondasalesforce.view.activity.GeneralKnowledgeActivity
import com.langit7.hondasalesforce.view.activity.MainActivity
import com.langit7.hondasalesforce.view.activity.ProductActivity
import java.util.*


class NotificationFragment : Fragment(), BaseFragmentInterface {

    override var title: String = "Notifikasi"

    lateinit var act: MainActivity;

    var lsNotif= ArrayList<notif>()
    lateinit var adp: NotifAdapter
    lateinit var treadall:TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_notification, container, false)

        act = activity as MainActivity

        val imgback = rootView.findViewById<ImageView>(R.id.imgback)
        val ttile = rootView.findViewById<TextView>(R.id.tactionbartitle)
        treadall = rootView.findViewById<TextView>(R.id.treadall)
        imgback.visibility = View.INVISIBLE
        ttile.setText(title)


        adp = NotifAdapter(act, lsNotif, object : NotifAdapter.SwipeActionListener {
            override fun onDelete(position: Int) {
                act.showLoadingDialog()
                act.presenter.deleteNotif(
                    lsNotif.get(position).id,
                    object : ObjectResponseInterface<baseresponse<notif>> {
                        override fun onSuccess(res: baseresponse<notif>) {
                            act.dismisLoadingDialog()
                            lsNotif.removeAt(position)
                        }

                        override fun onFailed(msg: String) {

                            act.presenter.getNotif(object : DataListInterface<notif> {
                                override fun onGetDataSuccess(res: List<notif>) {
                                    act.dismisLoadingDialog()
                                    lsNotif.clear()
                                    lsNotif.addAll(res)

                                    adp.notifyDataSetChanged()
                                }

                                override fun onGetDataFailed(msg: String) {
                                    act.dismisLoadingDialog()

                                }

                            })
                        }

                    })
            }

        })

        var lv = rootView.findViewById<ListView>(R.id.listView)
        lv.adapter = adp
        adp.notifyDataSetChanged()

        lv.setOnItemClickListener { t1, t2, pos, t4 ->

            if (!lsNotif.get(pos).isRead.equals("1")) {
                act.showLoadingDialog()
                act.presenter.readNotif(lsNotif.get(pos).id, object : ObjectResponseInterface<notif> {
                    override fun onSuccess(res: notif) {
                        act.dismisLoadingDialog()
                        lsNotif.get(pos).isRead = "1"
                        adp.notifyDataSetChanged()
                        act.permakTabs()
                    }

                    override fun onFailed(msg: String) {
                        act.dismisLoadingDialog()
                    }
                })
            }

            if (lsNotif.get(pos).actiontype.equals("1")) {

            } else if (lsNotif.get(pos).actiontype.equals("2")) {
                val ii = Intent(act, MainActivity::class.java)
                ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                ii.putExtra("tid", 2)
                startActivity(ii)
            } else if (lsNotif.get(pos).actiontype.equals("3")) {
                val ii = Intent(act, MainActivity::class.java)
                ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                ii.putExtra("tid", 1)
                startActivity(ii)
            } else if (lsNotif.get(pos).actiontype.equals("4")) {
                val ii = Intent(act, ProductActivity::class.java)
                ii.putExtra("tid", 0)
                startActivity(ii)
            } else if (lsNotif.get(pos).actiontype.equals("5")) {
                val ii = Intent(act, ArticleActivity::class.java)
                ii.putExtra("tid", 0)
                startActivity(ii)
            } else if (lsNotif.get(pos).actiontype.equals("6")) {
                val ii = Intent(act, GeneralKnowledgeActivity::class.java)
                ii.putExtra("tid", 1)
                startActivity(ii)
            } else if (lsNotif.get(pos).actiontype.equals("7")) {
                val ii = Intent(act, GeneralKnowledgeActivity::class.java)
                ii.putExtra("tid", 2)
                startActivity(ii)
            } else if (lsNotif.get(pos).actiontype.equals("8")) {
                val ii = Intent(act, GeneralKnowledgeActivity::class.java)
                ii.putExtra("tid", 3)
                startActivity(ii)
            }

        }

        treadall.setOnClickListener {


            if(getunread()>0) {
                act.showLoadingDialog()
                act.presenter.readAllNotif(object : ObjectResponseInterface<baseresponse<String>> {
                    override fun onSuccess(res: baseresponse<String>) {
                        act.dismisLoadingDialog()
                        for (i in 0..lsNotif.size - 1) {
                            lsNotif.get(i).isRead = "1"
                        }
                        adp.notifyDataSetChanged()
                        act.permakTabs()
                        act.Toast(res.message)
                        renamebutton()
                    }

                    override fun onFailed(msg: String) {
//                        Log.e("err",msg)
                        act.dismisLoadingDialog()
                    }
                })
            }



        }
        renamebutton()

        return rootView
    }

    fun renamebutton(){

        treadall.setText(getString(R.string.markread)+"("+getunread().toString()+")")

    }
    fun getunread():Int{
        var size=0;

        for(i in lsNotif){
            if(!i.isRead.equals("1"))
                size++
        }
        return size
    }

    companion object {
        fun Instantiate(ls: ArrayList<notif>): NotificationFragment {
            val sd = NotificationFragment()
            sd.lsNotif = ls
            return sd
        }
    }

}

