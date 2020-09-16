package my.id.phyton06.footballleague.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import my.id.phyton06.footballleague.R
import my.id.phyton06.footballleague.adapter.ItemAdapter
import my.id.phyton06.footballleague.model.DataItem
import my.id.phyton06.footballleague.R.array.club_detail
import my.id.phyton06.footballleague.R.array.club_name
import my.id.phyton06.footballleague.R.array.club_image
import my.id.phyton06.footballleague.R.array.club_id

class MainActivity : AppCompatActivity() {

    private var items: MutableList<DataItem> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()

        club_list.layoutManager =  GridLayoutManager(applicationContext, 2)
        club_list.adapter = ItemAdapter(this, items){
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra("id", it.id)
            i.putExtra("name",  it.name)
            i.putExtra("image", it.image)
            i.putExtra("desc", it.desc)
            startActivity(i)

            val toast = Toast.makeText(applicationContext, "Anda memilih "+it.name, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun initData(){
        val id = resources.getIntArray(club_id)
        val name = resources.getStringArray(club_name)
        val detail = resources.getStringArray(club_detail)
        val image = resources.obtainTypedArray(club_image)
        items.clear()
        for (i in name.indices) {
            items.add(
                DataItem(
                    id[i],
                    name[i],
                    image.getResourceId(i, 0),
                    detail[i]
                )
            )
        }
        image.recycle()
    }
}