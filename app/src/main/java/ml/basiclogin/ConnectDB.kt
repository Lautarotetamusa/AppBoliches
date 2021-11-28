package ml.basiclogin

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.sql.*

/**
 * Program to list databases in MySQL using Kotlin
 */

object ConnectDB {
    private val servername = "holaoficial.ml"

    fun sendGetRequest(method : String, parms : Map<String, String>) : JSONObject{

        var reqParam = ""

        parms.forEach(){
            reqParam += URLEncoder.encode(it.key, "UTF-8") + "=" + URLEncoder.encode(it.value, "UTF-8") + "&"
        }
        reqParam.substring(0, reqParam.length - 1)

        val mURL = URL("http://"+servername+"/DBmanager/"+method+".php?"+reqParam)

        println(mURL)

        with(mURL.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            println("URL : $url")
            println("Response Code : $responseCode")

            BufferedReader(InputStreamReader(inputStream)).use {
                var response : String = ""

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response += inputLine
                    inputLine = it.readLine()
                }
                it.close()

                println(response)

                return JSONObject(response)
            }
        }
    }
}

