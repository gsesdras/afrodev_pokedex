package com.goiz.pokedex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        val next = findViewById<Button>(R.id.next)

        next.setOnClickListener {
            val name = findViewById<EditText>(R.id.yourNameInput).text
            val nameString = name.toString()
            if(nameString == ""){
                Toast.makeText(applicationContext, R.string.toast_digite_seu_nome, Toast.LENGTH_SHORT).show()
            }else {
                val intent = Intent(this, HomePageList::class.java)
                intent.putExtra("Username", nameString)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
