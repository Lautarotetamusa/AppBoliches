package ml.basiclogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

fun main(){

    val user = User("migue", "migue123")

    val con = user.registerUser()

    println(con)
}


class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener() {

            //txtErr.alpha = 0f
            //val user = User(name.text.toString(), pass.text.toString())
            
            ConnectDB.connection()
            println(ConnectDB.conn)


            /*val con = ConnectDB.validateAcc(user)

            if(con){
                val intent = Intent(this, Main::class.java)

                startActivity(intent)
            }else{
                txtErr.alpha = 1f
                name.setText("")
                pass.setText("")
            }*/
        }
    }
}