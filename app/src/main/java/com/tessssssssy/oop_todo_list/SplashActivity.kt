package com.tessssssssy.oop_todo_list

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.tessssssssy.oop_todo_list.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this, IntroActivity::class.java)) //지정한 액티비티로 이동
            finish() //현재 액티비티 종료
        }, 3000)



    }
}