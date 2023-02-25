package com.metrotech.myapplication.adapter

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Build

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

import com.metrotech.myapplication.DetailActivity
import com.metrotech.myapplication.R
import com.metrotech.myapplication.Tools
import com.metrotech.myapplication.databinding.ItemPersonalityBinding
import com.metrotech.myapplication.model.Personality


class PersonalityAdapter (private val listPersonality : ArrayList<Personality>,private val context: Activity) : RecyclerView.Adapter<PersonalityAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemCardEksplorBinding =
            ItemPersonalityBinding.inflate(layoutInflater, parent, false)
        return ListViewHolder(itemCardEksplorBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listPersonality[position] )



    }

    override fun getItemCount(): Int = listPersonality.size

    inner class ListViewHolder(val binding: ItemPersonalityBinding)  : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Personality ){


            binding.tvPersonality.text = item.title
            binding.tvDesc.text = " \"${item.hint}\"  "
            binding.ivProfile.loadUrl(item.img)
            binding.root.setOnClickListener {
                context.startActivity(Intent(context,DetailActivity::class.java)
                    .putExtra(DetailActivity.KEY_DETAIL,item))
            }

            when(item.id){

                0 -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        binding.decoration.setBackgroundColor(context.getResources().getColor(R.color.first, context.getTheme()));
                    }else {
                        binding.decoration.setBackgroundColor(context.getResources().getColor(R.color.first));
                    }
                }

                1 -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        binding.decoration.setBackgroundColor(context.getResources().getColor(R.color.second, context.getTheme()));
                    }else {
                        binding.decoration.setBackgroundColor(context.getResources().getColor(R.color.second));
                    }
                }

                2 -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        binding.decoration.setBackgroundColor(context.getResources().getColor(R.color.third, context.getTheme()));
                    }else {
                        binding.decoration.setBackgroundColor(context.getResources().getColor(R.color.third));
                    }
                }

                3 -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        binding.decoration.setBackgroundColor(context.getResources().getColor(R.color.forth, context.getTheme()));
                    }else {
                        binding.decoration.setBackgroundColor(context.getResources().getColor(R.color.forth));
                    }
                }
            }











        }

        fun ImageView.loadUrl(url: String) {

            val imageLoader = ImageLoader.Builder(this.context)
                .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
                .build()

            val request = ImageRequest.Builder(this.context)
                .crossfade(true)
                .crossfade(500)

                .data(url)
                .target(this)
                .build()

            imageLoader.enqueue(request)
        }

    }
}