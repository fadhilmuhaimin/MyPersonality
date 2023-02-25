package com.metrotech.myapplication

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.util.Linkify
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.metrotech.myapplication.databinding.ActivityDetailBinding
import com.metrotech.myapplication.model.Personality

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private   var item: Personality? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)

        binding.toolbarDetail.setNavigationOnClickListener {
            finish()
        }

        item = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(KEY_DETAIL, Personality::class.java)
        } else {
            intent.getParcelableExtra<Personality>(KEY_DETAIL)
        }

        if ( item != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.tvDesc.setText(Html.fromHtml(item?.desc, Html.FROM_HTML_MODE_LEGACY));
            } else {
                binding.tvDesc.setText(Html.fromHtml(item?.desc));
            }

        }
//
//        GlideToVectorYou
//            .init()
//            .with(this)
//
//            .load(Uri.parse(item?.banner), binding.header)


        binding.tvTitle.text = item?.title
        binding.tvCategory.text = item?.type
        binding.tvValueLink.text = item?.link
        binding.hint.text = " \"${item?.hint}\"  "
        Linkify.addLinks(binding.tvValueLink,Linkify.WEB_URLS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.tvDesc.setText(Html.fromHtml(item?.desc, Html.FROM_HTML_MODE_LEGACY));
        } else {
            binding.tvDesc.setText(Html.fromHtml(item?.desc));
        }
        item?.banner?.let { binding.header.loadUrl(it) }

        setBackground()

        binding.actionShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, item?.link)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }











        setContentView(binding.root)

    }

    private fun setBackground() {
        when(item?.id){

            0 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.hint.setBackgroundColor(this.getResources().getColor(R.color.first, this.getTheme()));
                }else {
                    binding.hint.setBackgroundColor(theme.getResources().getColor(R.color.first));
                }
            }

            1 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.hint.setBackgroundColor(this.getResources().getColor(R.color.second, this.getTheme()));
                }else {
                    binding.hint.setBackgroundColor(theme.getResources().getColor(R.color.second));
                }
            }


            2 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.hint.setBackgroundColor(this.getResources().getColor(R.color.third, this.getTheme()));
                }else {
                    binding.hint.setBackgroundColor(theme.getResources().getColor(R.color.third));
                }
            }


            3 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    binding.hint.setBackgroundColor(this.getResources().getColor(R.color.forth, this.getTheme()));
                }else {
                    binding.hint.setBackgroundColor(theme.getResources().getColor(R.color.forth));
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

    companion object{
        val KEY_DETAIL = "Detail"
    }
}