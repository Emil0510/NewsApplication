package com.emilabdurahmanli.newapplication


import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.emilabdurahmanli.newapplication.adapter.Adapter
import com.emilabdurahmanli.newapplication.databinding.ActivityMainBinding
import com.emilabdurahmanli.newapplication.model.News
import com.emilabdurahmanli.newapplication.model.Results
import com.emilabdurahmanli.newapplication.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        getNews()
    }
    fun getNews(){
        val call: Call<Results?>? = RetrofitClient.getRetrofitInstance()?.getApi()?.getNews()
        call?.enqueue(object : Callback<Results?> {
            override fun onResponse(call: Call<Results?>?, response: Response<Results?>?) {
                val newsList: Results = response?.body() as Results
                binding.recyclerView.adapter = Adapter(newsList.results)
            }
            override fun onFailure(call: Call<Results?>?, t: Throwable?) {
                Toast.makeText(applicationContext, "An error has occured", Toast.LENGTH_LONG).show()
            }
        })
    }
}