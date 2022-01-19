package com.langit7.hondasalesforce.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.quizhistorydetail
import com.langit7.hondasalesforce.model.quizresponse
import com.langit7.hondasalesforce.presenter.adapter.QuizHistoryDetailListAdapter
import com.langit7.hondasalesforce.presenter.logic.QuizPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import kotlinx.android.synthetic.main.activity_quiz_history_detail.*

class QuizHistoryDetailActivity : BaseActivity() {

    lateinit var adp:QuizHistoryDetailListAdapter
    var lsHistory=ArrayList<quizhistorydetail>()
    lateinit var mQuiz:quizresponse
    lateinit var presenter:QuizPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_history_detail)


        mQuiz= intent.getSerializableExtra("qid") as quizresponse


        if(mQuiz==null)
            finish()


        presenter= QuizPresenter(this,APIServices)

        showLoadingDialog()
        presenter.getDetailHistoryQuiz(mQuiz.id,object :DataListInterface<quizhistorydetail>{
            override fun onGetDataSuccess(res: List<quizhistorydetail>) {
                dismisLoadingDialog()


                lsHistory.clear()
                lsHistory.addAll(res)
                adp.notifyDataSetChanged()

//                Log.e("num quies",lsHistory.size.toString())

            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
//                Log.e("error",msg)
            }
        })


        tname.setText(getUser()!!.firstName+" "+ getUser()!!.lastName)
        tid.setText(getUser()!!.id)
        tquiz.setText(mQuiz.quiz.title)
        tdate.setText(mQuiz.createdDate)
        tscore.setText(function.formatNumber(getUser()!!.profileUser.totalPoint.replace(".0","")))
        tlevel.setText(getUser()!!.profileUser.desc_medal_type)
        imgavatar.setImageResource(function.getMedalImageLarge(getUser()!!.profileUser.chosen_medal_type))

        adp= QuizHistoryDetailListAdapter(lsHistory,ctx)


        recycler_view.layoutManager=
            LinearLayoutManager(ctx)
        recycler_view.adapter=adp

    }
}
