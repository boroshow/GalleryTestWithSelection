package com.example.gallerytest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.selection.*
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gallerytest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val list = arrayListOf(
        "https://cdn.vox-cdn.com/thumbor/4E98u_RfYxa8pkRK79CyPClFABY=/0x0:1147x647/1200x800/filters:focal(483x233:665x415)/cdn.vox-cdn.com/uploads/chorus_image/image/70742090/Jotaro.0.jpeg",
        "https://static.wikia.nocookie.net/jjba/images/e/e0/Volume_4.jpg/revision/latest/top-crop/width/360/height/450?cb=20190212212228",
        "https://static.wikia.nocookie.net/jjba/images/2/21/Volume_61.jpg/revision/latest/top-crop/width/360/height/450?cb=20170624003813",
        "https://i.pinimg.com/736x/93/d2/b9/93d2b98345cb92667c846493a368bfd0.jpg",
        "https://static.wikia.nocookie.net/anime-characters-fight/images/d/db/Muhammad_Avdol_Art.png/revision/latest?cb=20190522133529&path-prefix=ru",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOp29Aat6-qHVGPAcjBHK2odw1s7xRg3rx09FFn8m1TS1H04nGorAra4sDdlmkqZPIilc&usqp=CAU",
    )
    private val adapter: RecyclerAdapter by lazy {
        RecyclerAdapter(list)
    }

    private var tracker: SelectionTracker<Long>? = null

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvInit()
        trackerInit()
    }

    private fun rvInit() {
        binding.rvImages.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@MainActivity.adapter
        }
    }

    private fun trackerInit() {
        tracker = SelectionTracker.Builder(
            "ImagesSelection",
            binding.rvImages,
            StableIdKeyProvider(binding.rvImages),
            ImagesLookUp(binding.rvImages),
            StorageStrategy.createLongStorage()
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        adapter.tracker = tracker

        tracker?.addObserver(object : SelectionTracker.SelectionObserver<Long>() {
            override fun onSelectionChanged() {
                super.onSelectionChanged()
                val items = tracker?.selection!!.size()
                if (items > 0) {
                    binding.imageDelete.isVisible = true
                    binding.imageAdd.isVisible = false
                    binding.imageDelete.setOnClickListener {
                        deleteSome(tracker?.selection!!)
                    }
                } else {
                    binding.imageAdd.isVisible = true
                    binding.imageDelete.isVisible = false
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteSome(selection: Selection<Long>?) {
        val deleteList = selection?.map {
            adapter.list[it.toInt()]
        }?.toList()
        deleteList?.toSet()?.let { list.removeAll(it) }
        tracker?.clearSelection()
        adapter.notifyDataSetChanged()
    }


}
