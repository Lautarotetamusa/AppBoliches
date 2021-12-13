package ml.basiclogin

import android.app.AlertDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        btn_login.setOnClickListener() {
            User.login(this)
        }
    }
}

fun makeDialog(ctx : Context, msg : String) {
    val dialog = AlertDialog.Builder(ctx)
    dialog.setMessage(msg)
    dialog.show()
}

