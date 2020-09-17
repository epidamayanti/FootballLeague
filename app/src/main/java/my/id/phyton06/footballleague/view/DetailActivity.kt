package my.id.phyton06.footballleague.view

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import my.id.phyton06.footballleague.R.color.colorLine
import my.id.phyton06.footballleague.common.Utils

class DetailActivity : AppCompatActivity() {

    private lateinit var detailUI: DetailUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUI = DetailUI()
        detailUI.setContentView(this)
    }

    override fun onResume() {
        super.onResume()

        val intent = intent
        val name = intent.getStringExtra("name")
        val image = intent.getIntExtra("image", 0)
        val desc = intent.getStringExtra("desc")

        detailUI.tvTittle.text = name
        detailUI.tvDesc.text = desc
        image.let { Picasso.get().load(image).into(detailUI.image) }

        detailUI.btnMatch.setOnClickListener {
            val i = Intent(this, MatchActivity::class.java)
            Utils.idLeague = intent.getIntExtra("id", 0)
            startActivity(i)
        }
    }

    class DetailUI : AnkoComponent<DetailActivity> {
        lateinit var image: ImageView
        lateinit var tvTittle: TextView
        lateinit var tvDesc: TextView
        lateinit var btnMatch: Button

        @SuppressLint("SetTextI18n")
        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
            scrollView {
                backgroundColor = Color.rgb(149,228,65)
                verticalLayout{
                    padding = dip(16)
                    image = imageView{
                    }.lparams(width = wrapContent, height = dip(230)){
                        topMargin = dip(5)
                        bottomMargin = dip(8)
                    }

                    cardView {
                        layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT).apply {
                            leftMargin = dip(8)
                            rightMargin = dip(8)
                            bottomMargin = dip(5)

                        }
                        backgroundColor = Color.WHITE
                        radius = dip(4).toFloat()
                        cardElevation = dip(2).toFloat()

                        verticalLayout {
                            tvTittle = textView {
                                textColor = Color.BLACK
                                textSize = 18f
                                typeface = Typeface.DEFAULT_BOLD
                            }.lparams(width = matchParent, height = wrapContent){
                                bottomMargin = dip(8)
                                topMargin = dip(8)
                                leftMargin = dip(8)
                                rightMargin = dip(16)
                            }
                            view {
                                backgroundColor =  ContextCompat.getColor(context, colorLine)
                            }.lparams(width = matchParent, height = dip(2)){
                            }

                            tvDesc = textView {
                                textColor = Color.BLACK
                                textSize = 14f
                                typeface = Typeface.SANS_SERIF
                            }.lparams(width = matchParent, height = wrapContent){
                                bottomMargin = dip(8)
                                topMargin = dip(8)
                                leftMargin = dip(8)
                                rightMargin = dip(16)
                            }
                        }

                    }.lparams(width= matchParent, height = wrapContent)

                    btnMatch = button {
                        textColor = Color.WHITE
                        textSize = 14f
                        text = "MATCH SCHEDULE"
                        backgroundColor = Color.rgb(30,112,33)

                    }.lparams(width = matchParent, height = wrapContent){
                        bottomMargin = dip(8)
                        topMargin = dip(5)
                        leftMargin = dip(8)
                        rightMargin = dip(8)
                    }
                }
            }
        }
    }
}