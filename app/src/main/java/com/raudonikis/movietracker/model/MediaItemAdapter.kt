package com.raudonikis.movietracker.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.api.load
import com.raudonikis.movietracker.R
import com.raudonikis.movietracker.api.util.MediaApiConstants
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.android.synthetic.main.item_movie.view.*

class MediaItemAdapter(
    private val applicationContext: Context,
    private val interaction: Interaction? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    @EntryPoint
    @InstallIn(ApplicationComponent::class)
    interface MediaItemAdapterInterface {
        fun getImageLoader(): ImageLoader
    }

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

        val mediaItemAdapterInterface =
            EntryPointAccessors.fromApplication(applicationContext, MediaItemAdapterInterface::class.java)

        return MovieItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            ),
            interaction,
            mediaItemAdapterInterface.getImageLoader()
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
        private val interaction: Interaction?,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MediaItem) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onMediaItemSelected(adapterPosition, item)
            }
            if (!item.posterPath.isNullOrBlank()) {
                itemView.image_poster.load(
                    imageLoader = imageLoader,
                    uri = MediaApiConstants.IMAGE_URL + item.posterPath
                )
            }
        }
    }

    interface Interaction {
        fun onMediaItemSelected(position: Int, item: MediaItem)
    }
}