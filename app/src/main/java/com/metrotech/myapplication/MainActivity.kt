package com.metrotech.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.metrotech.myapplication.adapter.PersonalityAdapter
import com.metrotech.myapplication.databinding.ActivityMainBinding
import com.metrotech.myapplication.model.Personality

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val list = ArrayList<Personality>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        list.addAll(getListPersonality())
        binding.rvPersonality.setHasFixedSize(true)

        showRecyclerList()
        binding.aboutPage.setOnClickListener {
            startActivity(Intent(this,AboutActivity::class.java))
        }


        setContentView(binding.root)





    }

    private fun showRecyclerList() {
        binding.rvPersonality.layoutManager = LinearLayoutManager(this)
        val listPersonalityAdapter = PersonalityAdapter(list,this)
        binding.rvPersonality.adapter = listPersonalityAdapter

    }



    private fun getListPersonality(): ArrayList<Personality> {
        val dataTitle = resources.getStringArray(R.array.data_title_personality)
        val dataDescription = resources.getStringArray(R.array.data_desc_personality)
        val dataHint = resources.getStringArray(R.array.data_hint_personality)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataLink = resources.getStringArray(R.array.data_link_more)
        val dataType = resources.getStringArray(R.array.data_type_personality)
        val dataHeader = resources.getStringArray(R.array.data_img_header)
        val dataID = resources.getIntArray(R.array.data_id_type_personality)




        val listPersonality = ArrayList<Personality>()
        for (i in dataTitle.indices) {
            val hero = Personality(dataTitle[i],
                dataPhoto[i],
                dataDescription[i],
                dataHint[i],
                dataLink[i],
                dataType[i],
                dataHeader[i],
                dataID[i]
            )
            listPersonality.add(hero)
        }
        return listPersonality
    }
}