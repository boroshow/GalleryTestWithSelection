package com.example.gallerytest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gallerytest.databinding.ItemRecyclerBinding

class RecyclerAdapter(private val list: List<RecyclerEntity>) :
    RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    var onItemClick: ((RecyclerEntity) -> Unit)? = null
    var onItemLongClick: ((RecyclerEntity) -> Unit)? = null
    var boolean: Boolean = false
    var mutableList = mutableListOf<RecyclerEntity>()

    inner class RecyclerViewHolder(private val binding: ItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(ent: RecyclerEntity) {
            binding.imgMain.load(ent.url)

            itemView.setOnClickListener {
                if (boolean) {
                    if (mutableList.isEmpty()) {
                        boolean = false
                    } else {
                        onItemClick?.invoke(list[absoluteAdapterPosition])
                        if (ent.enabled) {
                            ent.enabled = false
                            mutableList.remove(ent)
                            binding.imgSelected.isVisible = false
                            binding.btnRadio.isEnabled = false
                        } else {
                            ent.enabled = true
                            mutableList.add(ent)
                            binding.imgSelected.isVisible = true
                            binding.btnRadio.isEnabled = true
                        }
                    }
                }
            }

            itemView.setOnLongClickListener {
                onItemLongClick?.invoke(list[absoluteAdapterPosition])
                boolean = true
                if (ent.enabled) {
                    ent.enabled = false
                    mutableList.remove(ent)
                    binding.imgSelected.isVisible = false
                    binding.btnRadio.isEnabled = false
                } else {
                    ent.enabled = true
                    mutableList.add(ent)
                    binding.imgSelected.isVisible = true
                    binding.btnRadio.isEnabled = true
                }
                return@setOnLongClickListener true
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.onBind(list[position])
    }

}
