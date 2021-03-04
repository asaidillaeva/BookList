package com.example.booklist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booklist.model.Books
import com.example.booklist.net.Api
import com.example.booklist.net.NetworkRetrofit
import kotlinx.android.synthetic.main.activity_result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class ResultActivity : AppCompatActivity(), OnItemClick {

    val LOG_TAG = ResultActivity::class.java.simpleName
    var retrofit: Retrofit = NetworkRetrofit().getRetrofitInstance()
    var api: Api = retrofit.create(Api::class.java)
    lateinit var books: Books


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val searchText = intent.getStringExtra("searchText")!!.toString()

        api.getBooks(searchText).enqueue(object : Callback<Books> {
            override fun onResponse(call: Call<Books>, response: Response<Books>) {
                if (response.body() != null) {
                    books = response.body()!!
                    initBooks()
                } else {
                    intentBack()
                }
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<Books>, t: Throwable) {
                Log.e(LOG_TAG, t.message)
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
            }

        })

        recyclerView.hasFixedSize()


    }

    private fun intentBack() {
        val intent = Intent(this, MainActivity::class.java)
        Toast.makeText(applicationContext, "There is no such book", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    private fun initBooks() {
        val adapter = RecyclerAdapter(applicationContext, books, this)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
        adapter.onItemClick=this
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()


    }

    override fun onItemClicked(position: Int) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(books.items[position].volumeInfo.infoLink));
        startActivity(intent)
    }
}