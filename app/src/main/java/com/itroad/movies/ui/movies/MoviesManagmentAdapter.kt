package com.itroad.movies.ui.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itroad.movies.R
import com.itroad.movies.app.common.Constants
import com.itroad.movies.data.models.movies.Result

class MoviesManagmentAdapter(private val context: Context?,
                             var list: List<Result?>,
                             private val listMyLineClickListener: ListFormuleClickListener

) : RecyclerView.Adapter<MoviesManagmentAdapter.FeatureViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FeatureViewHolder {
        return FeatureViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.movies_item, parent, false)
        )


    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {

        list!![position].let {
            if (it != null) {
                holder.bind(it)
            }
        }
        holder.itemView.setOnClickListener {
            list[position]?.let { it1 -> listMyLineClickListener.onItemClick(position, it1) }
        }

    }

    /**
     * Find all the views of the list item
     */
    inner class FeatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_text)
        val subtitle: TextView = itemView.findViewById(R.id.title2_text)
        val img: ImageView = itemView.findViewById(R.id.delete)

        /**
         * Show the data in the views
         */
        fun bind(Movie: Result) {
            title.text = Movie.original_title?:""
            subtitle.text= Movie.release_date?:""

            context?.let {
                Glide.with(it)
                    .load(Constants.BASE_URL_IMAGE+Movie.poster_path?:"")
                    .into(img)
            }

        }

    }

}



