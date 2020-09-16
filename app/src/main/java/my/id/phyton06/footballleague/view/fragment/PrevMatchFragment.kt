package my.id.phyton06.footballleague.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.schedulers.Schedulers
import my.id.phyton06.footballleague.R
import my.id.phyton06.footballleague.common.RxBaseFragment
import my.id.phyton06.footballleague.common.Utils
import my.id.phyton06.footballleague.service.MatchService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class PrevMatchFragment : RxBaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prev_match, container, false)
    }

    private fun provideService(): MatchService {
        val clientBuilder: OkHttpClient.Builder = Utils.buildClient()
        val retrofit = Retrofit.Builder()
            .baseUrl(Utils.ENDPOINT)
            .client(clientBuilder
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
        return retrofit.create(MatchService::class.java)
    }


}