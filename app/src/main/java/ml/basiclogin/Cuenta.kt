package ml.basiclogin

import java.sql.SQLSyntaxErrorException

fun login(username : String,  password : String, table : String) : Boolean {

    ConnectDB.connection()

    var stmt = "SELECT * FROM Boliches.${table} " +
            "WHERE username='${username}'"

    var rs = ConnectDB.conn!!.createStatement().executeQuery(stmt)

    if (!rs.next()) {
        println("el usuario NO existe")
    } else {
        stmt = "SELECT * FROM Boliches.${table} " +
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

fun register(username : String,  password : String, table : String) : Boolean{

    if(username == "" || password == ""){
        println("el nombre o la contraseña no pueden estar vacios");
        return false
    }

    ConnectDB.connection()

    var stmt = "SELECT * FROM Boliches.${table} " +
            "WHERE username='${username}'"

    val rs = ConnectDB.conn!!.createStatement().executeQuery(stmt)

    if (rs.next()) {
        println("el usuario YA existe")
        return false
    }

    stmt = "INSERT INTO Boliches.${table} " +
            "(`username`, `password`) " +
            "VALUES ('${username}', '${password}')"

    println(stmt)
    try{
        ConnectDB.conn!!.createStatement().execute(stmt)
        return true
    }catch (e: SQLSyntaxErrorException){
        e.printStackTrace()
    }

    return false
}
