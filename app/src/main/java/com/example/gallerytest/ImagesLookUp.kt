package com.example.gallerytest

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

class ImagesLookUp(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {

    override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
        val view = recyclerView.findChildViewUnder(event.x, event.y)
        if (view != null){
            return (recyclerView.getChildViewHolder(view) as RecyclerAdapter.RecyclerViewHolder)
                .getItemDetails()
        }
        return null
    }

}