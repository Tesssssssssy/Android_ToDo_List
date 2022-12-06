package com.tessssssssy.oop_todo_list.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.ListFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tessssssssy.oop_todo_list.MainActivity
import com.tessssssssy.oop_todo_list.R
import com.tessssssssy.oop_todo_list.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.loginbtn.setOnClickListener {

            val email = binding.emailArea
            val pwd = binding.passwordArea

            auth.signInWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful) {

                        Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_LONG).show()
                    }
                }

        }

    }
}