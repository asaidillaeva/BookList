package com.example.booklist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.booklist.model.Books

class RecyclerAdapter internal constructor(context: Context, var books: Books, var onItemClick: OnItemClick) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val volumeInfo = books.items[position].volumeInfo

        val title = volumeInfo.title
        val year = volumeInfo.publishedDate
        val author =
            if (volumeInfo.authors == null) " "
            else volumeInfo.authors.toString()
                .replace("[", "")
                .replace("]", "")

        val pages = volumeInfo.pageCount.toString()

        var image = try {
            volumeInfo.imageLinks.imageLink
                .replace("http", "https");
        } catch (e: NullPointerException) {
            "R.drawable.star";
        }

        var options = RequestOptions()
            .error(R.drawable.star)
            .placeholder(R.mipmap.ic_launcher_round);

        holder.nameTextView.text = title
        holder.authorTextView.text = author
        holder.yearTextView.text = year
        holder.pagesTextView.text = pages
        Glide.with(holder.imageView.context)
            .load(image)
            .apply(options)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return books.items.size
    }

    class MyViewHolder(view: View, var onItemClick: OnItemClick) :
        RecyclerView.ViewHolder(view), View.OnClickListener {

        var nameTextView: TextView = view.findViewById(R.id.bookName)
        var authorTextView: TextView = view.findViewById(R.id.bookAuthor)
        var yearTextView: TextView = view.findViewById(R.id.publishedYear)
        var pagesTextView: TextView = view.findViewById(R.id.pages)
        var imageView: ImageView = view.findViewById(R.id.photoOfBook)

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onItemClick.onItemClicked(adapterPosition)
        }
    }
}

interface OnItemClick {
    fun onItemClicked(position: Int)
}


