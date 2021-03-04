package com.example.booklist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun searchButtonClicked(view: View) {
        val searchText: String = editText.text.toString().trim()
        if(searchText.isEmpty()){
            Toast.makeText(this, "Type Book name or Author", Toast.LENGTH_SHORT).show()
        }else{
            val intent =   Intent(this, ResultActivity::class.java)
            intent.putExtra("searchText", searchText)
            startActivity(intent)
        }
    }
}