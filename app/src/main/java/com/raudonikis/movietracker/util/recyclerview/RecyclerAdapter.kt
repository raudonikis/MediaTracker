package com.raudonikis.movietracker.util.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.raudonikis.movietracker.extensions.inflate

class RecyclerAdapter<ITEM>(
    @LayoutRes private val layoutResId: Int,
    private val bindHolder: View.(ITEM) -> Unit,
    private val itemClick: ITEM.() -> Unit = {}
) : RecyclerView.Adapter<RecyclerAdapter.Holder>() {

    private var items: List<ITEM> = emptyList()

    private fun onItemClick(itemView: View, position: Int) {
        items[position].itemClick()
    }

    private fun View.bind(item: ITEM) {
        bindHolder(item)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = parent inflate layoutResId
        val viewHolder =
            Holder(
                view
            )
        val itemView = viewHolder.itemView
        itemView.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onItemClick(itemView, adapterPosition)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.bind(items[position])
    }

    fun updateList(newList: List<ITEM>) {
        val diffResult = DiffUtil.calculateDiff(
            DiffUtilCallback(
                items,
                newList
            )
        )
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}