package my.id.phyton06.footballleague.view.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_prev_match.*
import my.id.phyton06.footballleague.R
import my.id.phyton06.footballleague.adapter.PrevMatchAdapter
import my.id.phyton06.footballleague.common.LoadingAlert
import my.id.phyton06.footballleague.common.RxBaseFragment
import my.id.phyton06.footballleague.common.Utils
import my.id.phyton06.footballleague.model.DataMatch
import my.id.phyton06.footballleague.service.MatchService
import my.id.phyton06.footballleague.view.DetailMatchActivity
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PrevMatchFragment : RxBaseFragment() {

    private var loading: Dialog? = null
    private var itemsMatches: MutableList<DataMatch> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prev_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loading = LoadingAlert.progressDialog(this.context!!, this.requireActivity())
        match_list.layoutManager = LinearLayoutManager(this.context)

        initDataPrevMatch()
    }

    private fun initDataPrevMatch() {
        loading?.show()
        subscriptions.add(provideService()
            .previousMatch(Utils.idLeague)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response ->
                    loading?.dismiss()
                    itemsMatches = response.events
                    event_tv.visibility = View.GONE
                    match_list.visibility = View.VISIBLE
                    match_list.adapter = PrevMatchAdapter(this.requireContext(), itemsMatches) {
                        Utils.idEvent = it.idEvent.toInt()
                        startActivity(Intent(activity, DetailMatchActivity::class.java))
                    }
            }) {
                    error ->
                loading?.dismiss()
                if(error.cause.toString() == "null"){
                    event_tv.visibility = View.VISIBLE
                    match_list.visibility = View.GONE
                }
                else {
                    val builder = AlertDialog.Builder(this.context)
                    builder.setMessage("PREV MATCH : "+error.localizedMessage)
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