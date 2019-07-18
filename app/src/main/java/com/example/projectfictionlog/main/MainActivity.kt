package com.example.projectfictionlog.main

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.projectfictionlog.R
import com.example.projectfictionlog.screenOne.Fragment.ScreenOneFragment
import com.example.projectfictionlog.screenTwo.Fragment.ScreenTwoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var screenOne: ScreenOneFragment
    private lateinit var screenTwo: ScreenTwoFragment


    private var REQUEST_PERMISSION_READ_AND_WRITE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenOne = ScreenOneFragment.newInstance()
        screenTwo = ScreenTwoFragment.newInstance()

        if(checkInternet()){
            if(checkPermission()){ supportFragmentManager.beginTransaction()
                    .replace(R.id.frameMain, screenOne)
                    .commit()
            navMain.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)




            }else {
                requestPermission(REQUEST_PERMISSION_READ_AND_WRITE)
            }
        }else {
            Toast.makeText(baseContext, "Please connect to the internet.", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                finishAffinity()
            }, 2000)
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.one -> {
                supportFragmentManager.beginTransaction().replace(R.id.frameMain, screenOne)
                    .addToBackStack(null)
                    .commit()
            }
            R.id.two -> {
                supportFragmentManager.beginTransaction().replace(R.id.frameMain, screenTwo)
                    .addToBackStack(null)
                    .commit()
            }

        }
        true
    }



    private fun requestPermission(requestCode: Int) {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), requestCode
        )
    }

    private fun checkInternet(): Boolean {
        val checkInternet = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = checkInternet.activeNetworkInfo
        return !(networkInfo == null || !networkInfo.isConnected)
    }

    private fun checkPermission(): Boolean {

        return (ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            this@MainActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED)
    }
}
