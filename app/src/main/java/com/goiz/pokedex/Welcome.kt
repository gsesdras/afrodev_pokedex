package com.goiz.pokedex

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity() {
    var handler: Handler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try{
            this.supportActionBar?.hide();
        }catch (NullPointerException: Error){}

        setContentView(R.layout.activity_welcome);

        val nameIntent = intent.getStringExtra("Name")
        val username = "$nameIntent!"
        name.text = username

        var counterStart = 3
        counterNumber.text = counterStart.toString()

        fun counter(){
            handler = Handler()
            handler!!.postDelayed({
                if(counterStart > 1){
                    counterStart -= 1
                    counterNumber.text = counterStart.toString()
                    counter()
                }else{
                    val intent = Intent(this, HomePageList::class.java)
                    startActivity(intent)
                }
            }, 1000)
        }
        counter()
    }

    override fun onBackPressed() {
        val toast = Toast.makeText(this, "Por favor, aguarde!", Toast.LENGTH_SHORT )
        toast.show()
    }
}
