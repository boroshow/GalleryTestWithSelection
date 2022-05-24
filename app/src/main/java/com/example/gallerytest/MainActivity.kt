package com.example.gallerytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.gallerytest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val list = arrayListOf(
        RecyclerEntity("https://cdn.vox-cdn.com/thumbor/4E98u_RfYxa8pkRK79CyPClFABY=/0x0:1147x647/1200x800/filters:focal(483x233:665x415)/cdn.vox-cdn.com/uploads/chorus_image/image/70742090/Jotaro.0.jpeg",
            false),
        RecyclerEntity("https://static.wikia.nocookie.net/jjba/images/e/e0/Volume_4.jpg/revision/latest/top-crop/width/360/height/450?cb=20190212212228",
            false),
        RecyclerEntity("https://static.wikia.nocookie.net/jjba/images/2/21/Volume_61.jpg/revision/latest/top-crop/width/360/height/450?cb=20170624003813",
            false),
        RecyclerEntity("https://i.pinimg.com/736x/93/d2/b9/93d2b98345cb92667c846493a368bfd0.jpg",
            false),
        RecyclerEntity("https://static.wikia.nocookie.net/anime-characters-fight/images/d/db/Muhammad_Avdol_Art.png/revision/latest?cb=20190522133529&path-prefix=ru",
            false),
        RecyclerEntity("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOp29Aat6-qHVGPAcjBHK2odw1s7xRg3rx09FFn8m1TS1H04nGorAra4sDdlmkqZPIilc&usqp=CAU",
            false),
        RecyclerEntity("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrDuFVdK8jUb0r1w8bQ3BhmTKAePEAoR2a_Sl9MOWcBBdQX2xlVRi_5Zn2T63AW_mozHU&usqp=CAU",
            false),
        RecyclerEntity("https://preview.redd.it/oomta40tjgc21.jpg?auto=webp&s=b96ae20efa31353c384e0fe2f4d6904874a9ed65",
            false),
        RecyclerEntity("https://cdn.vox-cdn.com/thumbor/4E98u_RfYxa8pkRK79CyPClFABY=/0x0:1147x647/1200x800/filters:focal(483x233:665x415)/cdn.vox-cdn.com/uploads/chorus_image/image/70742090/Jotaro.0.jpeg",
            false),
        RecyclerEntity("https://static.wikia.nocookie.net/jjba/images/e/e0/Volume_4.jpg/revision/latest/top-crop/width/360/height/450?cb=20190212212228",
            false),
        RecyclerEntity("https://static.wikia.nocookie.net/jjba/images/2/21/Volume_61.jpg/revision/latest/top-crop/width/360/height/450?cb=20170624003813",
            false),
        RecyclerEntity("https://i.pinimg.com/736x/93/d2/b9/93d2b98345cb92667c846493a368bfd0.jpg",
            false),
        RecyclerEntity("https://static.wikia.nocookie.net/anime-characters-fight/images/d/db/Muhammad_Avdol_Art.png/revision/latest?cb=20190522133529&path-prefix=ru",
            false),
        RecyclerEntity("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOp29Aat6-qHVGPAcjBHK2odw1s7xRg3rx09FFn8m1TS1H04nGorAra4sDdlmkqZPIilc&usqp=CAU",
            false),
        RecyclerEntity("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrDuFVdK8jUb0r1w8bQ3BhmTKAePEAoR2a_Sl9MOWcBBdQX2xlVRi_5Zn2T63AW_mozHU&usqp=CAU",
            false),
        RecyclerEntity("https://preview.redd.it/oomta40tjgc21.jpg?auto=webp&s=b96ae20efa31353c384e0fe2f4d6904874a9ed65",
            false)
    )
    private val adapter: RecyclerAdapter by lazy {
        RecyclerAdapter(list)
    }
    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvInit()
        rvClick()
    }

    private fun rvClick() {
        adapter.onItemLongClick = {
            if (it.enabled) {
                Toast.makeText(this, it.enabled.toString(), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, it.enabled.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun rvInit() {
        binding.rvImages.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@MainActivity.adapter
        }
    }
}