package my.id.phyton06.footballleague.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail_match.*
import my.id.phyton06.footballleague.R
import my.id.phyton06.footballleague.adapter.DetailMatchAdapter
import my.id.phyton06.footballleague.common.LoadingAlert
import my.id.phyton06.footballleague.common.RxBaseActivity
import my.id.phyton06.footballleague.common.Utils
import my.id.phyton06.footballleague.model.DataDetailMatch
import my.id.phyton06.footballleague.model.DataMatch
import my.id.phyton06.footballleague.service.MatchService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit

@RequiresApi(Build.VERSION_CODES.O)
class DetailMatchActivity : RxBaseActivity() {

    private var loading: Dialog? = null
    private var itemsMatches: MutableList<DataMatch> = mutableListOf()
    private lateinit var resultMatch: DataMatch
    private var listDetailMatch = ArrayList<DataDetailMatch>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        loading = LoadingAlert.progressDialog(this, this)
        match_list_detail.layoutManager = LinearLayoutManager(this)

        initDataMatch()

    }

    @SuppressLint("SetTextI18n")
    private fun initDataMatch() {
        loading?.show()
        subscriptions.add(provideService()
            .detailMatch(Utils.idEvent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    resp ->
                loading?.dismiss()
                itemsMatches = resp.events
                Utils.resultMatch = resp.events[0]
                resultMatch = Utils.resultMatch!!

                homeScoreTv.text = ""+ resultMatch.intHomeScore
                awayScoreTv.text = ""+ resultMatch.intAwayScore
                homeNameIv.text = ""+ resultMatch.strHomeTeam
                awayNameIv.text = ""+ resultMatch.strAwayTeam
                titleTv.text = ""+ resultMatch.strStatus
                date.text = ""+ resultMatch.dateEvent.substring(8, resultMatch.dateEvent.length)+ "-" +  resultMatch.dateEvent.substring(5,7) + "-" + resultMatch.dateEvent.substring(0,4)
                time.text = ""+convertDate(resultMatch.strTimestamp)

                 listDetailMatch.addAll(DataDetailMatch.getData())
                 match_list_detail.adapter = DetailMatchAdapter(this, listDetailMatch)

            }) {
                    error ->
                loading?.dismiss()
                val builder = AlertDialog.Builder(this)
                builder.setMessage("DETAIL MATCH : "+error.localizedMessage)
                    .setPositiveButton(
                        "OK"
                    ) { dialog, which ->
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE -> {
                                dialog.dismiss()
                            }
                        }
                    }.setCancelable(false).show()

            }
        )
    }

    private fun convertDate(timeStamp: String):String{
        return try {
            val odt: OffsetDateTime = OffsetDateTime.parse(timeStamp)
            val zonedDateTime: ZonedDateTime = odt.atZoneSameInstant(ZoneId.of("Asia/Jakarta"))
            zonedDateTime.toString().substring(11,16)
        } catch(e : NullPointerException){
            ""
        }
    }

    private fun provideService(): MatchService {
        val clientBuilder: OkHttpClient.Builder = Utils.buildClient()
        val retrofit = Retrofit.Builder()
            .baseUrl(Utils.ENDPOINT)
            .client(
                clientBuilder
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
        return retrofit.create(MatchService::class.java)
    }
}