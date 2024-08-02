package com.example.poetryapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.poetryapp.Adapter.PoetryAdapter
import com.example.poetryapp.Api.ApiClient
import com.example.poetryapp.Api.ApiInterface
import com.example.poetryapp.databinding.ActivityMainBinding
import com.example.poetryapp.response.GetPoetryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var poetryAdapter: PoetryAdapter
    private lateinit var apiInterface: ApiInterface
   // var poetryModelList:MutableList<PoetryModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Adding data inside Model
//        poetryModelList.add(PoetryModel(1,"Test Poetry","Mirza Ghalib","02-08-2023"))
//        poetryModelList.add(PoetryModel(2,"Test Poetry2","Mirza Ghalib2","02-08-2024"))

        initialization()
        getPoetryData()


    }

    private fun initialization(){
        val retrofit: Retrofit = ApiClient.getClient()
        apiInterface = retrofit.create(ApiInterface::class.java)
    }
    private fun setAdapter(poetryModelList:List<PoetryModel>){
        poetryAdapter = PoetryAdapter(poetryModelList)
        binding.poetryRecyclerView.apply {
            adapter = poetryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity,VERTICAL,false)
        }
        Log.d("MainActivity", "Adapter set with ${poetryModelList.size} items")
    }

    private fun getPoetryData() {
        apiInterface.getPoetry().enqueue(object : Callback<GetPoetryResponse> {
            override fun onResponse(call: Call<GetPoetryResponse>, response: Response<GetPoetryResponse>) {
                if (response.isSuccessful && response.body()?.status == "1") {
                    Log.d("MainActivity", "Data fetched: ${response.body()?.data}")
                    setAdapter(response.body()!!.data)
                } else {
                    Toast.makeText(this@MainActivity, response.body()?.message ?: "Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GetPoetryResponse>, t: Throwable) {
                Log.e("MainActivity", "Error fetching data: ${t.localizedMessage}")
            }
        })
    }
}