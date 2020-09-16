package my.id.phyton06.footballleague.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.match_list.view.*
import my.id.phyton06.footballleague.R.layout.match_list
import my.id.phyton06.footballleague.model.ResponseMatch

class PrevMatchAdapter(private val context: Context, private val items: List<ResponseMatch>, private val listener: (ResponseMatch) -> Unit)
    : RecyclerView.Adapter<PrevMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(match_list, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bindItem(items: ResponseMatch, listener: (ResponseMatch) -> Unit) {
            containerView.dateScheduleTv.text = items.dateEvent
            containerView.homeNameTv.text = items.strHomeTeam
            containerView.awayNameTv.text = items.strAwayTeam
            containerView.homeScoreTv.text = ""+items.intHomeScore
            containerView.awayScoreTv.text = ""+items.intAwayScore

        }
    }

}

