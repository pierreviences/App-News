package com.example.newsapi.ui.adapter

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapi.R
import com.example.newsapi.data.model.ArticlesItem
import com.example.newsapi.databinding.ItemNewsBinding
import com.example.newsapi.ui.NewsWebviewActivity
import com.example.newsapi.utils.convertDateFormat

class NewsAdapter: ListAdapter<ArticlesItem, NewsAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: ArticlesItem) = with(binding) {
            tvTitle.text = data.title
            Glide.with(itemView.context)
                .load(data.urlToImage)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .into(ivGambar)
            tvAuthor.text = data.author
            tvDate.text = data.publishedAt?.let { convertDateFormat(it) }

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, NewsWebviewActivity::class.java).apply {
                    putExtra(NewsWebviewActivity.EXTRA_URL, data.url)
                }
                itemView.context.startActivity(intent)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem:ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ArticlesItem, newItem:ArticlesItem ): Boolean {
                return oldItem == newItem
            }
        }
    }
}