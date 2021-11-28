package ml.basiclogin

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

import java.sql.*
import java.sql.DriverManager
import java.net.URL
import java.net.URLEncoder
import java.net.HttpURLConnection
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        btn_login.setOnClickListener() {
            val user = User(name.text.toString(), pass.text.toString())

            val parm = mapOf("username" to user.username,
                             "password" to user.password)

            val pref = getSharedPreferences("logged", Context.MODE_PRIVATE)

            val loggedIn = pref.getBoolean("loggedIn", false)

            val json = ConnectDB.sendGetRequest("login", parm)

            if(loggedIn){
                val intent = Intent(this, Welcome::class.java)
                startActivity(intent)
            }else{
                if(json.getBoolean("succes")){
                    //makeDialog(this, "Login suscesfully!")

                    val preferences = getSharedPreferences("logged", Context.MODE_PRIVATE).edit()
                    preferences.putBoolean("loggedIn", true)
                    preferences.apply()

                    val intent = Intent(this, Welcome::class.java)
                    startActivity(intent)
                }else{
                    val arr = json.getJSONObject("error")
                    makeDialog(this, arr.getString("log"))
                    println(arr.getString("sql"))
                }
            }
        }
    }
}

fun makeDialog(ctx : Context, msg : String) {
    val dialog = AlertDialog.Builder(ctx)
    dialog.setMessage(msg)
    dialog.show()
}

