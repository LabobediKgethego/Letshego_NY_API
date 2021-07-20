package com.example.letshego_ny_api

/*Author Kgethego Labobedi
* kgethegolabobedi@gmail.com
* 20-Jul-2021
* */

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.letshego_ny_api.MyAdapter
import com.example.letshego_ny_api.Articles
import android.os.Bundle
import android.view.View
import com.example.letshego_ny_api.R
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.example.letshego_ny_api.ControllerSupportService
import com.example.letshego_ny_api.ControllerSupportService.VolleyCallBack
import org.json.JSONObject
import org.json.JSONArray
import org.json.JSONException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AinActivity : AppCompatActivity() {
    var recyclerView: RecyclerView? = null
    var header: TextView? = null
    private var adapter: MyAdapter? = null
    private var list: ArrayList<Articles>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            val c = Calendar.getInstance().time
            val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            val formattedDate = df.format(c)
            val header = findViewById<View>(R.id.header) as TextView
            header.text = " \n$formattedDate"
            recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
            recyclerView!!.setHasFixedSize(true)
            recyclerView!!.layoutManager = LinearLayoutManager(this)
            list = ArrayList()
            adapter = MyAdapter(this, list!!)
            recyclerView!!.adapter = adapter

            unLoadHere()
        } catch (e: Exception) {
            Toast.makeText(this@AinActivity, "Error:$e", Toast.LENGTH_LONG).show()
        }
    }

    private fun unLoadHere() {
        //Get the response JSOObject
        //Interface allows us to wait for results from a JSONRequest
        val controllerSupportService = ControllerSupportService()
        controllerSupportService.downLoadDataVolley(this, object : VolleyCallBack {
            override fun onSuccess(jsonObject: JSONObject?) {
                //If JSONObject results are returned...
                try {
                    //Get JSONArray of results from the JSONObject
                    val jsonArr = jsonObject!!.getJSONArray("results")
                    for (i in 0 until jsonArr.length()) {
                        //Populate JSONArray with JSONObject
                        val jsonObject1 = jsonArr.getJSONObject(i)

                        //Get specific Strings for each JSONObject in a JSONArray
                        val source = jsonObject1.getString("source")
                        val title = jsonObject1.getString("title")
                        val asset_id = jsonObject1.getString("asset_id")
                        val publish_date = jsonObject1.getString("published_date")
                        val updated = jsonObject1.getString("updated")
                        val section = jsonObject1.getString("section")
                        val subsection = jsonObject1.getString("abstract")
                        val byline = jsonObject1.getString("byline")
                        val type = jsonObject1.getString("type")

                        //Create Object Articles by calling the constructor and populating it with the Strings from JSONObjects above
                        val articles = Articles(
                            title,
                            asset_id,
                            source,
                            publish_date,
                            updated,
                            section,
                            subsection,
                            byline,
                            type
                        )
                        //Populate the listArray List with articles from above.
                        list!!.add(articles)
                    }
                    adapter!!.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        })
    }
}