package com.goiz.pokedex

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

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
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }
        }.start()
    }
}