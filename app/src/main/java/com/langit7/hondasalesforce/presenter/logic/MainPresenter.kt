package com.langit7.hondasalesforce.presenter.logic

import com.google.gson.Gson
import com.langit7.hondasalesforce.API.api
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.model.teamreport.*
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.presenter.viewInterface.SliderInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class MainPresenter {
    private var disposable: Disposable? = null
    var base: BaseActivity
    var api: api

    constructor(b: BaseActivity, ap: api) {
        this.base = b
        this.api = ap
    }

    fun getSlider(cb: SliderInterface) {
        disposable = api.getSlider("0")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onGetSliderSuccess(res.data)
            }, { error ->
                cb.onGetSliderFailed(error.message!!)
            })


    }


    fun getNotif(cb: DataListInterface<notif>) {

        disposable = api.getNotif(base.getUser()!!.id, "50", "1", "0")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onGetDataSuccess(res.data)
            }, { error ->
                cb.onGetDataFailed(error.message!!)
            })


    }

    fun readNotif(notifid: String, cb: ObjectResponseInterface<notif>) {


        disposable = api.readNotif(notifid, "1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if (res != null) {
                    cb.onSuccess(res)
                }
            }, { error ->
                cb.onFailed(error.message!!)
            })


    }

    fun readAllNotif(cb: ObjectResponseInterface<baseresponse<String>>) {


        disposable = api.readAllNotif(base.getUser()!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if (res != null) {
                    cb.onSuccess(res)
                }
            }, { error ->
                error.printStackTrace()
                cb.onFailed(error.message!!)
            })


    }


    fun deleteNotif(notifid: String, cb: ObjectResponseInterface<baseresponse<notif>>) {


        disposable = api.deleteNotif(notifid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onSuccess(res)

            }, { error ->
                cb.onFailed(error.message!!)
//                Log.e("asdasd", error.message)
            })


    }

    fun getuser(cb: ObjectResponseInterface<baseresponse<user>>) {


        disposable = api.getUser(base.getUser()!!.id, "1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onSuccess(res)
            }, { error ->
                cb.onFailed(error.message!!)
            })


    }


    fun savePoint(cat: String, trans: String, cb: ObjectResponseInterface<history>) {

//       cat:
//        1 => read_article (membaca artikel)
//        2 => watch_video (menonton video)
//        3 => read_sales_talk (membaca sales talk)
//        4 => read_apparel (membaca apparel)
//        5 => read_product (membaca pengetahuan produk)
//        6 => read_general_knowledge (membaca pengetahuan umum)
//        7 => read_accessories (membaca aksesoris)
//        8 => answer_quiz (menyelesaikan kuis)
//        9 => answer_survey (menyelesaikan survey)
//        10 => share_prospect_collection (pengumpulan cdb)

        disposable = api.saveHistory(cat, base.getUser()!!.id, trans)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onSuccess(res)


                disposable = api.getUser(base.getUser()!!.id, "1")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ res ->
                        function.savePreverence(base, "user", Gson().toJson(res.data))
                    }, { error ->

                    })


            }, { error ->
                cb.onFailed(error.message!!)
            })


    }

    fun saveCounter(cat: String, trans: String, cb: ObjectResponseInterface<history>) {

//       cat:
//        ('1', 'Article'),
//        ('2', 'Video'),
//        ('3', 'Product'),
//        ('4', 'General Knowledge'),
//        ('5', 'Quiz'),
//        ('6', 'Survey'),
//        ('7', 'Prospect Collection'),

        disposable = api.saveCounter(cat, base.getUser()!!.id, trans)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onSuccess(res)


                disposable = api.getUser(base.getUser()!!.id, "1")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ res ->
                        function.savePreverence(base, "user", Gson().toJson(res.data))
                    }, { error ->

                    })


            }, { error ->
                cb.onFailed(error.message!!)
            })


    }

    fun getAds(cb: DataListInterface<ads>) {


        disposable = api.getAds("1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if (res.data.size > 0)
                    cb.onGetDataSuccess(res.data)
                else
                    cb.onGetDataFailed(res.message)
            }, { error ->
                cb.onGetDataFailed(error.message!!)
            })


    }


    fun getVersion(cb: ObjectResponseInterface<version>) {


        disposable = api.getVersion("1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if (res.data != null)
                    cb.onSuccess(res.data)
                else if (res.message.length > 0)
                    cb.onFailed(res.message)


            }, { error ->
                error.printStackTrace()
                cb.onFailed(error.message!!)
            })


    }

    fun submitRating(rate: String, cb: ObjectResponseInterface<rating>) {


        disposable = api.submitRating(rate, base.getUser()!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if (res.isSuccessful)
                    cb.onSuccess(res.body()!!)
                else
                    cb.onFailed(res.errorBody().toString())


            }, { error ->
                cb.onFailed(error.message!!)
            })


    }

    fun logout() {

        try {
            if (base.getUser() != null) {
                disposable = api.logout(base.getUser()!!.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ res ->
                    }, { error ->
                    })

            }
        } catch (e: Exception) {
        }
    }


    fun updateAvatar(medal: String, cb: ObjectResponseInterface<user>) {

        disposable = api.updateAvatar(base.getUser()!!.id, medal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if (res != null)
                    cb.onSuccess(res)
                else
                    cb.onFailed("Oops, Someting Wrong")


            }, { error ->
                cb.onFailed(error.message!!)
            })


    }

    fun updateProfile(
        img: File? = null,
        vaccine1: String? = null,
        vaccine2: String? = null,
        reason1: String? = null,
        reason2: String? = null,
        cb : ObjectResponseInterface<user>
    ) {


        var req: MultipartBody.Part? = null

        if (img != null) {
            var req1 = RequestBody.create(MediaType.parse("image/*"), img)
            req = MultipartBody.Part.createFormData("image", img.name, req1)
        }



        disposable = api.editprofile(
            RequestBody.create(MediaType.parse("text/plain"), base.getUser()!!.id),
            vaccine1?.let{RequestBody.create(MediaType.parse("text/plain"), vaccine1)},
            vaccine2?.let{RequestBody.create(MediaType.parse("text/plain"), vaccine2)},
            reason1?.let{RequestBody.create(MediaType.parse("text/plain"), reason1)},
            reason2?.let{RequestBody.create(MediaType.parse("text/plain"), reason2)},
            req
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { res ->
                        cb.onSuccess(res)
                },
                { error -> cb.onFailed(error.message!!) })
    }

    fun getVaccineReason(cb: DataListInterface<vaccinereason>) {

        disposable = api.getVaccineReason("1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if (res.data.size > 0)
                    cb.onGetDataSuccess(res.data)
                else
                    cb.onGetDataFailed(res.message)
            }, { error ->
                cb.onGetDataFailed(error.message!!)
            })
    }

    fun getFrequent(mainDealer:String, date:String, cb: ObjectResponseInterface<baseresponse<frequent>>) {
        disposable = api.getFrequent("1", mainDealer, date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if (res.data != null)
                    cb.onSuccess(res)
                else
                    cb.onFailed(res.message)
            }, { error ->
                cb.onFailed(error.message!!)
            })
    }


    fun getPartisipantDetail(xs:String,
                       main_dealer:String,
                       category:String,
                       month:String,
                       year:String,
                             semester : String,

                       cb: ObjectResponseInterface<baseresponse<PartisipantDetail>>) {
        disposable = api.getPartisipantDetail(xs, main_dealer, category, month, year, semester)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onSuccess(res)
            }, { error ->
                cb.onFailed(error.message!!)
            })
    }

    fun getPartisipant(xs:String,
                       nitems:Int,
                       page:Int,
                       main_dealer:String,
                       category:String,
                       month:String,
                       year:String,
                       category_position:String,
                       is_participant:String,
                       cb: ObjectResponseInterface<baseresponse<List<partisipant>>>) {
        disposable = api.getPartisipant(xs, nitems, page, main_dealer, category, month, year, category_position, is_participant)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onSuccess(res)
            }, { error ->
                cb.onFailed(error.message!!)
            })
    }

    fun getPartisipantQuizVerifikasi(xs:String,
                       nitems:Int,
                       page:Int,
                       main_dealer:String,
                       category:String,
                                     month:String,

                       year:String,
                       category_position:String,
                       is_participant:String,
                                     semester:Int,

                                     cb: ObjectResponseInterface<baseresponse<List<partisipant>>>) {
        disposable = api.getPartisipantQuizVerifikasi(xs, nitems, page, main_dealer, category, month, year, category_position, is_participant, semester)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onSuccess(res)
            }, { error ->
                cb.onFailed(error.message!!)
            })
    }

    fun getPartisipantQuizAndMonitor(xs:String,
                       nitems:Int,
                       page:Int,
                       main_dealer:String,
                       category:String,
                       month:String,
                       year:String,
                       category_position:String,
                       is_participant:String,
                                     semester: String,
                       cb: ObjectResponseInterface<baseresponse<List<PartisipantQuiz>>>) {
        disposable = api.getPartisipantQuizAndMonitor(xs, nitems, page, main_dealer, category, month, year, category_position, is_participant, semester)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onSuccess(res)
            }, { error ->
                cb.onFailed(error.message!!)
            })
    }

    fun getPartisipantQualified(xs:String,
                                     nitems:Int,
                                     page:Int,
                                     main_dealer:String,
                                     category:String,
                                     semester:String,
                                     year:String,
                                     category_position:String,
                                     cb: ObjectResponseInterface<baseresponse<List<ListParticipantQualified>>>) {
        disposable = api.getPartisipantQualified(xs, nitems, page, main_dealer, category, semester, year,category_position)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onSuccess(res)
            }, { error ->
                cb.onFailed(error.message!!)
            })
    }



    fun getMonitoringQualified(xs:String,
                        main_dealer:String,
                        semester:String,
                        year:String,
                        cb: ObjectResponseInterface<baseresponse<MonitoringQualified>>) {
        disposable = api.getMonitoringQualified(xs, main_dealer, semester, year)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onSuccess(res)
            }, { error ->
                cb.onFailed(error.message!!)
            })
    }

    fun getFrequentUser(xs:String,
                       nitems:Int,
                       page:Int,
                       main_dealer:String,
                       date:String,
                       category:String,
                       cb: ObjectResponseInterface<baseresponse<List<FrequentUser>>>) {
        disposable = api.getFrequentUser(xs, nitems, page, main_dealer, date, category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onSuccess(res)
            }, { error ->
                cb.onFailed(error.message!!)
            })
    }

    fun getQuizAwal(mainDealer:String, cb: ObjectResponseInterface<baseresponse<List<quizawal>>>) {
        disposable = api.getQuizAwal("1", mainDealer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                cb.onSuccess(res)
            }, { error ->
                cb.onFailed(error.message!!)
            })
    }
}