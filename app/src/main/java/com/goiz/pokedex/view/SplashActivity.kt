package com.goiz.pokedex.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.goiz.pokedex.R

class SplashActivity : AppCompatActivity() {

    private val icSplash: ImageView by lazy { findViewById(R.id.icSplashScreen) }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_screen)

        icSplash.animate().apply {
            duration = 1500
            rotationYBy(360f)
        }.withEndAction {
            icSplash.animate().apply {
                duration = 1500
                rotationYBy(360f)
            }.withEndAction {
                startApp()
            }
        }.start()
    }

    fun startApp(){
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
    }
}