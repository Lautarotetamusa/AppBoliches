package ml.basiclogin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_welcome.*

class Welcome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        btn_logout.setOnClickListener(){
            val preferences = getSharedPreferences("logged", Context.MODE_PRIVATE).edit()
            preferences.putBoolean("loggedIn", false)
            preferences.apply()

            makeDialog(this,"sesion is closed")

            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }
    }
}