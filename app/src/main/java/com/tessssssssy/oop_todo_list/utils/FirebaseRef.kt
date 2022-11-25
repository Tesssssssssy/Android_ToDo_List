package com.tessssssssy.oop_todo_list.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRef {

    companion object {

        val database = Firebase.database(("https://oop-todo-list-default-rtdb.asia-southeast1.firebasedatabase.app/"))

//        var auth = Firebase.auth
//        val user = auth.currentUser
//        var uid = user?.uid.toString()

//        Firebase realtime database에 회원가입 때 입력한 정보 저장.
        val userInfoRef = database.getReference("User_Information")
//        val todoInfoRef = userInfoRef.child(uid).child("ToDo_List")
    }
}