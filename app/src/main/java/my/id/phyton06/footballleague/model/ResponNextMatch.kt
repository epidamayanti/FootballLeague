package my.id.phyton06.footballleague.model

data class ResponNextMatch (
    val idEvent	: String,
    val idAPIfootball : String,
    val strEvent : String,
    val idLeague : String,
    val strLeague : String,
    val strSeason : String,
    val strHomeTeam : String,
    val strAwayTeam : String,
    val intRound	: Int,
    val strTimestamp : String,
    val dateEventLocal : String,
    val idHomeTeam : String,
    val idAwayTeam : String,
    val strVenue : String,
    val strCountry : String,
    val strStatus : String
)