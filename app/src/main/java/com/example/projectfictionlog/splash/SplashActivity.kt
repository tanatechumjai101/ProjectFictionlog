package com.example.projectfictionlog.splash

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.projectfictionlog.R
import com.example.projectfictionlog.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private lateinit var smallToLarge: Animation
    private var REQUEST_PERMISSION_READ_AND_WRITE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        smallToLarge = AnimationUtils.loadAnimation(
            this, R.anim.small_to_large
        )
        startAnim()

        smallToLarge.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationEnd(p0: Animation?) {
                openPageMain()
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })

        Toast.makeText(baseContext, "Please connect to the internet.", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            finishAffinity()
        }, 2000)

    }

    private fun startAnim() {
        ivLogo.startAnimation(smallToLarge)
    }



    private fun openPageMain() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


}

