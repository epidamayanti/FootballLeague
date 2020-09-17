package my.id.phyton06.footballleague.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.match_prev_list.view.*
import my.id.phyton06.footballleague.R.layout.match_prev_list
import my.id.phyton06.footballleague.model.DataItem
import my.id.phyton06.footballleague.model.DataMatch

class PrevMatchAdapter(private val context: Context, private val items: List<DataMatch>, private val listener: (DataMatch) -> Unit)
    : RecyclerView.Adapter<PrevMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(match_prev_list, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bindItem(items: DataMatch, listener: (DataMatch) -> Unit) {
            val date = items.dateEvent.substring(8, items.dateEvent.length)+ "-" +  items.dateEvent.substring(5,7) + "-" + items.dateEvent.substring(0,4)
            containerView.dateScheduleTv.text = date
            containerView.homeNameTv.text = items.strHomeTeam
            containerView.awayNameTv.text = items.strAwayTeam
            containerView.homeScoreTv.text = ""+items.intHomeScore
            containerView.awayScoreTv.text = ""+items.intAwayScore
            containerView.setOnClickListener { listener(items) }
        }

    }

}

