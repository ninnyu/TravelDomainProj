package com.example.potatopaloozac.traveldomainproj

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.potatopaloozac.traveldomainproj.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var s: String = "1.  Java\n" +
                "2.  Kotlin\n" +
                "3.  MVP Design Pattern\n" +
                "4.  Singleton Design Pattern\n" +
                "5.  Splash Screen\n" +
                "6.  Single Sign-On using Firebase\n" +
                "7.  AnyChart library\n" +
                "8.  CalendarView\n" +
                "9.  Spinner\n" +
                "10. Retrofit2\n" +
                "11. AlertDialog\n" +
                "12. ProgressDialog\n" +
                "13. Google Maps\n" +
                "14. RecyclerView\n" +
                "15. CardView\n" +
                "16. RxJava (validation)\n" +
                "17. PayPal\n" +
                "18. QR Code Generator\n" +
                "19. Email\n" +
                "20. SQLite Database\n" +
                "21. SharedPreferences\n" +
                "22. Parcelable\n" +
                ""

        var s2: String = "1.  NinnYu and Andy\n" +
                "2.  NinnYu and Andy\n" +
                "3.  NinnYu and Andy\n" +
                "4.  NinnYu and Andy\n\n" +
                "5.  NinnYu\n" +
                "6.  NinnYu\n\n" +
                "7.  Andy\n" +
                "8.  NinnYu\n" +
                "9.  NinnYu\n" +
                "10. NinnYu and Andy\n" +
                "11. NinnYu\n" +
                "12. NinnYu\n" +
                "13. Andy\n" +
                "14. NinnYu and Andy\n" +
                "15. NinnYu and Andy\n" +
                "16. NinnYu\n" +
                "17. NinnYu\n" +
                "18. NinnYu\n" +
                "19. NinnYu\n" +
                "20. Andy\n" +
                "21. NinnYu and Andy\n" +
                "22. NinnYu\n" +
                ""

        tv_techsUsed.text = s
        tv_whodidwhat.text = s2

        bt_startDemo.setOnClickListener {
            var i: Intent = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}
