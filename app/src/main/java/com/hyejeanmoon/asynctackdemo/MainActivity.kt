package com.hyejeanmoon.asynctackdemo

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyejeanmoon.asynctackdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.btn.setOnClickListener {
            MyTask().execute("Hello World")
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class MyTask : AsyncTask<String, Int, String>() {

        // 必须重写
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("MyTask", "onPreExecute")
            binding.textView.text = "MyTask onPreExecute"
        }

        // 必须重写
        override fun doInBackground(vararg params: String?): String {
            Log.d("MyTask", "doInBackground")

            for (i in 0..100) {
                Thread.sleep(50)
                publishProgress(i)
            }

            var result = "PEACE!"
            params[0]?.also {
                result = "$it $result"
            }

            return result
        }

        // 不必须重写
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)

            binding.textView.text = "${values[0]} %"
        }

        // 不必须重写
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.d("MyTask", "onPostExecute")
            binding.textView.text = "MyTask onPostExecute"
        }

    }
}

