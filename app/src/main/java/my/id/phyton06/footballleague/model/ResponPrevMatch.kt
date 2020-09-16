package my.id.phyton06.footballleague.model

data class ResponPrevMatch (
    val idEvent : String,
    val idAPIfootball : String,
    val strEvent : String,
    val idLeague : String,
    val strLeague : String,
    val strSeason : String,
    val strDescriptionEN  : String,
    val strHomeTeam  : String,
    val strAwayTeam  : String,
    val intHomeScore : Int,
    val intRound : Int,
    val intAwayScore : Int,
    val strHomeGoalDetails : String,
    val strHomeRedCards	 : String,
    val strHomeYellowCards : String,
    val strHomeLineupGoalkeeper : String,
    val strHomeLineupDefense : String,
    val strHomeLineupMidfield : String,
    val strHomeLineupForward : String,
    val strHomeLineupSubstitutes : String,
    val strHomeFormation : String,
    val strAwayRedCards : String,
    val strAwayYellowCards : String,
    val strAwayGoalDetails : String,
    val strAwayLineupGoalkeeper	 : String,
    val strAwayLineupDefense : String,
    val strAwayLineupMidfield : String,
    val strAwayLineupForward : String,
    val strAwayLineupSubstitutes : String,
    val strAwayFormation : String,
    val intHomeShots : Int,
    val intAwayShots : Int,
    val strTimestamp : String,
    val dateEvent : String,
    val strDate	 : String,
    val strTime	: String,
    val strTimeLocal: String,
    val strTVStation : String,
    val idHomeTeam	: Int,
    val idAwayTeam	: Int,
    val strResult	: String,
    val strVenue	: String,
    val strCountry	: String,
    val strCity	: String,
    val strPoster	 : String,
    val strFanart	 : String,
    val strThumb	: String,
    val strBanner	 : String,
    val strMap	 : String,
    val strTweet1	: String,
    val strTweet2	: String,
    val strTweet3	: String,
    val strVideo	: String,
    val strStatus	:String
)