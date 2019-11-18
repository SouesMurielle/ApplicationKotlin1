package com.soues.projet2

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import java.net.HttpURLConnection

class OkHttpUtils {

    private val client = OkHttpClient()

    fun sendGetOkHttpRequest(url: String): String? {
        Log.w("TAG_", url)

        //Création de la requête
        val request = Request.Builder().url(url).build()

        val response = client.newCall(request).execute()

        if (response.code != HttpURLConnection.HTTP_OK) {
            throw Exception("Reponse du serveur incorecte : " + response.code)
        } else {
            return response.body?.string()
        }

        //Exécution de la  requête
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//            }
//            override fun onResponse(call: Call, response: Response) {
//                if (response.body != null) {
//                    log(response.body!!.string())
//                    return println(response.body?.string())
//                }
//            }
//        }
//        )
    }
}
