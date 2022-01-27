package com.langit7.hondasalesforce.view.activity.nos

import NosAudit
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.databinding.ActivityListNosAuditBinding
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.teamreport.FrequentUser
import com.langit7.hondasalesforce.model.teamreport.PartisipantDetail
import com.langit7.hondasalesforce.model.teamreport.PartisipantQuiz
import com.langit7.hondasalesforce.presenter.adapter.teamreport.AdapterParticipantKuis
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.teamreport_kuis_peserta_activity.*

class ListNosAuditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListNosAuditBinding
    private lateinit var AdapterListNosAudit : AdapterListNosAudit
    var dataTemp = ArrayList<NosAudit>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_nos_audit)

        binding = ActivityListNosAuditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AdapterListNosAudit = AdapterListNosAudit()



        title = intent.getStringExtra("title").toString()


        tactionbartitle.text = title

        binding.bar.imgback    .setOnClickListener {
            onBackPressed()
        }


        getData()
    }

    fun getData(){

        dataTemp = generateDataH1Premises() as ArrayList<NosAudit>

        AdapterListNosAudit.setData(dataTemp)
        AdapterListNosAudit.notifyDataSetChanged()


        with(binding.rv) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = AdapterListNosAudit
        }
    }

    fun generateDataH1Premises(): List<NosAudit> {

        val nosAudit = ArrayList<NosAudit>()

        nosAudit.add(NosAudit("Kebersihan Dealer",
            "Exterior", "Exterior dalam keadaan: Bersih (Dibersihkan 1 tahun sekali - bukti ditunjukan) , Rapih & Tidak Rusak.\n" +
                    "Exterior terdiri dari :\n" +
                    "- Fascia\n" +
                    "- Pylon / Projecting Sign\n" +
                    "- Window Display\n" +
                    "- Louver\n" +
                    "- Kanopi\n" +
                    "\n" +
                    "Dinding Exterior (Pengecatan 2 tahun sekali - bukti ditunjukan) \n" +
                    "\n" +
                    "*Bila ditemukan kondisi kotor yang bukan akibat dari proses waktu (kebocoran , sarang binatang, lumpur, jejak sepatu maka harus segera dibersihkan tanpa menunggu jadwal rutin pembersihan)\n" +
                    "*Bukti pembersihan: \n" +
                    " - Bila menggunakan Vendor maka  Bukti Pembersihan berupa Kwitansi dan Dokumentasi dengan tanggal\n" +
                    " - Bila dilakukan oleh Dealer sendiri maka Bukti Pembersihan berupa Dokumentasi dengan tanggal"))
        nosAudit.add(NosAudit("Kebersihan Dealer",
            "Interior", "Kebersihan Interior :\n" +
                    "- Dinding Interior\n" +
                    "      - Pengecatan 2 tahun sekali - bukti ditunjukan\n" +
                    "      - Bersih dari sarang binatang \n" +
                    "*Bila ditemukan kondisi kotor yang bukan akibat dari proses waktu (kebocoran , lumpur, jejak sepatu maka harus segera dibersihkan tanpa menunggu jadwal rutin pembersihan)\n" +
                    "*Bukti pembersihan: \n" +
                    "       - Bila menggunakan Vendor maka  Bukti Pembersihan berupa Kwitansi dan Dokumentasi dengan tanggal\n" +
                    "        - Bila dilakukan oleh Dealer sendiri maka Bukti Pembersihan berupa Dokumentasi dengan tanggal\n" +
                    "\n" +
                    "- Lantai \n" +
                    "      - Dibersihkan minimal sehari 2 kali - bukti ditunjukan dengan Checklist Harian\n" +
                    "      - Dalam keadaan rapih dan tidak rusak \n" +
                    "- Toilet \n" +
                    "      - Dibersihkan minimal sehari 3 kali - bukti ditunjukan dengan Checklist Harian\n" +
                    "      - Dalam keadaan rapih dan tidak rusak \n" +
                    "- Furniture Standard Item: \n" +
                    "      - Dibersihkan minimal sehari 2 kali - bukti ditunjukan dengan Checklist Harian\n" +
                    "      - Dalam keadaan rapih dan tidak rusak \n" +
                    "*Bila ditemukan kondisi kotor (perubahan warna) maka harus segera dibersihkan tanpa menunggu jadwal rutin pembersihan atau diganti bila sudah tidak bisa dibersihkan\n" +
                    "\n" +
                    "- Item Furniture Recommended Item \n" +
                    "      - Dibersihkan minimal sehari 2 kali - bukti ditunjukan dengan Checklist Harian\n" +
                    "      - Dalam keadaan rapih dan tidak rusak \n" +
                    "*Bila ditemukan kondisi kotor (perubahan warna) maka harus segera dibersihkan tanpa menunggu jadwal rutin pembersihan atau diganti bila sudah tidak bisa dibersihkan\n" +
                    "\n" +
                    "- Display Motor (Dibersihkan minimal sehari 2 kali - bukti ditunjukan dengan Checklist Harian)"))


        nosAudit.add(NosAudit("Kebersihan Dealer", "Checklist Kebersihan Interior", "1. Terdapat checklist kebersihan untuk area showroom \n" +
                "2. Terdapat kolom checklist kebersihan minimal pada jam 07.30, 12.00, 15.00" ))

        nosAudit.add(NosAudit("Kebersihan Dealer", "Checklist Kebersihan Atribut", "1. Sesuai dengan gambar tampak depan yang sudah diapprove bersama\n" +
                "2. Gambar eksterior yang sudah diapproved, dicetak, ditempel di ruang Kacab" ))
            return nosAudit
    }
}