package com.example.newproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.example.newproject.Adapter.ImagePagerAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.lang.System.exit
import java.util.*

class Dashboard : AppCompatActivity() {


    private lateinit var viewPager: ViewPager
    private lateinit var adapter: ImagePagerAdapter
    private val images = listOf(R.drawable.food, R.drawable.nonvg, R.drawable.img)
    private var currentPage = 0
    private var timer: Timer? = null
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar


    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }

    private lateinit var profilePhotoImageView: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        drawerLayout = findViewById(R.id.drawer)
        navView = findViewById(R.id.navmenu)
        toolbar = findViewById(R.id.toolbar)
        // Rest of your code...
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()




        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.home_menu -> {
                       val i = Intent(this@Dashboard,itemkids::class.java)
                    startActivity(i)
                    true
                }
                R.id.about_menu -> {

                    val i = Intent(this@Dashboard,Profile::class.java)
                    startActivity(i)
                    true
                }
                R.id.share -> {
                     val i = Intent (this@Dashboard,itemkids::class.java)
                    startActivity(i)
                    true
                }
                R.id.changepassword -> {

                    true
                }
                R.id.logout_menu -> {


                    drawerLayout.closeDrawer(GravityCompat.START)
                    logout()

                    true
                }
                R.id.home_menu -> {

                    true
                }
                R.id.exit_menu -> {

                    drawerLayout.closeDrawer(GravityCompat.START)
                    exit()


                    true
                }
                // Add more menu items as needed
                else -> false
            }
        }


        viewPager = findViewById<ViewPager>(R.id.viewPager)
        adapter = ImagePagerAdapter(images, this)
        viewPager.adapter = adapter

        val handler = Handler()
        val update = Runnable {
            if (currentPage == images.size) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
        }

        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 2000, 2000) // Change image every 2 seconds
    }

    private fun logout() {
        TODO("Not yet implemented")
    }

    private fun exit() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit?")
        builder.setMessage("Are you sure want to exit?")
            .setCancelable(false)
            .setPositiveButton("Yes") {_,_  ->
                moveTaskToBack(true)
                android.os.Process.killProcess(android.os.Process.myPid())
                System.exit(1)
                Toast.makeText(applicationContext, "Exit", Toast.LENGTH_SHORT).show()
                startActivity(Intent(applicationContext, LoginPage::class.java))
                finish()
            }
            .setNegativeButton("No") { dialogInterface, _ ->
                dialogInterface.cancel()
            }

        val alertDialog = builder.create()
        alertDialog.show()
    }



    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}