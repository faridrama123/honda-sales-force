package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.user
import com.langit7.hondasalesforce.model.vaccinereason
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_vaccine.*

class VaccineActivity : BaseActivity() {

    lateinit var presenter: MainPresenter
    val listStatus = arrayOf("Pilih Status Vaksin", "Sudah Vaksin", "Belum Vaksin")

    var listReason = mutableListOf<vaccinereason>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccine)

        tactionbartitle.setText(R.string.update_data_vaksin)
        imgback.setOnClickListener {
            onBackPressed()
        }

        presenter = MainPresenter(this, APIServices)

        val adp =
            ArrayAdapter<String>(ctx, android.R.layout.simple_expandable_list_item_1, listStatus)
        spvaccine1.adapter = adp
        spvaccine2.adapter = adp


        llvaccine1reason.visibility = View.GONE
        llvaccine2reason.visibility = View.GONE

        spvaccine1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 2) {
                    llvaccine1reason.visibility = View.VISIBLE
                    llvaccine2.visibility = View.GONE
                    llvaccine2reason.visibility=View.GONE
                } else {
                    llvaccine1reason.visibility = View.GONE
                    llvaccine2.visibility = View.VISIBLE
                    spvaccine2.setSelection(0)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spvaccine2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 2)
                    llvaccine2reason.visibility = View.VISIBLE
                else
                    llvaccine2reason.visibility = View.GONE

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }




        presenter.getVaccineReason(object : DataListInterface<vaccinereason> {
            override fun onGetDataSuccess(res: List<vaccinereason>) {
                listReason.clear()
                listReason.addAll(res)

                var listReason1 = mutableListOf<String>()
                listReason1.add(0, "Pilih Alasan Belum Vaksin Pertama")
                listReason1.addAll(listReason.map { it.body })

                var listReason2 = mutableListOf<String>()
                listReason2.add(0, "Pilih Alasan Belum Vaksin Kedua")
                listReason2.addAll(listReason.map { it.body })

                var adpreason1 = ArrayAdapter<String>(
                    ctx,
                    android.R.layout.simple_expandable_list_item_1,
                    listReason1
                )
                var adpreason2 = ArrayAdapter<String>(
                    ctx,
                    android.R.layout.simple_expandable_list_item_1,
                    listReason2
                )
                spvaccine1reason.adapter = adpreason1
                spvaccine2reason.adapter = adpreason2
            }

            override fun onGetDataFailed(msg: String) {
            }

        })



        tvupdatevaccine.setOnClickListener {

            var vaccine1: String? = null
            var vaccine2: String? = null
            var reason1: String? = null
            var reason2: String? = null


            spvaccine1.selectedItemPosition.let {
                if (it > 0) {
                    vaccine1 = it.toString()
                    if (it == 2)
                        vaccine2 = it.toString()
                }else {
                    Toast("Pilih Status Vaksin Pertama!")
                    return@setOnClickListener
                }
            }
            spvaccine2.selectedItemPosition.let {
                if (it > 0)
                    vaccine2 = it.toString()
                else if (spvaccine1.selectedItemPosition == 1) {
                    Toast("Pilih Status Vaksin Kedua!")
                    return@setOnClickListener
                }
            }

            spvaccine1reason.selectedItemPosition.let {
                if (it > 0 && spvaccine1.selectedItemPosition == 2) {
                    reason1 = listReason.get(it - 1).body
                    reason2 = listReason.get(it - 1).body
                } else if (spvaccine1.selectedItemPosition == 2) {
                    Toast("Pilih Alasan Belum Vaksin Pertama!")
                    return@setOnClickListener
                }

            }

            spvaccine2reason.selectedItemPosition.let {
                if (it > 0 && spvaccine2.selectedItemPosition == 2)
                    reason2 = listReason.get(it - 1).body
                else if (spvaccine2.selectedItemPosition == 2) {
                    Toast("Pilih Alasan Belum Vaksin Kedua!")
                    return@setOnClickListener
                }
            }

            showLoadingDialog()
            presenter.updateProfile(
                vaccine1 = vaccine1,
                vaccine2 = vaccine2,
                reason1 = reason1,
                reason2 = reason2,
                cb = object : ObjectResponseInterface<user> {
                    override fun onSuccess(res: user) {
                        dismisLoadingDialog()
                        val ii = Intent()
                        ii.putExtra("user", res)
                        setResult(Activity.RESULT_OK, ii)
                        finish()
                    }

                    override fun onFailed(msg: String) {
                        dismisLoadingDialog()
                        Toast(msg)
                    }

                }
            )


        }


    }
}