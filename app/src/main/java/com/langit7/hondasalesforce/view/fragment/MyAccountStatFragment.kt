package com.langit7.hondasalesforce.view.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.user
import com.langit7.hondasalesforce.presenter.adapter.WrappingFragmentAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.MainActivity
import com.langit7.hondasalesforce.view.activity.VaccineActivity
import kotlinx.android.synthetic.main.fragment_myaccount_stat.*
import libs.mjn.prettydialog.PrettyDialog
import java.io.File
import java.io.FileInputStream


class MyAccountStatFragment : Fragment(), BaseFragmentInterface {

    override var title: String = "Akun Saya"
    var iskacab=false

    lateinit var act: MainActivity


    lateinit var mSectionsPagerAdapter: WrappingFragmentAdapter

    var rv : View? =null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_myaccount_stat, container, false)
        rv=rootView
        act = activity as MainActivity

//        var imgavatar= rootView.findViewById<ImageView>(R.id.imgavatar)
//        imgavatar.setOnClickListener {
//            var ii= Intent(context,ChangeAvatarActivity::class.java)
//            context!!.startActivity(ii)
//        }
//
//
//        var imgleaderboard= rootView.findViewById<ImageView>(R.id.imgleaderboard)
//        imgleaderboard.setOnClickListener {
//            var ii= Intent(context,LeaderboardActivity::class.java)
//            context!!.startActivity(ii)
//        }

//        var imglogout=rootView.findViewById<ImageView>(R.id.imglogout)
//        imglogout.setOnClickListener {
//            function.doLogout(act)
//            var ii=Intent(act,LoginActivity::class.java)
//            function.savePreverence(context!!, "hasaddialog", false)
//            act.startActivity(ii)
//            act.finish()
//        }


//        var tname=rootView.findViewById<TextView>(R.id.tname)
//        var tid=rootView.findViewById<TextView>(R.id.tid)
//        var tposition=rootView.findViewById<TextView>(R.id.tposition)
//        var tpoint=rootView.findViewById<TextView>(R.id.tpoint)
//        var treadsalestalk=rootView.findViewById<TextView>(R.id.treadsalestalk)
//        var twatchvideo=rootView.findViewById<TextView>(R.id.twatchvideo)
//        var tinputdata=rootView.findViewById<TextView>(R.id.tinputdata)
//        var ttotalassignment=rootView.findViewById<TextView>(R.id.ttotalassignment)
//        var tpass=rootView.findViewById<TextView>(R.id.tpass)
//        var tremed=rootView.findViewById<TextView>(R.id.tremed)


        val tredeem = rootView.findViewById<TextView>(R.id.tredeem)
        val tvupdatevaccine = rootView.findViewById<TextView>(R.id.tvupdatevaccine)
        val tveditprofileimage = rootView.findViewById<TextView>(R.id.tveditprofileimage)

        tredeem.visibility = View.INVISIBLE
        tredeem.setOnClickListener {

            DialogUtil.createYesDialog(act, "Untuk sementara redeem belum bisa digunakan", "OK")

//            var ii= Intent(context,RedeemActivity::class.java)
//            startActivity(ii)

        }



            try {
                val usr = function.getUser(act)

                renderUser(usr!!, rootView)

                tveditprofileimage.setOnClickListener {
                    var pDialog = PrettyDialog(requireActivity())
                    pDialog.setTitle("Profile Photo")
                        .setIcon(R.drawable.icon_addphoto, R.color.reds, null)
                        .setMessage("Choose Profile Photo")
                        .addButton("Kamera", R.color.white, R.color.colorPrimary) {
                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            resultLauncherCamera.launch(intent)
                            pDialog.dismiss()
                        }
                        .addButton("Galery", R.color.white, R.color.orange) {
                            val intent = Intent()
                            intent.type = "image/*"
                            intent.action = Intent.ACTION_GET_CONTENT
                            resultLauncherGallery.launch(Intent.createChooser(intent, "Pilih Foto"))
                            pDialog.dismiss()
                        }
                        .show()
                }

                tvupdatevaccine.setOnClickListener {


                    resultUpdateVaccine.launch(Intent(context, VaccineActivity::class.java))
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }




        return rootView
    }

    val resultUpdateVaccine =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                val usr = data?.getSerializableExtra("user") as user
                    rv?.let {
                        renderUser(usr, it)
                    }
            }
        }
    var resultLauncherGallery =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                var image: File? = null
                image = function.getBitmapFromGalleryResult(act, data)
//                Log.e("path", ">" + image + "<")

                if (image!!.length() / 1042 > 2000) {
                    act.Toast("Image too large, Please choose diferent image")
                    image = null
                }
                act.showLoadingDialog()
                updateImage(image)
            }
        }
    var resultLauncherCamera =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                var image: File? = null
                image = function.getBitmapFromCaptureImageResult(act, data!!)
                if (image!!.length() / 1042 > 2000) {
                    act.Toast("Image too large, Please choose diferent image")
                    image = null
                }
                act.showLoadingDialog()
                updateImage(image)
            }
        }

    fun renderUser(usr: user, rootView: View) {


        val llgridmenu = rootView.findViewById<LinearLayout>(R.id.llgridmenu)
        val tvstatusvaccine1 = rootView.findViewById<TextView>(R.id.tvstatusvaccine1)
        val tvstatusvaccine2 = rootView.findViewById<TextView>(R.id.tvstatusvaccine2)
        val imgprofile = rootView.findViewById<ImageView>(R.id.imgprofile)

        if(!iskacab) {
            val treadproduct = rootView.findViewById<TextView>(R.id.treadproduct)
            val treadknowledge = rootView.findViewById<TextView>(R.id.treadknowledge)
            val twatchvideo = rootView.findViewById<TextView>(R.id.twatchvideo)
            val treadartc = rootView.findViewById<TextView>(R.id.treadartc)
            val tadddata = rootView.findViewById<TextView>(R.id.tadddata)
            val ttakequis = rootView.findViewById<TextView>(R.id.ttakequis)
            val ttakesurvey = rootView.findViewById<TextView>(R.id.ttakesurvey)


            llgridmenu.visibility = View.VISIBLE
            treadproduct.text = usr.total_read_product
            treadknowledge.text = usr.total_read_general_knowledge
            twatchvideo.text = usr.totalWatchVideo
            treadartc.text = usr.total_read_article
            tadddata.text = usr.totalInsertData
            ttakequis.text = usr.total_quiz
            ttakesurvey.text = usr.total_survey


        }else{
            llgridmenu.visibility = View.GONE

        }


        Glide.with(requireContext()).load(R.drawable.ic_profile)
        .apply(RequestOptions().circleCrop()).into(imgprofile)
        usr.profileUser.image?.let {
            if (it.isNotEmpty())
                Glide.with(requireContext()).load(it)
                    .apply(RequestOptions().circleCrop()).into(imgprofile)
        }

        rendervaccine(tvstatusvaccine1, usr.profileUser.statusvaccine1?:"2")
        rendervaccine(tvstatusvaccine2, usr.profileUser.statusvaccine2?:"2")
    }

    fun rendervaccine(tv: TextView, status: String) {
        if (status == "1") {
            tv.setText(R.string.sudah_vaksin)
            tv.setTextColor(resources.getColor(R.color.green))
        } else {
            tv.setText(R.string.belum_vaksin)
            tv.setTextColor(resources.getColor(R.color.reds))
        }
    }

    fun updateImage(image: File?) {
        image.let {
            act.presenter.updateProfile(it, cb = object : ObjectResponseInterface<user> {
                override fun onSuccess(res: user) {
                    act.dismisLoadingDialog()
                    act.Toast("Update Profile Berhasil")
                    val myBitmap = BitmapFactory.decodeStream(FileInputStream(image))
                    Glide.with(requireContext()).load(myBitmap).apply(RequestOptions().circleCrop())
                        .into(imgprofile)
                }

                override fun onFailed(msg: String) {
                    act.dismisLoadingDialog()
                    act.Toast(msg)
                }

            })
        }
    }


    companion object {
        fun Instantiate(kacab:Boolean=false): MyAccountStatFragment {
            val sd = MyAccountStatFragment()
            sd.iskacab=kacab
            return sd
        }
    }


}

