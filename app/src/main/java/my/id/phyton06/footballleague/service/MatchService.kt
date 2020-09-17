package my.id.phyton06.footballleague.service

import my.id.phyton06.footballleague.common.Utils
import my.id.phyton06.footballleague.model.DataMatch
import io.reactivex.Observable
import my.id.phyton06.footballleague.model.ResponseMatch
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchService {
    @POST(Utils.PREV_MATCH_ENDPOINT)
    fun previousMatch(@Query ("id") idLeague: Int): Observable<ResponseMatch>

    @POST (Utils.NEXT_MATCH_ENDPOINT)
    fun nextMatch(@Query ("id") idLeague: Int): Observable<ResponseMatch>

    @POST (Utils.MATCH_DETAIL_ENDPOINT)
    fun detailMatch(@Query ("id") idEvent: Int): Observable<ResponseMatch>
    @POST (Utils.SEARCH_MATCH_ENDPOINT)
    fun searchMatch(@Path ("query") query: String): Observable<MutableList<DataMatch>>

}