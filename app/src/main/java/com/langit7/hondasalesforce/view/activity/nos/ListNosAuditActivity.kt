package com.langit7.hondasalesforce.view.activity.nos

import NosAudit
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.common.Constants
import com.langit7.hondasalesforce.common.ExcelUtils
import com.langit7.hondasalesforce.common.FileShareUtils
import com.langit7.hondasalesforce.databinding.ActivityListNosAuditBinding
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.teamreport.FrequentUser
import com.langit7.hondasalesforce.model.teamreport.PartisipantDetail
import com.langit7.hondasalesforce.model.teamreport.PartisipantQuiz
import com.langit7.hondasalesforce.presenter.adapter.teamreport.AdapterParticipantKuis
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity




class ListNosAuditActivity : BaseActivity() {

    private lateinit var binding: ActivityListNosAuditBinding
    private lateinit var AdapterListNosAudit : AdapterListNosAudit
    var dataTemp = ArrayList<NosAudit>()
    var option1 = 0
    var option2 = 0
    var option3 = 0
    var option4 = 0

    var index = ""
    var judul = ""







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_nos_audit)

        binding = ActivityListNosAuditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AdapterListNosAudit = AdapterListNosAudit()



        judul = intent.getStringExtra("title").toString()

        binding.bar.tactionbartitle       .setText(judul)

        binding.bar.imgback    .setOnClickListener {
            onBackPressed()
        }

        index = intent.getStringExtra("index").toString()

        // submit data, and get results
        binding.submit   .setOnClickListener {

            // reset answer
             option1 = 0
             option2 = 0
             option3 = 0
             option4 = 0


            var data : ArrayList<NosAudit> =  AdapterListNosAudit.getData()
//            data.forEachIndexed {
//                    index, e ->
//
//                if(e.answer == 1){
//                    option1++
//                }
//                if(e.answer == 2){
//                    option2++
//                }
//                if(e.answer == 3){
//                    option3++
//                }
//
//                if(e.answer == 4){
//                    option4++
//                }
//            }
//
//         if(index=="1")   initiateExport(data, "H1 Premises", "Exist, Good", "Exist, Not Good", "Not Exist", "N/A")
//            if(index=="2")   initiateExport(data, "H1 People", "Exist, Done", "Exist, Not Done", "Not Exist", "N/A")
//            if(index=="3")   initiateExport(data, "H1 Process", "Exist, Done", "Exist, Not Done", "Not Exist", "N/A")


        }
    }

//    fun goToPeserta(index: String, title : String, data : ArrayList<NosAudit>
//    ) {
//
//        var ii= Intent (this, NosAuditResultsActivity::class.java)
//        ii.putExtra("index", index)
//        ii.putExtra("title", title)
//        ii.putExtra("data", data )
//
//        this.startActivity(ii)
//    }

    fun initiateExport(dataList: List<NosAudit>, title : String,
                       option1 : String,
                       option2 : String,
                       option3 : String,
                       option4 : String,) {
        Log.d(
            "",
            "initiateExport: "
        )


        // Initially setting Status as 'LOADING' and set/post value to generateExcelMLD
        val isExcelGenerated: Boolean = ExcelUtils.exportDataIntoWorkbook(
            application,
            Constants.EXCEL_FILE_NAME, dataList, title, option1, option2,option3,option4
        )
        if (isExcelGenerated) {
            Log.d("Succes", "Succes")
          //  onShareButtonClicked()
        } else {
            Log.d("Failed", "Failed")

        }
    }


    override fun onResume() {
        super.onResume()
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
                    " - Bila dilakukan oleh Dealer sendiri maka Bukti Pembersihan berupa Dokumentasi dengan tanggal", 0))
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
                    "- Display Motor (Dibersihkan minimal sehari 2 kali - bukti ditunjukan dengan Checklist Harian)",0))


        nosAudit.add(NosAudit("Kebersihan Dealer", "Checklist Kebersihan Interior", "1. Terdapat checklist kebersihan untuk area showroom \n" +
                "2. Terdapat kolom checklist kebersihan minimal pada jam 07.30, 12.00, 15.00",0 ))

        nosAudit.add(NosAudit("Kebersihan Dealer", "Checklist Kebersihan Atribut", "1. Sesuai dengan gambar tampak depan yang sudah diapprove bersama\n" +
                "2. Gambar eksterior yang sudah diapproved, dicetak, ditempel di ruang Kacab" ,0))


        nosAudit.add(NosAudit("Approval Layout New VinCi",
            "Eksterior",
            "1. Sesuai dengan gambar tampak depan yang sudah diapprove bersama\n" +
                    "2. Gambar eksterior yang sudah diapproved, dicetak, ditempel di ruang Kacab", 0))
        nosAudit.add(NosAudit("Approval Layout New VinCi",
            "Interior",
            "1. Area / Zoning Showroom sesuai dengan gambar yang sudah diapprove bersama : \n" +
                    "Item Interior yang ada di gambar approval berada didalam showroom dan digunakan sesuai fungsinya, khususnya:\n" +
                    "    - Sales & Finance Front Desk \n" +
                    "    - Negotiation Table  minimal sejumlah yang ada didalam gambar approval\n" +
                    "    - Khusus Wing Dealer maka Posisi Wing Corner harus sesuai dengan gambar approval dan Item Interior yang ada didalam Wing Corner harus sesuai dan tidak boleh dipindahkan , terkecuali meja negosiasi bila harus digantikan oleh unit motor\n" +
                    "\n" +
                    "2. Area / Zoning HUB / PENGHUBUNG sesuai dengan gambar yang sudah diapprove bersama : \n" +
                    "a. Ruang Tunggu \n" +
                    "     - Posisi dan Fungsinya sesuai dengan gambar interior yang sudah diapprove bersama\n" +
                    "b. Kasir\n" +
                    "    - Posisi dan Fungsinya sesuai dengan gambar interior yang sudah diapprove bersama\n" +
                    "\n" +
                    "3.Area / Zoning  H3 sesuai dengan gambar yang sudah diapprove bersama (4 Ruko)\n" +
                    "\n" +
                    "4. Area / Zoning  H2 sesuai dengan gambar yang sudah diapprove bersama\n" +
                    "\n" +
                    "5. Gambar interior yang sudah diapproved, dicetak, ditempel di ruang Kacab (untuk Wing Dealer, gambar yang dipajang adalah gambar yang sudah terdapat wing corner)", 0))
        nosAudit.add(NosAudit("Eksterior",
            "Fascia",
            "1. Dalam keadaan rapih\n" +
                    "2. Nama Dealer terbaca jelas\n" +
                    "3. Lampu menyala pada pukul 18:00 - 22:00 ( for New VinCi only )\n" +
                    "4. Dalam Keadaan Bersih\n" +
                    "5. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "6. Untuk new VinCi sesuai dengan gambar tampak depan yang sudah di approve bersama\n" +
                    "\n" +
                    "Hanya untuk temporary Fascia : \n" +
                    "1. Mengikuti Juklak AHM, ukuran mengikuti Standard yang ada di Manual Book AHM\n" +
                    "2. Warna tidak pudar (merah tidak memudar, sesuai standard warna Merah Honda) ", 0))
        nosAudit.add(NosAudit("Eksterior",
            "Pylon Atau Projecting Sign",
            "1. Dalam keadaan rapih\n" +
                    "2. Untuk logo Honda,detail tulisan penjualan,pemeliharaan,suku cadang tidak terhalangi dan dapat dilihat dan dibaca oleh pengguna jalan dari dua sisi\n" +
                    "3. Lampu menyala pada pukul 18:00 - 22:00 ( for New VinCi only )\n" +
                    "4. Dalam Keadaan Bersih\n" +
                    "5. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "6. Peletakan sesuai dengan denah yang sudah disepakati bersama", 0))
        nosAudit.add(NosAudit("Eksterior",
            "Canopy",
            "1. Dalam keadaan bersih (bukan debu) \n" +
                    "2. Dalam Keadaan Bersih\n" +
                    "3. Dalam Keadaan Rapih dan Tidak Rusak", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Window Display",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3.Display motor terlihat jelas \n" +
                    "4. Lampu menyala pada pukul 18:00 - 22:00 \n" +
                    "5. 1 Ruko = 4m diisi 1 motor dan 1 roller banner \n" +
                    "\n" +
                    "Dapat memilih N/A dengan melampirkan surat tertulis penundaan investasi dari MD ke AHM", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Kisi-Kisi Alumunium / Louvre ",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "\n" +
                    "Dapat memilih N/A dengan melampirkan surat tertulis penundaan investasi dari MD ke AHM", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Operational hour",
            "1. Dapat terlihat jelas oleh konsumen pada saat datang / sampai di halaman /pagar  Dealer\n" +
                    "2. Berinformasikan : Hari & Jam operational Showroom dan AHASS\n" +
                    "3. Dapat berupa sticker di kaca,banner,running text,spanduk", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Cadangan Masker",
            "Tersedia masker yang masih disegel, terletak di area security/pintu masuk, dan diberikan kepada konsumen yang tidak membawa masker saat memasuki area dealer", 0))

        nosAudit.add(NosAudit("Eksterior",
            "X-Banner Protab Kebersihan",
            "1. Terdapat X-Banner yang berisi edukasi protab kebersihan  di area security (pintu masuk)", 0))

        nosAudit.add(NosAudit("Eksterior",
            "",
            "", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Thermal Gun",
            "1. Spesifikasi: infrared dan digital\n" +
                    "2. Terdapat pada pintu masuk atau di area security / pintu masuk (dedicated untuk showroom)", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Tempat Cuci Tangan",
            "1. Spesifikasi: infrared dan digital\n" +
                    "2. Terdapat pada pintu masuk atau di area security / pintu masuk (dedicated untuk showroom)", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Tempat Cuci Tangan",
            "1. Tersedia tempat cuci tangan dan sabun pada area pintu masuk dealer\n" +
                    "2. Terdapat sabun cair \n" +
                    "3. Terdapat tissue kering\n" +
                    "*untuk dealer H123 minimal terdapat 1 tempat cuci tangan pada area pintu masuk dealer\n" +
                    "\n" +
                    "Dapat menyesuaikan rekomendasi juklak 164/AHM/MPA/VII/2020", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Area Tunggu Sementara",
            "1.Terdapat area tunggu sementara diluar showroom / AHASS yang dapat digunakan konsumen jika kondisi showroom / AHASS sedang ramai\n" +
                    "2. Nice to have jika berupa tenda", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Garis Pembatas",
            "1. Terdapat garis pembatas 1m pada area parkir yang dapat digunakan sebagai titik berdiri konsumen untuk memasuki showroom, jika kondisi sedang ramai", 0))

        nosAudit.add(NosAudit("Material Promosi Eksterior",
            "Product Banner/ Spanduk",
            "1. Memasang spanduk mengacu pada Line Up Bulanan (Notes : Juklak no 376/AHM/MPA/XI/2018  tanggal 28 November 2018)\n" +
                    "2. Ditempatkan di Pagar Dealer / Dinding Halaman Dealer / Dibawah Kanopi Dealer (Janggutan)\n" +
                    "3. Spanduk dalam keadaan :  \n" +
                    "- bersih dari kotoran/debu\n" +
                    "- tidak terlipat\n" +
                    "- tidak bertumpukan dengan material promosi lain\n" +
                    "- tidak melendut\n" +
                    "- tidak berkibar (sisi kanan dan kiri harus terikat)\n" +
                    "4. Design dan Periode pemasangan Sesuai dengan Line Up Bulanan", 0))

        nosAudit.add(NosAudit("Material Promosi Eksterior",
            "HGP atau AHM Oil Banner dan Vertical Banner",
            "1.Banner ditempatkan  di pagar dealer/dinding halaman dealer dibawah kanopi halaman dealer dan dalam keadaan bersih dari kotoran/debu, tidak terlipat, tidak bertumpukan  dengan material promosi lain , tidak berkibar (sisi kanan dan kiri harus terikat)i dan ter-update sesuai ketentuan PT AHM.\n" +
                    "2. Vertical banner ditempatkan dihalaman dealer/pagar dealer dan dalam keadaan bersih dari kotoran/debu, tidak terlipat, tidak bertumpukan  dengan material promosi lain dan ter-update sesuai ketentuan PT AHM.\n" +
                    "3. Periode pemasangan sesuai dengan ketentuan PT AHM\n", 0))
        nosAudit.add(NosAudit("Material Promosi Eksterior",
            "HGP atau AHM Oil Poster",
            "1. Poster dalam keadaan bersih dari kotoran/debu, tidak terlipat, tidak bertumpukan  dengan material promosi lain dan ter-update sesuai ketentuan PT AHM.\n" +
                    "2. Terdapat di AHASS / ruang tunggu AHASS\n" +
                    "\n" +
                    "*Periode pemasangan sesuai dengan ketentuan PT AHM.", 0))

        nosAudit.add(NosAudit("Material Promosi Eksterior",
            "Product Giant Banner (Old VinCi)",
            "1. Memasang giant banner di glass protector.\n" +
                    "2. Giant Banner dalam keadaan bersih dari kotoran/debu, tidak terlipat, tidak bertumpukan  dengan material promosi lain dan ter-update sesuai ketentuan PT AHM.\n" +
                    "\n" +
                    "*Periode pemasangan sesuai dengan ketentuan PT AHM", 0))

        nosAudit.add(NosAudit("Parking Area",
            "Parking Area",
            "1. Area parkir khusus digunakan untuk parkir customer.\n" +
                    "2. Memiliki marka parkir (warna putih/kuning)\n" +
                    "3. Bentuk Marka parkir mengikuti juklak\n" +
                    "4. Bersih dan tertata rapi (kendaraan tertata di dalam marka)\n" +
                    "\n" +
                    "*) Nilai N/A : bagi dealer yang tidak memiliki lahan untuk parkir (misal langsng trotoar/jalan).", 0))

        nosAudit.add(NosAudit("Parking Area",
            "Direction Signage (Showroom, Service, Spareparts)",
            "1. Terlihat jelas oleh customer\n" +
                    "2. Tanda penunjuk arah pada signage mengarahkan dan sesuai dengan lokasi Parkir/Showroom/AHASS\n" +
                    "\n" +
                    "*Mengikuti juklak yang ada\n" +
                    "(di detailkan peruntukannya)", 0))
        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Poster Reminder ",
            "Terdapat informasi untuk menggunakan masker dan  hand sanitizer pada pintu masuk showroom\n" +
                    "\n" +
                    "Dapat diletakan bersebelahan dengan hand santizer atau ditempel pada botol hand sanitizer", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Hand Sanitizer",
            "Terletak di pintu masuk showroom", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Dealing Table ",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Tertata rapi\n" +
                    "4. Dilengkapi permen, vas bunga (maks. 20 cm), buku tamu, AMDK (maks. 3 pcs), tisu basah kemasan botol, sanitizer, price list, dan kalendar product\n" +
                    "5.  Meja bewarna putih", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Corporate Identity Logo\n" +
                    "(New VinCi)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Perletakan sesuai dengan denah dealer yang sudah diapprove AHM", 0))
        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Direction Sign \n" +
                    "(Papan Gantung /  Menempel di Dinding)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Terbaca dan Terlihat Jelas\n" +
                    "4. Terdapat minimal : penjualan,pembiayaan, bengkel,suku cadang,ruang tunggu,toilet,smoking,no smoking,female toilet/male toilet/toilet", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Sales Front Desk & Finance Front Desk\n" +
                    "(New VinCi)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Tertata rapi \n" +
                    "4. Jumlah dan Perletakan sesuai dengan denah dealer yang sudah diapprove AHM\n" +
                    "5. Terdapat hand sanitizer", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Staff chair\n" +
                    "(New VinCi)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Tertata rapi\n" +
                    "4. Material setara : alas hitam,sandaran putih,tanpa sandaran tangan,kaki dengan roda,tuas untuk mengatur ketinggian posisi\n" +
                    "Ket : \n" +
                    "Untuk all front desk ", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Customer chair \n" +
                    "(New VinCi)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Tertata rapi\n" +
                    "4. Material setara : warna hitam dan merah,tanpa sandaran tangan dan kaki 4 tanpa roda\n" +
                    "Ket : \n" +
                    "Terletak di depan front desk ", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Safety Shield\n" +
                    "Nice to have",
            "1. terletak pada area meja sales counter dan meja fincoy\n" +
                    "2. sesuai dengan rekomendasi juklak 154/AHM/MPA/VI/2020", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            " Modification Rack Acc or Jacket Hanger (Old VinCi)",
            "1. Terpasang / terdisplay secara rapi dan bersih \n" +
                    "2. Standard display apparel tidak menggunakan plastik pembungkus, kecuali jika posisi dealer terbuka (tidak ada kaca) dan bila karakteristik konsumen menginginkan display menggunakan plastik, maka boleh menggunakan plastik (mika)\n" +
                    "\n" +
                    "* Untuk old VinCi dan Non boleh menggunakan rak modifikasi\n" +
                    "* Item arrangement mengikuti juklak yang dikirim oleh AHM setiap bulannya", 0))


        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Wall system display (New VinCi)",
            "1. Pengaturan rak mengikuti juklak yang dikirim oleh AHM setiap bulannya\n" +
                    "2. Pengaturan rapi\n" +
                    "3. Product yang di display dalam keadaan bersih\n" +
                    "4. Semua apparel menggunakan hanger dalam keadaan rapih, bersih & bebas debu, dengan spesifikasi sbb :\n" +
                    "Specification : Hook Material : Stainless (4.00 mm) Shoulder Material : Wood  Shoulder Colour : Wood Colour Size : Width : 420 mm Note : Ukuran tidak harus identik sama\n" +
                    "5. Standard display apparel tidak menggunakan plastik pembungkus, kecuali jika posisi dealer terbuka (tidak ada kaca) dan bila karakteristik konsumen menginginkan display menggunakan plastik, maka boleh menggunakan plastik (mika).\n" +
                    "6. Penempatan dan Jumlah Wall System Display sesuai dengan Layout yang sudah disetujui\n" +
                    "\n" +
                    "Wall System / Furniture\n" +
                    "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Spec Stand \n" +
                    "(New VinCi)",
            "1. Ditempatkan didalam area H1 dan bersebelahan dengan unit display.\n" +
                    "2. Terdapat sticker logo Honda\n" +
                    "3. Dalam Keadaan Bersih\n" +
                    "4. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "5. Type sesuai dengan unit yang didisplay\n" +
                    "6. Matprom yang digunakan sesuai dengan standard AHM", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Glass Showcase\n" +
                    "(New VinCi)",
            "1. Jika glass showcase ada di Premium Corner, maka yang harus di-display adalah Aksesoris Premium 2. Apparel dapat di-display dalam Glass Showcase, kecuali Jacket, T-Shirt, dan Helmet 3.HGA dan Apparel yang di-display dalam Glass Showcase dikeluarkan dari kemasan atau packaging-nya (kelengkapan baut, manual, dan lainnya tidak di-display di dalam Glass Showcase).\n" +
                    "4. Menggunakan acrylic display untuk :\n" +
                    "a. Aksesoris dan Apparel kecil seperti sarung tangan, Tank Pad, Garnish, dll.\n" +
                    "b. Aksesoris dan apparel besar seperti Seat Cowl, Fender Eliminator, dll. Specification : Material : Acrylic, Size : 90mm x 60mm Note : Ukuran tidak harus identik sama\n" +
                    "\n" +
                    "Glass Showcase / Furniture\n" +
                    "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak", 0))

        nosAudit.add(NosAudit("Material Promosi Interior",
            "X-banner Product",
            "1. Terpasang di samping atau di belakang sesuai unitnya. Minimal terdapat 3 X-Banner\n" +
                    "2. X-banner dalam keadaan bersih dari kotoran/debu, tidak terlipat dan ter-update sesuai ketentuan PT AHM.\n" +
                    "3. Dalam keadaan bersih dari kotoran / debu dan kondisi baik \n" +
                    "\n" +
                    "*Periode pemasangan harus sesuai dengan ketentuan line up bulanan", 0))


        nosAudit.add(NosAudit("Material Promosi Interior",
            "Wobbler",
            "1.  Terpasang sesuai di unit motor (di setiap series produk) minimal dipasang di 3 tipe yang berbeda\n" +
                    "2. Terdapat QR Code untuk scan aksesoris (unit baru)\n" +
                    "3. Terbaca oleh konsumen", 0))

        nosAudit.add(NosAudit("Material Promosi Interior",
            "Brochure Rack ",
            "1. Ditempatkan didalam area H1\n" +
                    "2. Tersedia minimum 1 di showroom\n" +
                    "3. Produk terbaru diletakan paling atas\n" +
                    "4. Diletakan dekat dengan ruang tunggu konsumen\n" +
                    "5. Terdapat sticker logo Honda\n" +
                    "6. Pemilihan Tipe Self Standing atau Wall Mounted disesuaikan dengan ruang yang tersedia didalam showroom.\n" +
                    "7. Dalam Keadaan Bersih\n" +
                    "8. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "\n" +
                    "*Disusun berdasarkan juklak bulanan yang dikirimkan oleh PT. AHM", 0))

        nosAudit.add(NosAudit("Material Promosi Interior",
            "Honda Contact Center Banner",
            "1. Ditempatkan di area yang terlihat oleh konsumen dan area ruang tunggu / showroom \n" +
                    "2.Design sesuai juklak AHM terupdate\n" +
                    "3. Berbentuk X-Banner\n" +
                    "4. Nomor telepon yang tertera adalah nomor telepon call center Honda dan Hotline Main Dealer", 0))


        nosAudit.add(NosAudit("Material Promosi Interior",
            "LCD TV",
            "1. Ditempatkan di dalam showroom \n" +
                    "2. Minimal berukuran 32 inchi dan dinyalakan selama jam buka showroom\n" +
                    "3. Terdapat looping TVC. \n" +
                    "4. Untuk Wing Dealer, akan disertakan material racing video, regular product, dan PDSA video.\n" +
                    "5. Dalam keadaan berfungsi (menyala)\n" +
                    "6. Bersih / bebas dari debu", 0))

        nosAudit.add(NosAudit("Material Promosi Interior",
            "Racing Video + PDSA Video",
            "1. Di tampilkan liputan sesuai dengan ketentuan dan juklak dari PT AHM", 0))

        nosAudit.add(NosAudit("Material Promosi Interior",
            "Box Duratrans (Old VinCi)",
            "1. Terpasang di dinding interior \n" +
                    "2. Lengkap dan terisi \n" +
                    "3. Dalam Keadaan Bersih\n" +
                    "4. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "5. Poster One Heart\n" +
                    "\n" +
                    "* sesuai ketentuan PT AHM", 0))

        nosAudit.add(NosAudit("Material Promosi Interior",
            "Wall Sticker One Heart (New VinCi)",
            "1. Terpasang di dinding interior \n" +
                    "2. Dalam keadaan bersih dari kotoran/debu, tidak terlipat\n" +
                    "3. Sticker One Heart\n" +
                    "\n" +
                    "* sesuai ketentuan PT AHM", 0))


        nosAudit.add(NosAudit("Material Promosi Interior",
            "9 Panel Poster Display \n" +
                    "(New VinCi)\n" +
                    "* > 3 ruko ",
            "1. Terpasang di 9 panel poster display.\n" +
                    "2. Pemasangan  panel tidak diperbolehkan terhalangi objek lain.\n" +
                    "3. Dalam Keadaan Bersih\n" +
                    "4. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "5. Periode pemasangan dan material poster yang didisplay sesuai ketentuan PT AHM\n" +
                    "6. Peletakan berada di area showroom\n", 0))


        nosAudit.add(NosAudit("Material Promosi Interior",
            "Poster Display \n" +
                    "(New VinCi)",
            "1. Ditempatkan didalam area H1 dan dekat dengan ruang tunggu (dekat sofa ruang tunggu)\n" +
                    "2. Dalam Keadaan Bersih\n" +
                    "3. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "4. Berisikan promosi dan layanan yang ada di dealer (contoh : sales champaign di dealer)\n" +
                    "5. Peletakan berada di area showroom", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Unit Display Arrangement",
            "1. Mengelompokkan base on juklak bulanan yang dikirim oleh PT. AHM\n" +
                    "2. Mengatur jarak antar unit minimum 60-70 cm \n" +
                    "3. Unit bersih (diperbolehkan menggunakan plastik wrapping jok dengan kondisi yang bersih dan rapi)\n" +
                    "4. Melengkapi spion\n" +
                    "5. Meletakkan di lokasi yang strategis (mudah dilihat konsumen)\n" +
                    "6. Memberi penerangan yang cukup (Semua lampu dipastikan dalam kondisi menyala) \n" +
                    "7. Unit display dilengkapi dengan tag PDI\n" +
                    "\n" +
                    "* sesuai ketentuan PT AHM\n", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Unit Display di Stage (Old VinCi)",
            "1. Memasang produk sesuai juklak bulanan yang dikirimkan oleh PT. AHM\n" +
                    "2. Meletakkan di area strategis, mudah dilihat.\n" +
                    "3. Memberi penerangan yang cukup\n" +
                    "\n" +
                    "* sesuai ketentuan PT AHM", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Unit display with accessories",
            "1. Semua motor harus terpasang aksesoris \n" +
                    "2. Jika ada varian motor yang sama namun berbeda warna atau tipe, cukup salah satu saja yang dipasang aksesoris (contoh : Di dealer ada 1 Vario 125 dan 1 Vario 150, maka cukup salah satu motor saja yang dipasang aksesoris)\n" +
                    "\n" +
                    "*Sesuai dengan juklak yang dikirimkan bulanan oleh PT. AHM", 0))


        nosAudit.add(NosAudit("Unit Display",
            "Clear File Holder untuk Informasi HGA, Apparel, dan Customized Parts",
            "1. Untuk penyimpanan media informasi berupa : \n" +
                    "a. Brosur acc & apparel\n" +
                    "b. Price List HGA, Apparels,Customized Parts per tipe motor (bukan brosur unit),\n" +
                    "c. Brosur bengkel modifikasi, dengan ketentuan sbb :\n" +
                    "- Format Brosur Bengkel Modifikasi disesuaikan dengan format masing-masing Main Dealer\n" +
                    "- Brosur Bengkel Modifikasi untuk CBR250RR dan CRF150L (Jika bukan Wing Dealer, maka hanya akan ada brosur Bengkel Modifikasi untuk CRF150L)\n" +
                    "d. Brosur customized parts, dengan ketentuan sbb :\n" +
                    " *Ter-update sesuai dengan ketentuan PT. AHM\n" +
                    "e. Sales talk HGA & Apparel\n" +
                    "Jumlah Sales Talk Aksesoris per tipe motor harus ada sejumlah  (Contoh : jika di Juklak ada 10 model motor yang terpasang aksesoris, maka harus ada 10 sales talk aksesoris per tipe motor).\n" +
                    "2. Ukuran Clear File Holder : Folio\n", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Banner Community",
            "1. Berupa X-Banner ", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Hero Stage\n" +
                    "(New VinCi)\n" +
                    "* > 3 ruko ",
            "1. Memasang produk volume seller atau best seller\n" +
                    "2. Meletakkan di area strategis, mudah dilihat.\n" +
                    "3. Memberi penerangan yang cukup (Semua lampu dipastikan dalam kondisi menyala)\n" +
                    "4. Perletakan sesuai dengan denah dealer yang sudah diapprove AHM\n" +
                    "\n" +
                    "Hero Stage / Furniture\n" +
                    "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak", 0))


        nosAudit.add(NosAudit("Unit Display",
            "Isolate Stage\n",
            "1. Memasang produk baru dalam periode 3 bulan setelah launching atau selama periode yang ditentukan AHM\n" +
                    "2. Meletakkan di area strategis, mudah dilihat.\n" +
                    "3. Memberi penerangan yang cukup (Semua lampu dipastikan dalam kondisi menyala)\n" +
                    "Keterangan : \n" +
                    "Untuk area di luar wing corner:\n" +
                    "- 2 ruko : 0 \n" +
                    "-  3 - 4 ruko : 1 unit\n" +
                    "4. Perletakan sesuai dengan denah dealer yang sudah diapprove AHM\n" +
                    "\n" +
                    "Isolate Stage / Furniture\n" +
                    "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Mannequin\n",
            "1. Warna Putih\n" +
                    "2. Seluruh badan lengkap dengan kepala dan tidak boleh ada berwajah (faceless)\n" +
                    "3. Dikarenakan mannequin yang digunakan adalah full body, maka harap dilengkapi juga dengan celana dan sepatu\n" +
                    "4. Apparel yang di-display pada mannequin harus senada dan satu tema antara Jacket, T-Shirt, Gloves, Helmet, atau Cap (misal tema Honda Racing Red, maka dari Helmet/Cap, Jacket, Gloves, dan Shirt dapat menggunakan line up Honda Racing Red)\n" +
                    "5. Peletakan berada di area showroom\n" +
                    "6. Jika ada sales program Direct Gift Apparel, maka mannequin boleh dipergunakan untuk mendukung program tersebut selama periode program", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Glass Showcase\n" +
                    "(New VinCi)\n" +
                    "*>4 ruko",
            "1. Dapat diisi dengan Aksesoris dan Apparel Regular (jika posisi Glass Showcase diluar Premium Corner)\n" +
                    "2. Apparel dapat di-display dalam Glass Showcase, kecuali Jacket, T-Shirt, dan Helmet\n" +
                    "3.HGA dan Apparel yang di-display dalam Glass Showcase dikeluarkan dari kemasan atau packaging-nya (kelengkapan baut, manual, dan lainnya tidak di-display di dalam Glass Showcase)\n" +
                    "4. Peletakan berada di area showroom\n" +
                    "\n" +
                    "Glass Showcase / Furniture\n" +
                    "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Riding Test Unit ",
            "1. Riding Test Unit wajib tersedia di setiap dealer. Tipe dan jumlah mengacu pada juklak bulanan PT AHM.\n" +
                    "\n" +
                    "2. Unit Riding Test wajib selalu dalam keadaan bersih & layak digunakan. \n" +
                    "\n" +
                    "3. Riding Test Unit wajib di PDI sebelum digunakan oleh konsumen. Bukti PDI harap disimpan.\n" +
                    "\n" +
                    "4. STNK sepeda motor unit test ride wajib atas nama Dealer/Owner/Kepala Cabang.\n" +
                    "\n" +
                    "5. Jarak tempuh sepeda motor (dilihat pada speedometer) berada pada angka yang wajar sesuai penggunaan.\n" +
                    "\n" +
                    "6. Penempatan unit Riding Test adalah di Riding Test Parking Area. \n" +
                    "\n" +
                    "7. Berikut contoh utilisasi unit riding test di Dealer :\n" +
                    " a. Riding Test Prospek Konsumen\n" +
                    " b. Exhibition, Roadshow & Sales Event\n" +
                    " c. Wing SF Weekend Riding\n" +
                    " d. Leisure Touring\n" +
                    " e. Fun Race Track Day", 0))


        nosAudit.add(NosAudit("Unit Display",
            "Riding test guest book",
            "1. Terdapat kolom hari/tanggal, nama, alamat, no telpon, tipe motor yang disukai (tertarik), warna dan saran/kritik.\n" +
                    "2. Tertata rapi dalam folder.\n" +

                    "3. Form riding test guest book dipegang oleh Wing Sales People dan Sales People Regular.", 0))


        nosAudit.add(NosAudit("Unit Display",
            "Riding test parking area",
            "1. Terdapat marka khusus untuk riding test unit parking area. Desain tidak diberikan dari AHM, masing-masing dealer bebas berkreasi untuk desain riding test unit parking area.\n" +
                    "\n" +
                    "2. Pada riding test unit parking area terdapat tulisan “Test Ride Zone” atau “Riding Test Area” yang terlihat jelas oleh konsumen.\n" +
                    "\n" +
                    "3. Riding Test Unit Parking Area terletak di dekat pintu masuk showroom.\n" +
                    "\n" +
                    "4. Riding Test Unit Parking Area harus dapat memuat minimal 2 unit riding test (latest product).\n" +
                    "\n" +
                    "5. Riding Test Unit Parking Area disediakan oleh dealer.\n" +
                    "\n" +
                    "6. Pengecualian untuk tidak memiliki Riding Test Unit Parking Area diberikan bagi dealer yang tidak memiliki area parkir/halaman depan.\n", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Riding Test Signage",
            "1. Terlihat jelas oleh customer \n" +
                    "2. Dalam keadaan bersih \n" +
                    "3. Terpasang di plat motor bertuliskan \"test ride\" jika motor sedang tidak digunakan.", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Cairan Pembersih dan Wiping Tools Riding Test",
            "1. berisi campuran air bersih dengan sabun (dilarang menggunakan cairan yang mengandung alkohol)\n" +
                    "2. wiping tools microfiber / plas chamois \n" +
                    "3. Terletak di sekitar area riding test", 0))

        nosAudit.add(NosAudit("Unit Display",
            "HRT Banner (New VinCi)\n" +
                    "(* > 3 ruko)",
            "1. Diletakkan dekat dengan HRT\n" +
                    "2. Terlihat jelas oleh customer \n" +
                    "3. Dalam Keadaan Bersih\n" +
                    "4. Dalam Keadaan Rapih dan Tidak Rusak", 0))


        nosAudit.add(NosAudit("Unit Display",
            "HRT\n" +
                    "(* > 3 ruko)",
            "1. HRT harus berfungsi dengan baik\n" +
                    "2. Terdapat buku tamu HRT \n" +
                    "3. Dalam keadaan bersih", 0))

        nosAudit.add(NosAudit("Riding Test",
            "Riding Test Unit ",
            "1. Riding Test Unit wajib tersedia di setiap dealer. Tipe dan jumlah mengacu pada juklak bulanan PT AHM.\n" +
                    "\n" +
                    "2. Unit Riding Test wajib selalu dalam keadaan bersih & layak digunakan. \n" +
                    "\n" +
                    "3. Riding Test Unit wajib di PDI sebelum digunakan oleh konsumen. Bukti PDI harap disimpan.\n" +
                    "\n" +
                    "4. STNK sepeda motor unit test ride wajib atas nama Dealer/Owner/Kepala Cabang.\n" +
                    "\n" +
                    "5. Jarak tempuh sepeda motor (dilihat pada speedometer) berada pada angka yang wajar sesuai penggunaan.\n" +
                    "\n" +
                    "6. Penempatan unit Riding Test adalah di Riding Test Parking Area. \n" +
                    "\n" +
                    "7. Berikut contoh utilisasi unit riding test di Dealer :\n" +
                    " a. Riding Test Prospek Konsumen\n" +
                    " b. Exhibition, Roadshow & Sales Event\n" +
                    " c. Wing SF Weekend Riding\n" +
                    " d. Leisure Touring\n" +
                    " e. Fun Race Track Day", 0))

        nosAudit.add(NosAudit("Riding Test",
            "Riding test guest book",
            "1. Terdapat kolom hari/tanggal, nama, alamat, no telpon, tipe motor yang disukai (tertarik), warna dan saran/kritik.\n" +
                    "2. Tertata rapi dalam folder.\n" +

                    "3. Form riding test guest book dipegang oleh Wing Sales People dan Sales People Regular.", 0))


        nosAudit.add(NosAudit("Riding Test",
            "Riding test parking area",
            "1. Terlihat jelas oleh customer \n" +
                    "2. Dalam keadaan bersih \n" +
                    "3. Terpasang di plat motor bertuliskan \"test ride\" jika motor sedang tidak digunakan.", 0))

        nosAudit.add(NosAudit("Riding Test",
            "Cairan Pembersih dan Wiping Tools Riding Test",
            "1. berisi campuran air bersih dengan sabun (dilarang menggunakan cairan yang mengandung alkohol)\n" +
                    "2. wiping tools microfiber / plas chamois \n" +
                    "3. Terletak di sekitar area riding test", 0))

        nosAudit.add(NosAudit("Riding Test ",
            "HRT Banner (New VinCi)\n" +
                    "(* > 3 ruko)",
            "1. Diletakkan dekat dengan HRT\n" +
                    "2. Terlihat jelas oleh customer \n" +
                    "3. Dalam Keadaan Bersih\n" +
                    "4. Dalam Keadaan Rapih dan Tidak Rusak", 0))

        nosAudit.add(NosAudit("Riding Test",
            "HRT",
            "1. HRT harus berfungsi dengan baik\n" +
                    "2. Terdapat buku tamu HRT \n" +
                    "3. Dalam keadaan bersih", 0))

        nosAudit.add(NosAudit("Indent",
            "Information Board for indent management",
            "1. Indent Information Board dibagi ke dalam 4 bagian, yaitu:\n" +
                    "• Daftar Indent: isi dari form indent dipindahkan ke bagian ini.\n" +
                    "• Feedback: berisi informasi estimasi unit diterima konsumen\n" +
                    "• Form Monitoring: berisi informasi nama sales people, Honda ID, Tanggal Follow-Up 1-3.\n" +
                    "• Update Status: berisi informasi status (Open/Close/Cancel), tanggal pembelian, nomor mesin dan keterangan untuk status indent (mengapa masih open, mengapa cancel).\n" +
                    "\n" +
                    "2. Terdapat 3 pilihan Indent Information Board, yaitu:\n" +
                    "• Menggunakan televisi berukuran minimal 32 inch.\n" +
                    "• Menggunakan printed whiteboard dengan ukuran 120 x 180 cm.\n" +
                    "• Menggunakan kertas print-out yang ditempelkan pada whiteboard. Kertas berukuran minimal A4.\n" +
                    "\n" +
                    "3. Indent Information Board ditempatkan di ruang briefing sales people.", 0))

        nosAudit.add(NosAudit("Indent",
            "Form Indent",
            "1. Available \n" +
                    "2. Berisi keterangan : tanggal, tipe, warna, nama customer, alamat, no. telpon, tanda jadi\n" +
                    "3. Ukuran minimal A4, harus di print\n" +
                    "4. Tersimpan rapi dalam folder\n" +
                    "5. Mengikuti standard dari AHM terupdate", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Daftar Perjanjian\n" +
                    "(contohnya)",
            "1. Berisi keterangan :Nama Customer, contact number, Tanggal dan waktu perjanjian, Salespeople yang membuat janji, dokumen yang dibutuhkan, Kebutuhan datang ke Dealer\n" +
                    "2. Semua konten terisi\n" +
                    "3. Di update setiap hari", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Album Katalog Brosur ",
            "1. Brosur lengkap dan update untuk semua type di dalam Clear File Holder\n" +
                    "2. Dalam keadaan rapi \n" +
                    "\n", 0))


        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Surat Pesanan Kendaraan",
            "1. Minimal Berisi kolom yang distandardkan AHM\n" +
                    "2. Terdapat nama dan alamat Dealer, logo dealer opsional\n" +
                    "3. Form harus di print dan atau menggunakan sistem (aplikasi)\n" +
                    "4. Ditanda tangan oleh konsumen", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Lampiran estimasi Delivery time,STNK / BPKB, dan penjelasan refund,indent  policy",
            "1. Berisikan : estimasi delivery time ke konsumen, estimasi STNK / BPKB, dan kolom checklist bahwa konsumen sudah dijelaskan terkait refund dan indent policy \n" +
                    "2. Ditanda tangan oleh salespeople dan konsumen \n" +
                    "3. Dilampirkan di SPK", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Form Pengajuan STNK / Customer Database ",
            "1. Available berupa : apps / hard copy \n" +
                    "2. Terdapat di CS dan salesman\n" +
                    "3. Konten minimal sesuai dari AHM\n" +
                    "4. Tambahan konten dari form CDB lama :   \n" +
                    "    a. Tempat Lahir Konsumen\n" +
                    "    b. Hobi\n" +
                    "    c. Pilihan Pegawai Swasta dan Wiraswasta / Pedagang, bidang / sektor : \n" +
                    "        Agricultural, Industri, Konstruksi, Mining, Jasa, Retail\n" +
                    "    d. Nama Instansi / Usaha\n" +
                    "    e. Alamat, kecamatan, kota, propinsi Instansi / Usaha\n" +
                    "    f. Aktivitas Penjualan (Gathering / Showroom Event, Walk In, Canvassing, Ruang Tunggu AHASS, Pameran Besar, Pameran Menengah / Kecil, Roadshow, SMS/WA/Call (FU CDB), Visit (FU CDB), Social Media, Flyering, Website, Mobile App MD/Dealer, Referral, Contact Center, External Parties (Leasing/Insurance), Virtual Event, Others)\n" +
                    "5. Surat persetujuan database konsumen yang dilengkapi tanda tangan konsumen\n", 0))


        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Database Kartu Keluarga",
            "1. Terdapat di sistem Dealer\n" +
                    "2 Konten minimal sesuai dari AHM, berisi : \n" +
                    "     a. Nomor Kartu keluarga\n" +
                    "     b. NIK\n" +
                    "     c. Nama Lengkap\n" +
                    "     d. Jeni Kelamin\n" +
                    "     e. Tempat lahir\n" +
                    "     f. Tanggal lahir\n" +
                    "     g. Agama\n" +
                    "     h. Pendidikan\n" +
                    "     i. Jenis Pekerjaan\n" +
                    "     j. Status pernikahan\n" +
                    "     k. Status Hubungan dalam keluarga\n" +
                    "     l. Kewarganegaraan", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Price List",
            "Ketentuan Pricelist Produk Premium :\n" +
                    "1. Pricelist menggunakan bahan Art Paper, 120 gram dan 4/4 full color (sesuai dengan arahan dari MPA AHM).\n" +
                    "\n" +
                    "2. Pricelist memuat Harga OTR yang sesuai.\n" +
                    "\n" +
                    "3. Penempatan Pricelist adalah di dealing table dalam area Wing Corner.\n" +
                    "\n" +
                    "4. Pricelist wajib untuk dicetak & dipersiapkan.\n" +
                    "\n" +
                    "5. Jumlah Pricelist yang tersedia di dealer mencukupi untuk diberikan ke konsumen.\n" +
                    "\n" +
                    "Ketentuan Pricelist Produk Regular :\n" +
                    "1. Pricelist memuat informasi Nama Dealer, Alamat Dealer, No. Telpon Dealer, Nama Sales People dan No. Kontak Sales People.\n" +
                    "\n" +
                    "2. Pricelist memuat Harga OTR yang sesuai dengan ketentuan AHM dan MD.\n" +
                    "\n" +
                    "3. Pricelist wajib untuk dicetak & dipersiapkan.\n" +
                    "\n" +
                    "4. Pricelist wajib ada di setiap meja dealing.\n" +
                    "\n" +
                    "5. Jumlah Pricelist yang tersedia di dealer mencukupi untuk diberikan ke konsumen.", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Service Talk Flyer",
            "1. Desain sesuai standard AHM\n" +
                    "2. Terdapat pada Map Kerja Sales People\n" +
                    "3. Available \n", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Guest Book",
            "1. Available berupa : apps / hard copy (minimal berukuran A4)\n" +
                    "2. Terdapat di CS dan salesman\n" +
                    "3. Konten minimal sesuai dari AHM, berisi :\n" +
                    "    a. tanggal\n" +
                    "    b. nama\n" +
                    "    c. alamat email\n" +
                    "    d. sosmed & ID sosial media\n" +
                    "    e. no telp/HP\n" +
                    "    f. alamat lengkap\n" +
                    "    g. varian motor dan warna\n" +
                    "    h. status Hot/Medium/Low\n" +
                    "    i. New Cust/RO\n" +
                    "    j. Cash/credit\n" +
                    "    k. follow up 1-3\n" +
                    "    l. keterangan.\n" +
                    "4. Tidak berserakan,tertata rapi\n" +
                    "5. Dimiliki setiap Salespeople\n" +
                    "6. Dilaporkan ke PIC Dealer setiap hari dan diinput ke sistem/excel setiap hari sebagai data prospek (data yang di dalam buku sesuai dengan data yang di excel / sistem)", 0))


        nosAudit.add(NosAudit("Customer Facilities",
            "Ashtray",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Diletakkan di area smoking", 0))

        nosAudit.add(NosAudit("Customer Facilities",
            "Charging area\n" +
                    "(Nice to have)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak / Berfungsi\n" +
                    "3. Terdapat sticker informasi untuk charging area\n" +
                    "4. Design direkomendasikan mengikuti juklak AHM\n", 0))

        nosAudit.add(NosAudit("Customer Facilities",
            "Jaringan Internet Wi-Fi & Design Wi-Fi Sticker \n",
            "1. Terdapat sticker informasi free wifi sesuai dengan desain di samping\n" +
                    "     a. New ViCi : boleh menggunakan desain No. 1\n" +
                    "     b. Old ViCi : wajib menggunakan desain No. 2\n" +
                    "2. Informasi terbaca jelas oleh konsumen (tanpa penghalang)\n" +
                    "3. Terdapat informasi Username dan Password wifi untuk konsumen\n" +
                    "4. Jaringan wifi berfungsi dengan baik", 0))

        nosAudit.add(NosAudit("Customer Facilities",
            "Meja Observasi \n" +
                    "(New VinCi)\n",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Material dan design mengikuti rekomendasi dari AHM : warna putih,dilengkapi laci untuk meletakkan tas\n" +
                    "4. Peletakan sesuai dengan denah yang sudah disepakati bersama\n" +
                    "5. Terdapat hand sanitizer\n", 0))

        nosAudit.add(NosAudit("Customer Facilities",
            "Kursi Observasi\n" +
                    "(New VinCi)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Jumlah kursi (include sofa)  = 2 x jumlah pit \n" +
                    "4. Material dan design mengikuti rekomendasi dari AHM : bewarna hitam,merah dalam 1 meja ( selang seling ), dengan sandaran tangan,tanpa roda\n" +
                    "5. Peletakan sesuai dengan denah yang sudah disepakati bersama\n" +
                    "6. diberi jarak antar kursi sesuai juklak No 136/AHM/MPA/IV/2020", 0))


        nosAudit.add(NosAudit("Customer Facilities",
            "Snack Corner\n" +
                    "(New VinCi)\n" +
                    "(nice to have)",
            "1. Tersedia snack dan minuman\n" +
                    "2. Dalam Keadaan Bersih\n" +
                    "3. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "4. Material dan design dianjurkan  mengikuti rekomendasi dari AHM \n", 0))

        nosAudit.add(NosAudit("Customer Facilities",
            "Toilet",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Memilki Item minimal :\n" +
                    "  - Kloset Duduk / Jongkok\n" +
                    "  - Terdapat Bak / Penampung air / Hand Shower\n" +
                    "  - Terdapat Tissue\n" +
                    "  - Terdapat Wastafel (didalam / diluar dinding toilet)\n" +
                    "  - Terdapat Cermin (seukuran minimal 1 muka orang dewasa)\n" +
                    "  - Terdapat Floor Drain dan lubangnya tertutup rapih\n" +
                    "  - Terdapat Tong Sampah\n" +
                    "  - Terdapat Rak Hambalan / Hook Pengantung\n" +
                    "4. Terdapat checklist kebersihan minimal pada jam 07.30, 12.00, 15.00", 0))

        nosAudit.add(NosAudit("Payment",
            "Bukti Pembayaran ",
            "1. Bukti pembayaran harus mencantumkan nama, alamat dealer dan No. Telp Dealer (logo dealer opsional).\n" +
                    "\n" +
                    "2. Bukti pembayaran harus di-print dengan ukuran A5 berbahan kertas karbon dan terdapat cap/stempel resmi dealer.\n" +
                    "\n" +
                    "3. Bukti pembayaran terletak di area kasir.", 0))


        nosAudit.add(NosAudit("Payment",
            "Pre Delivery Safety Advice (PDSA) Flyer",
            "1. Flyer wajib dicetak dengan ukuran A5 (print 2 sisi bolak-balik)\n" +
                    "2. Dimasukan di dalam acryic mini display \n" +
                    "3. Diletakan di loket kasir yang mudah terbaca untuk konsumen dan kasir", 0))

        nosAudit.add(NosAudit("Payment",
            "Direction Sign\n" +
                    "(New VinCi)",
            "1. Terlihat jelas oleh customer\n" +
                    "2. Dalam Keadaan Bersih\n" +
                    "3. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "\n" +
                    "Ket : Menempel pada dinding / pada langit - langit Dealer", 0))

        nosAudit.add(NosAudit("",
            "Name Card",
            "Name card 2 sisi :\n" +
                    "- Bagian depan : informasi Nama & Alamat Dealer, Nama dan No. Telp. sales people, Nama dan No. Telp. Service Advisor, Logo Wing Dealer, Nama Kepala Cabang (No Hp optional), Foto (optional)\n" +
                    "\n" +
                    "- Bagian Belakang : Logo Wing, No Telp Penjualan dan Pemeliharaan / Bengkel, sosial media dealer (optional)\n", 0))

        nosAudit.add(NosAudit("",
            "Data Follow-Up RO unit Sepeda Motor Honda",
            "1. Data RO penjualan unit SMH yang dibagi oleh PIC CRM \n" +
                    "2. Available berupa apps / file ms. Excel / hard copy\n" +
                    "3. Terdapat di CS / sales people / telemarketing / admin CRM H1\n" +
                    "4. Konten minimal berisi : \n" +
                    "     a. nama\n" +
                    "     b. alamat\n" +
                    "     c. no hp\n" +
                    "     d. pekerjaan\n" +
                    "     e. tipe & tahun motor sebelumnya\n" +
                    "     f. hasil follow up 1, 2, 3\n" +
                    "     g. keterangan", 0))


        nosAudit.add(NosAudit("",
            "Form Follow-Up RO unit Sepeda Motor Honda dari ruang tunggu AHASS",
            "1. Data follow up ruang tunggu AHASS\n" +
                    "2. Available berupa apps / file ms. Excel / hard copy\n" +
                    "3. Terdapat di CS \n" +
                    "4. Konten minimal berisi : \n" +
                    "     a. nama\n" +
                    "     b. no hp\n" +
                    "     c. tipe & tahun motor sebelumnya\n" +
                    "     d. dealer pembelian SMH sebelumnya (dealer sendiri / dealer lain)\n" +
                    "     e. tanggal follow up\n" +
                    "     f. hasil follow up (hot/medium/low)\n" +
                    "     g. varian motor dan warna (khusus konsumen yang tertarik)\n" +
                    "     h. keterangan ", 0))


        nosAudit.add(NosAudit("Warehouse",
            "FIFO ",
            "1. Menjalankan FIFO: Dijalankan berdasarkan nomor aging per bulan saat unit motor diterima Dealer\n" +
                    "2. Unit motor defect/ Non Ready for Sales dipisahkan\n" +
                    "3. Penyusunan unit mempertimbangkan jarak antar motor (tidak bersentuhan antara unit motor)\n" +
                    "4. Penyusunan unit per tipe per warna \n" +
                    "5. Menggunakan alat bantu visual Tag FIFO (digantung di motor dan berdasarkan Urutan Huruf) atau jika dealer sudah mempunyai sistem, dapat dibuktikan pada saat audit)", 0))

        nosAudit.add(NosAudit("Warehouse",
            "Border Line",
            "1. Jelas dan terlihat ", 0))

        nosAudit.add(NosAudit("Warehouse",
            "Kondisi Warehouse",
            "1. Sesuai dengan kebutuhan kapasitas gudang unit = Dealer Stock days target MD * Target Daily Sales Dealer (untuk dealer stock days nya apakah mau dibuat sheet baru untuk informasi dealer stock days per MD nya)\n" +
                    "2. Sesuai dengan kebutuhan luas gudang (m2)= Kapasitas gudang (unit)* 2.2 m2\n" +
                    "3. Dalam keadaan rapi dan bersih", 0))

        nosAudit.add(NosAudit("Warehouse",
            "Kondisi Warehouse",
            "1. Sesuai dengan kebutuhan kapasitas gudang unit = Dealer Stock days target MD * Target Daily Sales Dealer (untuk dealer stock days nya apakah mau dibuat sheet baru untuk informasi dealer stock days per MD nya)\n" +
                    "2. Sesuai dengan kebutuhan luas gudang (m2)= Kapasitas gudang (unit)* 2.2 m2\n" +
                    "3. Dalam keadaan rapi dan bersih", 0))


        nosAudit.add(NosAudit("Warehouse",
            "Premium Zone ",
            "1. Premium zone sebagai tempat meletakkan unit premium\n" +
                    "2. Premium zone dibatasi oleh border line berwarna kuning cerah\n" +
                    "3. Terdapat SIGNING “Premium Zone” yang ditempel di dinding atau digantung di area tersebut", 0))

        nosAudit.add(NosAudit("Delivery",
            "Kelengkapan Standard Unit",
            "1. Pengiriman unit  dilengkapi dengan KSU (buku pedoman pemilik, helm, tool set dan buku servis)", 0))

        nosAudit.add(NosAudit("Delivery",
            "Appreciation card ",
            "1. Berisi : nomor kontak service booking, email dealer,nomor telepon,nomor kontak showroom,nomor kontak AHASS, nomor hotline Dealer / Main Dealer \n" +
                    "2. Tercetak \n" +
                    "3. Ukuran sebesar KTP / SIM (setelah dilipat)", 0))


        nosAudit.add(NosAudit("Delivery",
            "Delivery Car",
            "1. Minimal Jumlah delivery car = Round up (Target jualan per bulan /200)\n" +
                    "2. Kondisi mobil dalam keadaan bersih\n" +
                    "3. Terdapat refreshing tissue untuk delivery man\n" +
                    "*apabila menggunakan pihak ketiga, maka design tetap mengikuti standard ", 0))

        nosAudit.add(NosAudit("Delivery",
            "Cairan Pembersih dan Wiping Tools Delivery ",
            "1. berisi campuran air bersih dengan sabun (dilarang menggunakan cairan yang mengandung alkohol)\n" +
                    "2. wiping tools microfiber / plas chamois \n" +
                    "3. Terletak di dalam mobil delivery", 0))

        nosAudit.add(NosAudit("Follow Up First Call ",
            "Telepon/Handphone",
            "1. Berfungsi dengan baik\n" +
                    "2. Dedicated untuk kegiatan follow up konsumen", 0))

        nosAudit.add(NosAudit("Follow Up First Call ",
            "",
            "1. Available berupa apps / file ms. Excel\n" +
                    "2. Terdapat di admin CRM H1\n" +
                    "3. Minimal berisi keterangan : \n" +
                    "     a. no frame\n" +
                    "     b. nama konsumen\n" +
                    "     c. tanggal lahir\n" +
                    "     d. no hp/telp\n" +
                    "     e. alamat\n" +
                    "     f. agama\n" +
                    "     g. email\n" +
                    "     h. terima kasih\n" +
                    "     i. verifikasi data\n" +
                    "     j. reminder service (KPB 1)\n" +
                    "     k. no tlp dealer\n" +
                    "     l. tanggal follow up\n" +
                    "     m. status\n", 0))


        nosAudit.add(NosAudit("Follow Up Customer Database",
            "WA Broadcast dan SMS Broadcast software\n",
            "1. Berfungsi dengan baik\n" +
                    "2. cek simulasi pengiriman dan histori pengiriman terupdate minimal 1 hari yang lalu (di hari kerja)\n" +
                    "3. SMS hanya digunakan untuk konsumen tidak punya WA", 0))

        nosAudit.add(NosAudit("Follow Up Customer Database",
            "Komputer + Modem",
            "1. Komputer dedicated untuk kegiatan CRM\n" +
                    "2. Berfungsi dengan baik ", 0))



        nosAudit.add(NosAudit("Follow Up Customer Database",
            "Data Follow-Up Reminder Servis by SMS / WA (rutinCRM/exclude booking service)\n",
            "1. Data reminder SMS / WA untuk servis KPB 1 dari data penjualan\n" +
                    "2. Available berupa apps / file ms. Excel / hard copy\n" +
                    "3. Konten minimal berisi : \n" +
                    "     a. nama konsumen \n" +
                    "     b. no hp\n" +
                    "     c. pekerjaan\n" +
                    "     d. tipe & tahun motor sebelumnya\n" +
                    "     f. tanggal follow up SMS / WA \n" +
                    "     g. hasil follow up SMS / WA (misal terkirim/ tidak terkirim / booking servis/ servis kunjung / dll)\n" +
                    "     h. keterangan\n" +
                    "\n" +
                    "Note : proses reminder selalu update, minimal reminder WA / SMS dilakukan 1 hari yang lalu (di hari kerja) ", 0))



        nosAudit.add(NosAudit("Follow Up Customer Database",
            "Form monitoring / rekapan PIC CRM dari hasil follow up konsumen per SC / salesman",
            "1. Available berupa apps / file ms. Excel\n" +
                    "2. terdapat di PIC CRM Dealer\n" +
                    "3. Minimal berisi keterangan : \n" +
                    "     a. nama salespeople\n" +
                    "     b. jumlah data yang dibagikan ke salespeople\n" +
                    "     c. jumlah konsumen yang dapat dihubungi\n" +
                    "     d. jumlah konsumen yang RO\n" +
                    "     e. jumlah konsumen yang gagal\n" +
                    "     f. jumlah konsumen yang menunggu / belum ada keputusan\n" +
                    "     g. keterangan", 0))



        nosAudit.add(NosAudit("Follow Up Customer Database",
            "Report Niguri ke MD (full cycle)",
            "Tersedia report niguri secara lengkap di dealer (dealer information, niguri H1 to H2, niguri H2 to H1, detil konsumen RO)/ bulan (cek email)", 0))



        nosAudit.add(NosAudit("Follow Up Customer Database",
            "Form Rekap Data Pending\n",
            "1. Tersedia list konsumen yang pending dari hasil prospect  konsumen dan selalu update \n" +
                    "2. Terdapat di PIC CRM Dealer\n" +
                    "3. Konten minimal berisi : \n" +
                    "    a. nama\n" +
                    "    b. no hp\n" +
                    "    c. alamat\n" +
                    "    d. pekerjaan konsumen\n" +
                    "    e. progress hasil follow up\n" +
                    "    f. waktu rencana pembelian SMH (hot / medium / low)\n" +
                    "    g. keterangan", 0))



        nosAudit.add(NosAudit("Follow Up Customer Database",
            "SOP (Workflow) CRM Dealer",
            "1. Dealer memiliki SOP CRM (H1 ke H2 dan H2 ke H1) yang ditempel di ruangan kacab atau ruangan CRM\n" +
                    "2. SOP CRM Dealer dilengkapi dengan nama-nama PIC, admin CRM, atau jabatan yang bertanggung jawab terhadap proses CRM\n", 0))


        nosAudit.add(NosAudit("Follow Up Customer Database",
            "Panduan Follow Up ",
            "1. Script Follow Up (Call/ SMS / WA) : \n" +
                    "    a. Penawaran Pembelian Unit SMH\n" +
                    "    b. Thank you call\n" +
                    "    c. Informasi STNK / BPKB jadi\n" +
                    "    d. Reminder Service \n" +
                    "    e. Reminder booking service\n" +
                    "    f. Follow Up After Service\n" +
                    "2. Panduan Waktu Telepon (Golden time)\n" +
                    "3. Dapat berupa hard copy atau soft copy ", 0))



        nosAudit.add(NosAudit("Ruang Briefing Sales People",
            "Ruang Briefing Sales People",
            "1. Minimal luas ruangan 9m2 untuk 1 team terdiri dari 1 koordinator dan 8 salesman\n" +
                    "2. Kursi dan meja menyesuaikan jumlah sales people dalam  1 team\n" +
                    "3. Sebaiknya lokasi berada di lantai 2, tetapi diperbolehkan berlokasi di lantai 1 dengan syarat tidak terlihat dan tidak terdengar oleh konsumen\n" +
                    "4. Terdapat Sales Wallboard (berisi Sales per Sales People daily, weekly, monthly, CoE, Info sales program, info insentif dan unit indent))\n" +
                    "5. Terdapat Poster Product Comparisson (Start Semester 2- 2021)\n" +
                    "6. Terdapat Sales Talk Product Knowledge untuk refreshment\n" +
                    "7. Jika ruangan tidak satu atap dengan dealer, jarak paling jauh adalah 10 menit jalan kaki\n" +
                    "8. Jika jumlah tim >3 tim, briefing dapat dilakukan bergantian\n" +
                    "9. Terdapat jeda pembatas 1m antar kursi atau antar sales people\n" +
                    "10. Terdapat hand sanitizer \n", 0))



        nosAudit.add(NosAudit("Ruang Briefing Sales People",
            "TV LED ",
            "1. Ukuran min. 32 inch\n" +
                    "2. Dapat memutar Video NOS & Video Product Knowledge\n" +
                    "3. Dapat digunakan menampilkan Sales Wallboard", 0))


        nosAudit.add(NosAudit("Sales Tools",
            "My Hero Application",
            "1. Terdapat pada setiap smartphone sales force dan sudah berhasil login\n" +
                    "2. Digunakan dalam mempelajari product knowledge dan informasi lain terkait sepeda motor Honda serta sebagai alat bantu dalam menjelaskan produk Honda ke konsumen.", 0))


        nosAudit.add(NosAudit("Sales Tools",
            "Map Kerja Sales People",
            "1. Dimiliki setiap sales people (1 Sales People memiliki 1 set utuh)\n" +
                    "2. Berisi : Service Talk Flyer, Community Flyer, Flyer Acc & App, Flyer Customizing Part, Flyer Rekomendasi Bengkel Modifikasi, Pricelist, Sales Program List, Alat tulis", 0))


        nosAudit.add(NosAudit("Sales Tools",
            "Kalender Product",
            "a. Wajib dimiliki setiap Sales Counter dan digunakan ketika menjelaskan product ke konsumen\n" +
                    "b. Diletakkan di meja dealing table\n" +
                    "c. Desain Standard dari AHM (baik map maupun isinya)\n" +
                    "d. Untuk tipe - tipe baru, yang belum ada design dari AHM, Dealer menggunakan print out dari sales talk product knowledge", 0))


        nosAudit.add(NosAudit("NOS New Normal",
            "Rekap Harian Suhu Tubuh Karyawan",
            "1. Terletak di Ruang Kepala Cabang\n" +
                    "2. Diupdate setiap hari untuk seluruh karyawan dealer (Showroom + AHASS)", 0))


        nosAudit.add(NosAudit("NOS New Normal",
            "Daftar Suhu Tubuh Harian Sales People\n",
            "Terletak pada sekitar area pintu masuk showroom dan terlihat oleh konsumen", 0))



        return nosAudit
    }


    fun generateDataH1People(): List<NosAudit> {

        val nosAudit = ArrayList<NosAudit>()

        nosAudit.add(NosAudit("Kepala Cabang",
            " Jumlah ",
            "Dealer wajib memiliki 1 Kepala Cabang ", 0))

        nosAudit.add(NosAudit("Kepala Cabang",
            "Training",
            "Sudah melakukan training (dengan masa kerja) :\n" +
                    "a. Wing Sales People Training (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done : Sudah lulus training (Nilai >= 80)\n" +
                    "* Not Exist : Belum lulus Training", 0))
        nosAudit.add(NosAudit("Kepala Cabang",
            "Training",
            "b. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training", 0))

        nosAudit.add(NosAudit("Kepala Cabang",
            "Training",
            "c. Customer Service for leader 1 - Complaint Handling (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Kepala Cabang",
            "Training",
            "d. Customer Service for leader 2 (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Kepala Cabang",
            "Training",
            "e. TFT Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Kepala Cabang",
            "Training",
            "g. Parts Management Knowledge (Wajib dilakukan pada usia kerja 3 bulan atau lebih)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah ikut training\n" +
                    "* Not Exist :   Belum ikut training\n" +
                    "* N/A : Apabila masa kerja belum memenuhi", 0))
        nosAudit.add(NosAudit("Kepala Cabang",
            "Sales Tools",
            "Kepala Cabang memiliki sales tools berupa :\n" +
                    "Aplikasi MyHero (berhasil Login)\n" +
                    "\n" +
                    "Ketika menjelaskan konsumen diutamakan menggunakan Aplikasi MyHero (digital)", 0))

        nosAudit.add(NosAudit("Kepala Cabang",
            "Product Knowledge Test",
            "Dapat menjelaskan konsep produk, design produk, performance dan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA\n" +
                    "\n" +
                    "Alternatif Tipe Produk : Premium Product\n" +
                    "\n" +
                    "* Exist, Done : Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "\n" +
                    "* Not Exist  : Tidak Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A", 0))
        nosAudit.add(NosAudit("Supervisor",
            " Jumlah ",
            "1 Spv membawahi = 5 Coord SM\n" +
                    "Target  Jmlah Coord SM (A)\n" +
                    "Target  Jml Spv = A / 5\n" +
                    "\n" +
                    "* Exist, Done :  Jml Spv ≥ Target  Jml Spv \n" +
                    "* Exist, Not Done : Target  Jml Spv > 0 dan  Jml Spv < Target  Jml Spv\n" +
                    "* Not Done : Target  Jml Spv > 0 dan  Jml Spv = 0\n" +
                    "* N/A : Target  Jml Spv = 0 dan  Jml Spv = 0", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "Sudah melakukan training (dengan masa kerja) :\n" +
                    "a. Wing Sales People Training (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done : Sudah lulus training (Nilai >= 80)\n" +
                    "* Not Exist : Belum lulus Training", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "b. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training\n", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "c. Customer Service for leader 1 - Complaint Handling (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "d. Customer Service for leader 2 (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "e. TFT Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "f. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "Notes :\n" +
                    "* Apabila Target  Jml Spv = 0 dan  Jmlah Spv = 0, maka untuk penilaian diisii N/A\n" +
                    "* Apabila Target  Jml Spv > 0 dan  Jmlah Spv = 0, maka penilaian tetap dilakukan", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Product Knowledge Test",
            "Dapat menjelaskan konsep produk, design produk, performance dan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge.\n" +
                    "\n" +
                    "Alternatif Tipe Produk : Premium Product\n" +
                    "\n" +
                    "* Exist, Done : Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "\n" +
                    "* Not Exist  : Tidak Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "  Jumlah ",
            "1 Coordinator SC membawahi = 4 SC\n" +
                    "Target  Jmlah SC (A)\n" +
                    "Target  Jml Coordinator SC = A / 4\n" +
                    "* Jabatan ini adalah fungsi yang dapat dirangkap oleh Sales Counter\n" +
                    "\n" +
                    "* Exist, Done : Actual Jml Coord SC ≥ Target  Jml Coord SC\n" +
                    "* Exist, Not Done : Target Jml Coord SC > 0 dan Actual Jml Coord SC < Target Jml Coord SC \n" +
                    "* Not Done : Target Jml Coord SC > 0 dan Actual Jml Coord SC = 0\n" +
                    "* N/A : Target Jml Coord SC = 0 dan Actual Jml Coord SC = 0", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "a. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training\n", 0))
        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "b. Customer Service For FLP Training (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "c. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "d. Salesmanship Training  (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "e. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail)\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah dan ID Card Holder WSP dari AHM\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    "Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Sales Tools",
            "Memiliki sales tools berupa :\n" +
                    "1. Aplikasi MyHero (berhasil Login)\n" +
                    "2. Map Kerja Sales People yang berisi Service Talk Flyer, Community Flyer, Flyer Acc & App, Flyer Customizing Part, Flyer Rekomendasi Bengkel Modifikasi, Pricelist, Sales Program List, Alat tulis. \n" +
                    "3. Kalendar Produk\n" +
                    "\n" +
                    "Ketika menjelaskan ke konsumen di utamakan menggunakan aplikasi MyHero (digital) ", 0))
        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Product Knowledge Test",
            "1 Coordinator SM membawahi < = 10 SM\n" +
                    "Target  Jmlah SM (A)\n" +
                    "Target  Jml Coordinator SM = A / 10\n" +
                    "\n" +
                    "* Exist, Done :  Jml Coord SM ≥ Target  Jml Coord SM\n" +
                    "* Exist, Not Done : Target  Jml Coord SM > 0 dan  Jml Coord SM < Target  Jml Coord SM \n" +
                    "* Not Done : Target  Jml Coord SM > 0 dan  Jml Coord SM = 0\n" +
                    "* N/A : Target  Jml Coord SM = 0 dan  Jml Coord SM = 0", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
                    "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))

        nosAudit.add(NosAudit("",
            "",
            "", 0))
        nosAudit.add(NosAudit("",
            "",
            "", 0))

        return nosAudit
    }
}