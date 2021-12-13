package ml.basiclogin

import android.content.Context
import android.content.Intent

object User {
    var id = ""
    var username = ""
    var password = ""
    var email = ""

    fun login(ctx : Context){
        val parm = mapOf("username" to username,
                         "password" to password)

        val json = ConnectDB.sendGetRequest("login", parm)

        if(json.getBoolean("succes")){
            setLoggedState(true, ctx)

            val intent = Intent(ctx, Welcome::class.java)
            ctx.startActivity(intent)
        }else{
            val arr = json.getJSONObject("error")
            makeDialog(ctx, arr.getString("log"))
            println(arr.getString("sql"))
        }
    }

    fun register(ctx : Context){
        val parm = mapOf("username" to username,
                         "password" to password,
                         "email" to email)

        val json = ConnectDB.sendGetRequest("register", parm)
        println(json)

        if(json.getBoolean("succes")){
            makeDialog(ctx, "Se envio un correo a $email para que verifiques tu cuenta");


            id = json.getString("id")

            val intent = Intent(ctx, Verify::class.java)
            ctx.startActivity(intent)
        }else{
            val arr = json.getJSONObject("error")
            makeDialog(ctx, arr.getString("log"))
            println(arr.getString("sql"))
        }
    }

    fun verify(ctx: Context, code : String) {
        val parm = mapOf("id" to id,
                         "code" to code)

        val json = ConnectDB.sendGetRequest("verify", parm)

        if(json.getBoolean("succes")){
            setLoggedState(true, ctx)

            val intent = Intent(ctx, Welcome::class.java)
            ctx.startActivity(intent)
        }else {
            val arr = json.getJSONObject("error")
            makeDialog(ctx, arr.getString("log"))
            println(arr.getString("sql"))
        }
    }

    fun getLoggedState(ctx : Context) : Boolean{
        val pref = ctx.getSharedPreferences("logged", Context.MODE_PRIVATE)
        return pref.getBoolean("loggedIn", false)
    }

    fun setLoggedState(state : Boolean, ctx : Context) {
        val preferences = ctx.getSharedPreferences("logged", Context.MODE_PRIVATE).edit()
        preferences.putBoolean("loggedIn", state)
        preferences.apply()
    }
}