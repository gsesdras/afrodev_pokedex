package com.goiz.pokedex

import android.annotation.SuppressLint
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class About : AppCompatActivity() {
    private val txtDescription: TextView by lazy { findViewById(R.id.description) }

    @SuppressLint("InlinedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            txtDescription.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }



    }
    fun handleBackPressed(view: View){
        super.onBackPressed()
    }
}