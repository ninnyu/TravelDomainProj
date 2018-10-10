package com.example.potatopaloozac.traveldomainproj

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var t1: Thread = Thread() {
            Thread.sleep(2500)

            var i: Intent = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }

        t1.start()
    }
}
