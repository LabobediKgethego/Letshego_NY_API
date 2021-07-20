package com.example.letshego_ny_api

/*Author Kgethego Labobedi
* kgethegolabobedi@gmail.com
* 20-Jul-2021
* */
import org.json.JSONObject
import com.example.letshego_ny_api.ControllerSupportService.VolleyCallBack
import com.android.volley.toolbox.JsonObjectRequest
import android.widget.Toast
import org.json.JSONException
import com.android.volley.VolleyError
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class ControllerSupportService {
    var context: Context? = null

    //Create a volleyCallBack interface to wait for results of type JSONObject
    interface VolleyCallBack {
        fun onSuccess(jsonObject: JSONObject?)
    }

    //Create a method to hit NY Times Most Popular Articles
    fun downLoadDataVolley(context: Context?, volleyCallBack: VolleyCallBack) {
        //URL for the NY Times Most Popular Articles
        val url =
            " https://api.nytimes.com/svc/mostpopular/v2/viewed/7.json?api-key=nuTrNlVHBDeifidtvRzikZ4Kw2Jce0m8"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                try {
                    val jsonObject = JSONObject(response.toString())
                    //Cast response to JSONObject
                    Toast.makeText(context, "Success", Toast.LENGTH_LONG).show()
                    volleyCallBack.onSuccess(jsonObject)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }) { error ->
            Log.d(ContentValues.TAG, "onErrorResponse: $error")
            Toast.makeText(context, "Error:$error", Toast.LENGTH_LONG).show()

        }
        val requestQueue = Volley.newRequestQueue(context)
        requestQueue.add(jsonObjectRequest)

    }
}