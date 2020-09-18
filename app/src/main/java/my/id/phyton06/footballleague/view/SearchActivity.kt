package my.id.phyton06.footballleague.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_search.*
import my.id.phyton06.footballleague.R
import my.id.phyton06.footballleague.adapter.NextMatchAdapter
import my.id.phyton06.footballleague.common.LoadingAlert
import my.id.phyton06.footballleague.common.RxBaseActivity
import my.id.phyton06.footballleague.common.Utils
import my.id.phyton06.footballleague.model.DataMatch
import my.id.phyton06.footballleague.service.MatchService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SearchActivity : RxBaseActivity() {

    private var loading: Dialog? = null
    private var itemsMatches: MutableList<DataMatch> = mutableListOf()
    private var query: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        loading = LoadingAlert.progressDialog(this, this)
        match_result.layoutManager = LinearLayoutManager(this)

        bt_back.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        bt_search.setOnClickListener {
            query = et_search.text.toString()
            initDataResult(query)
        }

    }

    private fun initDataResult(query: String) {
        loading?.show()
        subscriptions.add(provideService()
            .searchMatch(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    resp ->
                        loading?.dismiss()
                        itemsMatches = resp.event
                        lyt_no_result.visibility = View.GONE
                        match_result.visibility = View.VISIBLE
                        match_result.adapter = NextMatchAdapter(this, itemsMatches) {
                            Utils.idEvent = it.idEvent.toInt()
                            startActivity(Intent(this, DetailMatchActivity::class.java))
                        }
            }
            ) { error ->
                loading?.dismiss()
                if(error.cause.toString() == "null"){
                    lyt_no_result.visibility = View.VISIBLE
                    match_result.visibility = View.GONE
                }
                else {
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("SEARCH MATCH : "+error.localizedMessage)
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
            }
        )
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