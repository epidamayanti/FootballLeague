package my.id.phyton06.footballleague.model

data class ResponseMatch (
    val events : MutableList<DataMatch>,
    val event : MutableList<DataMatch>
)