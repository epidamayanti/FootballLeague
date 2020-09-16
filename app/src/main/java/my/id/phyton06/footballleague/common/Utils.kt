package my.id.phyton06.footballleague.common

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class Utils {

    companion object {
        val ENDPOINT = "https://www.thesportsdb.com/api/v1/json/1/"

        const val LIGA_ENDPOINT = "lookupleague.php?id={idLeague}"
        const val NEXT_MATCH_ENDPOINT = "eventsnextleague.php?id={idLeague} "
        const val PREV_MATCH_ENDPOINT = "eventspastleague.php?id={idLeague}"
        const val MATCH_DETAIL_ENDPOINT = "lookupevent.php?id={idEvent}"
        const val SEARCH_MATCH_ENDPOINT = "searchevents.php?e={query}"

        fun buildClient(): OkHttpClient.Builder {
            val clientBuilder = OkHttpClient.Builder()
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            clientBuilder.addInterceptor(loggingInterceptor)
                .connectTimeout(5 , TimeUnit.MINUTES)
            return clientBuilder
        }
    }
}