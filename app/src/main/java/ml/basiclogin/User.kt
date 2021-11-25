package ml.basiclogin

import java.sql.SQLDataException
import java.sql.SQLSyntaxErrorException

class User(
    var username : String,
    var password : String,

) {
    fun loginUser() : Boolean {
        ConnectDB.connection()

        var stmt = "SELECT * FROM Boliches.Cuentas " +
                   "WHERE username='${username}'"

        var rs = ConnectDB.conn!!.createStatement().executeQuery(stmt)

        if (!rs.next()) {
            println("el usuario NO existe")
        } else {
            stmt = "SELECT * FROM Boliches.Cuentas " +
                    "WHERE password='${password}' AND" +
                    "      username='${username}'"

            rs = ConnectDB.conn!!.createStatement().executeQuery(stmt)

            if (!rs.next()) {
                println("Contraseña incorrecta")
            } else {
                return true
            }
        }

        return false
    }


    fun registerUser() : Boolean{

        if(username == "" || password == ""){
            println("el nombre o la contraseña no pueden estar vacios");
            return false
        }

        ConnectDB.connection()

        var stmt = "SELECT * FROM Boliches.Cuentas " +
                    "WHERE username='${username}'"

        val rs = ConnectDB.conn!!.createStatement().executeQuery(stmt)

        if (rs.next()) {
            println("el usuario YA existe")
            return false
        }

        stmt = "INSERT INTO Boliches.Cuentas " +
                "(`username`, `password`) " +
                "VALUES ('${username}', '${password}')"

        println(stmt)
        try{
            ConnectDB.conn!!.createStatement().execute(stmt)
            return true
        }catch (e:SQLSyntaxErrorException){
            e.printStackTrace()
        }

        return false
    }
}