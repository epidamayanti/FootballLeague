package my.id.phyton06.footballleague.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import my.id.phyton06.footballleague.view.fragment.NextMatchFragment
import my.id.phyton06.footballleague.view.fragment.PrevMatchFragment
import my.id.phyton06.footballleague.R
import my.id.phyton06.footballleague.adapter.ViewPagerAdapter
import my.id.phyton06.footballleague.common.LoadingAlert
import my.id.phyton06.footballleague.common.RxBaseActivity
import my.id.phyton06.footballleague.common.Utils
import my.id.phyton06.footballleague.service.MatchService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MatchActivity : RxBaseActivity() {

    var bottomNavigationView: BottomNavigationView? = null

    //This is viewPager
    private var viewPager: ViewPager? = null

    private var nextMatchFragment: NextMatchFragment? = null
    private var prevMatchFragment: PrevMatchFragment? = null
    private var prevMenuItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_activity)

        //Initializing viewPager
        viewPager = findViewById(R.id.viewpager)

        //Initializing the bottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView!!.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_prev -> viewPager!!.currentItem = 0
                R.id.action_next -> viewPager!!.currentItem = 1
            }
            false
        }
        viewPager?.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if (prevMenuItem != null) {
                    prevMenuItem!!.isChecked = false
                } else {
                    bottomNavigationView!!.menu.getItem(0).isChecked = false
                }
                Log.d("page", "onPageSelected: $position")
                bottomNavigationView!!.menu.getItem(position).isChecked = true
                prevMenuItem = bottomNavigationView!!.menu.getItem(position)
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })

        setupViewPager(viewPager!!)

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        prevMatchFragment = PrevMatchFragment()
        nextMatchFragment = NextMatchFragment()
        adapter.addFragment(prevMatchFragment!!)
        adapter.addFragment(nextMatchFragment!!)
        viewPager.adapter = adapter
    }




}