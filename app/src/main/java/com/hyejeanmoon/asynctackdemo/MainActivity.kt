package com.hyejeanmoon.asynctackdemo

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.hyejeanmoon.asynctackdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btn.setOnClickListener {
            val result = MyTask().execute("Hello World").get()
            Snackbar.make(binding.root, result, Snackbar.LENGTH_LONG).show()
        }
    }
}

class MyTask() : AsyncTask<String, String, String>() {

    // 必须重写
    override fun onPreExecute() {
        super.onPreExecute()
        Log.d("MyTask", "onPreExecute")
    }

    // 必须重写
    override fun doInBackground(vararg params: String?): String {
        Log.d("MyTask", "doInBackground")

        Thread.sleep(2000)

        var result = "PEACE!"
        params[0]?.also {
            result = "$it $result"
        }

        return result
    }

    // 不必须重写
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        Log.d("MyTask", "onPostExecute")
    }

}
