package com.tessssssssy.oop_todo_list

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.tessssssssy.oop_todo_list.auth.IntroActivity
import com.tessssssssy.oop_todo_list.databinding.ActivitySplashBinding
import com.tessssssssy.oop_todo_list.utils.FirebaseAuthUtils

class SplashActivity : AppCompatActivity() {

    private val TAG = "SplashActivity"

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        회원가입 후 firebase authentication에 저장된 uid값을 불러옴. (기존 유저인지 아닌지 확인 위함)
        val uid = FirebaseAuthUtils.getUid()

//        app 실행 시 uid 값이 null이면 로그인을 새로 해야허므로 인트로 액티비티로 이동.
//        uid 값이 null이 아닌 uid 값이 들어오면 기존 회원이므로 바로 메인 액티비티로 이동.
        if (uid == "null") {

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                startActivity(Intent(this, IntroActivity::class.java)) //지정한 액티비티로 이동
                finish() //현재 액티비티 종료
            }, 3000)

        } else {

            Handler(Looper.getMainLooper()).postDelayed(Runnable {
                startActivity(Intent(this, MainActivity::class.java)) //지정한 액티비티로 이동
                finish() //현재 액티비티 종료
            }, 3000)
        }


    }
}