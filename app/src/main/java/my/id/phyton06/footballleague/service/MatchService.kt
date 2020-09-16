package my.id.phyton06.footballleague.service

import android.database.Observable
import my.id.phyton06.footballleague.common.Utils
import my.id.phyton06.footballleague.model.ResponseMatch
import retrofit2.http.POST
import retrofit2.http.Path

interface MatchService {
    @POST (Utils.PREV_MATCH_ENDPOINT)
    fun previousMatch(@Path ("idLeague") idLeague: String): Observable<MutableList<ResponseMatch>>

    @POST (Utils.NEXT_MATCH_ENDPOINT)
    fun nextMatch(@Path ("idLeague") idLeague: String): Observable<MutableList<ResponseMatch>>

    @POST (Utils.MATCH_DETAIL_ENDPOINT)
    fun detailMatch(@Path ("idEvent") idEvent: String): Observable<MutableList<ResponseMatch>>

    @POST (Utils.SEARCH_MATCH_ENDPOINT)
    fun searchMatch(@Path ("query") query: String): Observable<MutableList<ResponseMatch>>

}