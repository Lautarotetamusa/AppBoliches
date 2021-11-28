package ml.basiclogin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_login_screen.setOnClickListener(){

            val pref = getSharedPreferences("logged", Context.MODE_PRIVATE)
            val loggedIn = pref.getBoolean("loggedIn", false)

            if(loggedIn){
                val intent = Intent(this, Welcome::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
        }
    }
}