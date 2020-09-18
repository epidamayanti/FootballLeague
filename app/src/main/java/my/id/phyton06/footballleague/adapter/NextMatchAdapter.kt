package my.id.phyton06.footballleague.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.match_next_list.view.*
import my.id.phyton06.footballleague.R.layout.match_next_list
import my.id.phyton06.footballleague.model.DataMatch
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Suppress("DEPRECATION")
class NextMatchAdapter(private val context: Context, private val items: List<DataMatch>, private val listener: (DataMatch) -> Unit)
    : RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(match_next_list, parent, false))


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    @RequiresApi(Build.VERSION_CODES.O)
    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bindItem(items: DataMatch, listener: (DataMatch) -> Unit) {
            if (items.strSport.contains("Soccer")) {
                val date = items.dateEvent.substring(8, items.dateEvent.length) + "-" + items.dateEvent.substring(5, 7) + "-" + items.dateEvent.substring(0, 4)
                containerView.dateScheduleTv.text = date
                containerView.homeNameTv.text = items.strHomeTeam
                containerView.awayNameTv.text = items.strAwayTeam
                containerView.timeScheduleTv.text = "" + convertDate(items.strTimestamp)
                containerView.setOnClickListener { listener(items) }
            }
        }

        private fun convertDate(timeStamp: String):String{
            return try {
                val odt: OffsetDateTime = OffsetDateTime.parse(timeStamp)
                val zonedDateTime: ZonedDateTime = odt.atZoneSameInstant(ZoneId.of("Asia/Jakarta"))
                zonedDateTime.toString().substring(11,16)
            } catch(e : NullPointerException){
                ""
            }
        }
    }

}

