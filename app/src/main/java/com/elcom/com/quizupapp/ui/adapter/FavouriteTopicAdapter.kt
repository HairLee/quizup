package com.elcom.com.quizupapp.ui.adapter


import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import com.elcom.com.quizupapp.R
import com.elcom.com.quizupapp.ui.activity.model.entity.response.topicdetail.Topic
import com.elcom.com.quizupapp.ui.listener.OnItemClickListener
import com.mikhaellopez.circularimageview.CircularImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_setting.*


/**
 * Created by Hailpt on 4/24/2018.
 */
class FavouriteTopicAdapter(private val moviesList: List<Topic>,OnItemClick:HorizontalRecyclerAdapter.OnItemClickListener) : RecyclerView.Adapter<FavouriteTopicAdapter.MyViewHolder>() {

    private var mOnItemClick = OnItemClick

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imvAva: CircularImageView = view.findViewById(R.id.imvAva)
        var imvFavor: ImageView = view.findViewById(R.id.imvFavor)
        var tvName: TextView = view.findViewById(R.id.tvName)
        var tvDes: TextView = view.findViewById(R.id.tvDes)
        var tvNumberOfPlayer: TextView = view.findViewById(R.id.tvNuberOfPlayers)
        var tvLevel: TextView = view.findViewById(R.id.tvLevel)
        var rlRoot: RelativeLayout = view.findViewById(R.id.rlRoot);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.favourite_topic_item_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = moviesList[position]

        if(movie.statusFollow == "0"){
            holder.imvFavor.setImageResource(R.drawable.ic_detail_not_favor)
        } else {
            holder.imvFavor.setImageResource(R.drawable.ic_favor)
        }

        if( movie.url != null){
            Picasso.get()
                    .load(movie.url)
                    .into(holder.imvAva);
        }

        holder.tvName.text = movie.name
        holder.tvDes.text = movie.description
        holder.tvNumberOfPlayer.text = movie.number_played.toString() + " Lượt chơi"
        holder.tvLevel.text = "Level "+movie.level

        holder.rlRoot.setOnClickListener {
            mOnItemClick.onItemClick(holder.rlRoot,movie)
        }


    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}