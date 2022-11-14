package com.tessssssssy.oop_todo_list.utils

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRef {

    companion object {

        val database = Firebase.database

//        Firebase realtime database에 회원가입 때 입력한 정보 저장.
        val userInfoRef = database.getReference("User_Information")
    }
}