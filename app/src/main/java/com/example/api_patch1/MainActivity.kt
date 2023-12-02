package com.example.api_patch1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.api_patch1.ViewModel.ApiViewModel
import com.example.api_patch1.ViewModel.ApiViewModelFactory

import com.example.api_patch1.databinding.ActivityMainBinding
import com.example.api_patch1.repository.ApiRepository
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var apiViewModel :ApiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = ApiRepository()
        val viewModelFactory = ApiViewModelFactory(repository)

        apiViewModel = ViewModelProvider(this,viewModelFactory).get(ApiViewModel::class.java)
        //apiViewModel.getAllPosts()
        apiViewModel.allPosts.observe(this, Observer {
            Log.i("TOTALLIST","${it}")
        })

        binding.Getbutton.setOnClickListener {
            val id = binding.IdEditText.text.toString().toInt()
            apiViewModel.getPostWithPath(id)
            apiViewModel.responseForId.observe(this,Observer{
                binding.textView.text = "${it}"
            })
        }
    }

    fun insertPosts(posts: Posts){
        lifecycleScope.async {
            val response = apiViewModel.insertPost(posts).await()
            if(response.isSuccessful){
                withContext(Main){
                    Log.i("INSERTED","<-${response.code()}")
                }
            }
        }
    }

    fun getPostsUsingCallBack(){
        /*
        call.enqueue(object: Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                if(!response.isSuccessful){
                    Toast.makeText(applicationContext,"Cannot find",Toast.LENGTH_SHORT).show()
                }
                val posts = response.body() as ArrayList<Posts>
            }
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })*/
    }
}