package com.raudonikis.movietracker.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.api.MediaApiConstants
import kotlinx.android.synthetic.main.item_movie.view.*

class MediaItemAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<MediaItem>() {

        override fun areItemsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MovieItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieItemViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<MediaItem>) {
        differ.submitList(list)
    }

    class MovieItemViewHolder
    constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MediaItem) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onMediaItemSelected(adapterPosition, item)
            }
            if (item.posterPath == null) return@with
            itemView.image_poster.load(MediaApiConstants.IMAGE_URL + item.posterPath)
        }
    }

    interface Interaction {
        fun onMediaItemSelected(position: Int, item: MediaItem)
    }
}