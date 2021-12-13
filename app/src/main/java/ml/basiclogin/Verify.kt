package ml.basiclogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_verify.*

class Verify : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        btn_verificar.setOnClickListener(){
            User.verify(this, code.text.toString())
        }
    }
}