package com.tessssssssy.oop_todo_list.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tessssssssy.oop_todo_list.MainActivity
import com.tessssssssy.oop_todo_list.databinding.ActivityJoinBinding
import com.tessssssssy.oop_todo_list.utils.FirebaseRef

class JoinActivity : AppCompatActivity() {

    private val TAG = "JoinActivity"

//    Firebase 초기화
    private lateinit var auth: FirebaseAuth

    private var uid = ""
    private var emailstring = ""
    private var nickname = ""

    private lateinit var binding: ActivityJoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Firebase의 authentication 기능 호출.
        auth = Firebase.auth

        binding.joinbtn.setOnClickListener {

            val email = binding.emailArea
            val pwd = binding.pwdArea
            val pwdcheck = binding.pwdCheckArea

//            회원가입을 할 때 예외처리
            val emailCheck = email.text.toString()

            if (emailCheck.isEmpty()) {
                Toast.makeText(this, "비어 있음!", Toast.LENGTH_LONG).show()
            } else if (pwd.text.toString() != pwdcheck.text.toString()) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_LONG).show()
            }

            emailstring = binding.emailArea.text.toString()
            nickname = binding.nicknameArea.text.toString()

            auth.createUserWithEmailAndPassword(email.text.toString(), pwd.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()

                        val user = auth.currentUser
//                        유저의 uid값 저장.
                        uid = user?.uid.toString()

//                        구조화하기 위해 유저 데이터모델을 따로 만듦.
                        val userModel = UserDataModel(
                            uid,
                            emailstring,
                            nickname
                        )

                        FirebaseRef.userInfoRef.child(uid).setValue(userModel)


                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()

                    }
                }




        }


    }
}