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

class RecyclerAdapter(context: Context, books: Books, var onItemClick: RecyclerAdapter.OnItemClick) :
        RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    lateinit var inflater: LayoutInflater
    var books: Books = books
    var recyclerView: RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return MyViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val volumeInfo = books.items[position].volumeInfo

        val title = volumeInfo.title
        val year = volumeInfo.publishedDate
        val authors: String = volumeInfo.authors?.let { " " }
                ?: volumeInfo.authors.toString().replace("[", "").replace("]", "");
        val pages = volumeInfo.pageCount?.let { " " } ?: volumeInfo.pageCount.toString()

        var image = try {
            volumeInfo.imageLinks.imageLink.replace("http", "https");
        } catch (e: NullPointerException) {
            "R.drawable.star";
        }

        var options = RequestOptions()
                .error(R.drawable.star)
                .placeholder(R.mipmap.ic_launcher_round);

        holder.nameTextView.text = title
        holder.authorTextView.text = authors
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


    class MyViewHolder(view: View, var onItemClick: RecyclerAdapter.OnItemClick) : RecyclerView.ViewHolder(view), View.OnClickListener {

        var nameTextView: TextView = view.findViewById(R.id.bookName)
        var authorTextView: TextView = view.findViewById(R.id.bookAuthor)
        var yearTextView: TextView = view.findViewById(R.id.publishedYear)
        var pagesTextView: TextView = view.findViewById(R.id.pages)
        var imageView: ImageView = view.findViewById(R.id.imageView)



        override fun onClick(v: View?) {
            onItemClick.onItemClick(adapterPosition)
        }

    }

    public interface OnItemClick {
        public fun onItemClick(position: Int)
    }

}

