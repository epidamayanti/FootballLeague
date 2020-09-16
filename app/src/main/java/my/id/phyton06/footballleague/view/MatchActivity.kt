package my.id.phyton06.footballleague.view

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import my.id.phyton06.footballleague.view.fragment.NextMatchFragment
import my.id.phyton06.footballleague.view.fragment.PrevMatchFragment
import my.id.phyton06.footballleague.R
import my.id.phyton06.footballleague.adapter.ViewPagerAdapter


class MatchActivity : AppCompatActivity() {

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
        viewPager = findViewById(R.id.viewpager) as ViewPager

        //Initializing the bottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView!!.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_prev -> viewPager!!.setCurrentItem(0)
                R.id.action_next -> viewPager!!.setCurrentItem(1)
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

        /*  //Disable ViewPager Swipe

       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        */
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