package my.id.phyton06.footballleague.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_detail_match.view.*
import my.id.phyton06.footballleague.R.layout.list_detail_match
import my.id.phyton06.footballleague.model.DataDetailMatch

class DetailMatchAdapter(private val context: Context, private val items: List<DataDetailMatch>)
    : RecyclerView.Adapter<DetailMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(list_detail_match, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        @SuppressLint("SetTextI18n")
        fun bindItem(items: DataDetailMatch) {
            containerView.typeTv.text = items.nameResult
            containerView.awayNameIv.text = items.awayResult
            containerView.homeNameIv.text = items.homeResult
        }
    }

}

