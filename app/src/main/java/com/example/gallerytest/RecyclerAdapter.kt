package com.example.gallerytest

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gallerytest.databinding.ItemRecyclerBinding

class RecyclerAdapter(
    val list: ArrayList<String>,
) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    var tracker: SelectionTracker<Long>? = null

    init {
        setHasStableIds(true)
    }

    inner class RecyclerViewHolder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(value: String, isActivated: Boolean = false) {
            binding.imgMain.load(value)
            binding.imgSelected.isVisible = isActivated
            binding.btnRadio.isChecked = isActivated
            if (isActivated) {
                val animZoomOut = AnimationUtils.loadAnimation(itemView.context,
                    R.anim.zoom_out)
                binding.card.startAnimation(animZoomOut)
            } else {
                val animZoomIn = AnimationUtils.loadAnimation(itemView.context,
                    R.anim.zoom_in)
                binding.card.startAnimation(animZoomIn)
            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = absoluteAdapterPosition
                override fun getSelectionKey(): Long = itemId
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun getItemCount(): Int = list.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val number = list[position]
        tracker?.let {
            holder.onBind(number, it.isSelected(position.toLong()))
        }
    }

}
