package com.itroad.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.itroad.movies.ui.movies.MoviesActivity

class MainActivity : AppCompatActivity() {
    var SPLASH_DISPLAY_LENGTH = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            startActivity(Intent(this@MainActivity, MoviesActivity::class.java))
            finish()

        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}