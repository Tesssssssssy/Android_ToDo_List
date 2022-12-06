package com.tessssssssy.oop_todo_list.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tessssssssy.oop_todo_list.utils.FirebaseRef

class MyinfoRepository {

    var auth = Firebase.auth
    val user = auth.currentUser
    var uid = user?.uid.toString()

    fun observeMyname(nickname: MutableLiveData<String>) {

        FirebaseRef.userInfoRef.child(uid).child("nickname").addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                nickname.postValue( snapshot.value.toString() )
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })
    }

    fun observeMyemail(email: MutableLiveData<String>) {

        FirebaseRef.userInfoRef.child(uid).child("emailstring").addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                email.postValue( snapshot.value.toString() )
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })
    }

}