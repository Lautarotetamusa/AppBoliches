package ml.basiclogin

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.name
import kotlinx.android.synthetic.main.activity_login.pass
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        btn_register.setOnClickListener() {
            User.username = name.text.toString()
            User.password = pass.text.toString()
            User.email    = email.text.toString()

            User.register(this)
        }
    }
}