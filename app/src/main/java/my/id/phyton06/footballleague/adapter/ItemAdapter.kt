package my.id.phyton06.footballleague.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.view.*
import my.id.phyton06.footballleague.model.DataItem
import  my.id.phyton06.footballleague.R.layout.item_list

class ItemAdapter(private val context: Context, private val items: List<DataItem>, private val listener: (DataItem) -> Unit)
    : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(item_list, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    override fun getItemCount(): Int = items.size


    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(items: DataItem, listener: (DataItem) -> Unit) {
            containerView.itemNameTextView.text = items.name
            items.image?.let { Picasso.get().load(it).into(containerView.itemImageView) }
            containerView.setOnClickListener { listener(items) }
        }
    }

}

