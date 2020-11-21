package com.goiz.pokedex.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.goiz.pokedex.R


class MainActivity : AppCompatActivity() {

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val usernameCached = getUsername()
        usernameCached?.let{ goListPage(usernameCached) }

        setContentView(R.layout.activity_main)


        val next = findViewById<Button>(R.id.next)

        next.setOnClickListener {
            val name = findViewById<EditText>(R.id.yourNameInput).text
            val nameString = name.toString().trim()
            if (nameString == "") {
                Toast.makeText(
                    applicationContext,
                    R.string.toast_digite_seu_nome,
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                saveUsername(nameString)
                goListPage(nameString)
            }
        }
    }

    private fun getUsername(): String? {
        val sharedPref = this.getSharedPreferences("LoginPreferences",Context.MODE_PRIVATE) ?: return null
        return sharedPref.getString("username", null)
    }

    private fun saveUsername(username: String) {
        val sharedPref = this.getSharedPreferences("LoginPreferences",Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("username", username)
            commit()
        }
    }

    private fun goListPage(username: String) {
        val intent = Intent(this, HomePageList::class.java)
        intent.putExtra("Username", username)
        startActivity(intent)
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}
