package com.goiz.pokedex.view

import android.annotation.SuppressLint
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.goiz.pokedex.R

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
}