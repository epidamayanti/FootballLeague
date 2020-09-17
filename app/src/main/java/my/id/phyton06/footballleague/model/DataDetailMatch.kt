package my.id.phyton06.footballleague.model

import my.id.phyton06.footballleague.common.Utils
import java.io.Serializable

data class DataDetailMatch(
    val nameResult: String,
    val homeResult: String,
    val awayResult: String
) : Serializable {
    companion object {
        fun getData(): List<DataDetailMatch> {
            val resultList = ArrayList<DataDetailMatch>()
            resultList.clear()
            resultList.add(DataDetailMatch("Goal Details", ""+ (Utils.resultMatch?.strHomeGoalDetails?: "-"), ""+ (Utils.resultMatch?.strAwayGoalDetails ?: "-")))
            resultList.add(DataDetailMatch("Shots", ""+ Utils.resultMatch?.intHomeShots, ""+ Utils.resultMatch?.intHomeShots))
            resultList.add(DataDetailMatch("Red Cards", ""+ Utils.resultMatch?.strHomeRedCards, ""+ Utils.resultMatch?.strAwayRedCards))
            resultList.add(DataDetailMatch("Yellow Cards", ""+ Utils.resultMatch?.strHomeYellowCards, ""+ Utils.resultMatch?.strAwayYellowCards))
            resultList.add(DataDetailMatch("Lineup Goalkeeper", ""+ Utils.resultMatch?.strHomeLineupGoalkeeper, ""+ Utils.resultMatch?.strAwayLineupGoalkeeper))
            resultList.add(DataDetailMatch("Lineup Defense", ""+ Utils.resultMatch?.strHomeLineupDefense, ""+ Utils.resultMatch?.strAwayLineupDefense))
            resultList.add(DataDetailMatch("Lineup Midfield", ""+ Utils.resultMatch?.strHomeLineupMidfield, ""+ Utils.resultMatch?.strAwayLineupMidfield))
            resultList.add(DataDetailMatch("Lineup Forward", ""+ Utils.resultMatch?.strHomeLineupForward, ""+ Utils.resultMatch?.strAwayLineupForward))
            resultList.add(DataDetailMatch("Lineup Substitutes", ""+ Utils.resultMatch?.strHomeLineupSubstitutes, ""+ Utils.resultMatch?.strAwayLineupSubstitutes))
            resultList.add(DataDetailMatch("Lineup Formation", ""+ Utils.resultMatch?.strHomeFormation, ""+ Utils.resultMatch?.strAwayFormation))

            return getData()
        }
    }
}