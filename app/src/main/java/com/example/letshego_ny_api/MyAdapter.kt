package com.example.letshego_ny_api

/*Author Kgethego Labobedi
* kgethegolabobedi@gmail.com
* 20-Jul-2021
* */
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.letshego_ny_api.MyAdapter.MyViewHolder
import java.util.*

class MyAdapter(var context: Context, var articlesList: ArrayList<Articles>) :
    RecyclerView.Adapter<MyViewHolder>() {

    var something = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //Create a viewHolder and inflate that returns view
        val v = LayoutInflater.from(context).inflate(R.layout.item, parent, false)


        v.setOnClickListener {
            //Returning wrong position
            //Toast.makeText(context, "Position: $viewType", Toast.LENGTH_LONG).show()
            // showDialog(something);
        }
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //bind array with object Articles
        val articles = articlesList[position]
        holder.id_title.text = articles.title
        holder.id_source.text = articles.source
        holder.id_publishDate.text = articles.publish_date
        something = position
        holder.id_title.tag = position
        holder.id_title.setOnClickListener { v -> // Also having Async task
            //What to do when pressed
            val index = v.tag as Int
            showDialog(index)
        }
    }

    override fun getItemCount(): Int {
        //Whats the size of the array?
        return articlesList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //Instantiate XML resources
        var id_title: TextView
        var id_source: TextView
        var id_publishDate: TextView
        // var cardView: CardView

        init {
            id_title = itemView.findViewById(R.id.id_title)
            id_source = itemView.findViewById(R.id.id_source)
            id_publishDate = itemView.findViewById(R.id.id_publishDate)
            //id_publishDate = itemView.findViewById(R.id.id_publishDate)
        }
    }

    private fun showDialog(position: Int) {
        //Show details Method.
        val articles = articlesList[position]
        val builder = AlertDialog.Builder(context)
        builder.setTitle("""
    ${articles.title}
    """.trimIndent())
        builder.setMessage(
            """
    Published: ${articles.publish_date}
    Section: ${articles.section}
    Asset ID:${articles.asset_id}
    By line:${articles.byline}
    Abstract:    ${articles.subsection}
    """.trimIndent()
        )
        //builder.setIcon(R.drawable.loggggggg);
        builder.setPositiveButton("DISMISS") { dialog, id ->
            dialog.dismiss()

            // Toast.makeText(context,  articles.getTitle().toUpperCase()+" DELETED SUCCESSFULLY",Toast.LENGTH_LONG).show();
        }
        builder.setNegativeButton("") { dialog, id -> dialog.dismiss() }
        val alert = builder.create()
        alert.show()
    }
}