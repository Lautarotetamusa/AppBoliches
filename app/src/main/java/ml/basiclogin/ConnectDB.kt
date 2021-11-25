package ml.basiclogin

import java.sql.*

/**
 * Program to list databases in MySQL using Kotlin
 */

fun main(){
    ConnectDB.connection()
    //ConnectDB.execQuery("INSERT into cuentas.instagram " +
    //        "values('mari', 'pelado')");

    //ConnectDB.queryRows("cuentas", "instagram")

    val teti = User("teti", "Lautaro123.")

    //SQL injection: ' OR '1' = '1


    val pass = ConnectDB.validateAcc(teti)
    println(pass)
}

object ConnectDB {
    var conn: Connection? = null
    private val username = "teti" // provide the username
    private val password = "Lautaro123." // provide the corresponding password
    private val ipdir = "holaoficial.ml" // domain redir to my ip


    fun connection () {
        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://$ipdir",
                username,
                password
            )
            println("connection ok")
            //conn!!.setAutoCommit(false);
        }catch (e: SQLException){
            println("err connect")
            e.printStackTrace()
        }
    }

    fun execQuery(query : String) : ResultSet?{
        if(conn != null){
            try{
                val rs = conn!!.createStatement().executeQuery(query)
                println("query OK")
                return rs
            }catch (e: SQLException) {
                e.printStackTrace()
            }
        }
        println("not connected! ")
        return null
    }

    fun queryRows(schema : String, table : String) {
        val sql = "SELECT * FROM $schema.$table"
        val rs = conn!!.createStatement().executeQuery(sql)
        while (rs.next()) {
            println("USER: ${rs.getString("username")}\t" +
                    "PASS: ${rs.getString("password")}\t")
        }
    }

    fun validateAcc(u : User) : Boolean{
        val stmt = "SELECT * FROM Boliches.Cuentas " +
                   "WHERE username = '${u.username}' AND "+
                         "password = '${u.password}'"

        println(stmt)

        try {
            val rs = conn!!.createStatement().executeQuery(stmt)

            return rs.next()
        }catch (e:SQLException){
            e.printStackTrace()
        }
        return false
    }
}

