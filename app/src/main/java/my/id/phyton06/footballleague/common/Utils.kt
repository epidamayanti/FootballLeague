package my.id.phyton06.footballleague.common

import my.id.phyton06.footballleague.model.DataMatch
import my.id.phyton06.footballleague.model.ResponseMatch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class Utils {

    companion object {
        const val ENDPOINT = "https://www.thesportsdb.com/api/v1/json/1/"

        //const val LIGA_ENDPOINT = "lookupleague.php?id={idLeague}"
        const val NEXT_MATCH_ENDPOINT = "eventsnextleague.php?"
        const val PREV_MATCH_ENDPOINT = "eventspastleague.php?"
        const val MATCH_DETAIL_ENDPOINT = "lookupevent.php?"
        const val SEARCH_MATCH_ENDPOINT = "searchevents.php?"

        var idLeague = 0
        var idEvent  = 0
        var resultMatch: DataMatch? = null

        fun buildClient(): OkHttpClient.Builder {
            val clientBuilder = OkHttpClient.Builder()
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)
                .connectTimeout(5 , TimeUnit.MINUTES)
            return clientBuilder
        }
    }
}