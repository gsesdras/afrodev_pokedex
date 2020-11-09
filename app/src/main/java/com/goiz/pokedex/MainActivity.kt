package com.goiz.pokedex

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        try{
            this.supportActionBar?.hide();
        }catch (NullPointerException: Error){}

        next.setOnClickListener {
            val name = findViewById<EditText>(R.id.yourNameInput).text
            val nameString = name.toString()
            if(nameString == ""){
                val toast = Toast.makeText(applicationContext, "Digite seu nome!", Toast.LENGTH_SHORT)
                toast.show()
            }else {
                val intent = Intent(this, Welcome::class.java)
                intent.putExtra("Name", nameString)
                startActivity(intent)
            }
        }
    }
}
