package com.tessssssssy.oop_todo_list.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tessssssssy.oop_todo_list.model.Todo
import com.tessssssssy.oop_todo_list.utils.FirebaseRef

class TodoRepository {

    var auth = Firebase.auth
    val user = auth.currentUser
    var uid = user?.uid.toString()

    fun observeTodo(todoList: MutableLiveData<ArrayList<Todo>>) {

        FirebaseRef.userInfoRef.child(uid).child("ToDo_List").addValueEventListener(object: ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("snapshot", FirebaseRef.userInfoRef.child(uid).child("ToDo_List").toString())
                // onDataChange시에 todoList의 value를 다시 비워줍니다.
                todoList.value?.clear()
                // 이 settingList에 todo들을 담아 todoList의 value로 전달하려고 합니다.
                val settingList = arrayListOf<Todo>()

                for(data in snapshot.children) {
                    settingList.add(extractData(data)) // 직접 snapshot에서 받아온 데이터를 parsing해서 Todo를 담는 arrayList에 담습니다.
                }
                Log.d("size", settingList.size.toString())

                // settingList에 있는 Todo들을 todoList에 추가합니다.
                for(data in settingList){
                    Log.d("!!", data.toString())
                    todoList.value!!.add(data)
                }
                val result = todoList.value

                // todoList의 value를 보여주기 위한 단계
                todoList.value= todoList.value

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    fun postTodo(todo: Todo) {
        val list = mutableListOf(todo.todo, todo.completion, todo.priority)
//        todoRef.child(todo.todo).setValue(list)
        FirebaseRef.userInfoRef.child(uid).child("ToDo_List").child(todo.todo).setValue(list)
    }

    fun deleteTodo(todo: Todo){
        FirebaseRef.userInfoRef.child(uid).child("ToDo_List").child(todo.todo).removeValue().addOnSuccessListener {
            Log.d("successListener", "remove success!")
        }
    }



    // list로 넘어오는 데이터를 toString으로 바꾼 뒤 parsing하는 함수를 만들었습니다.
    fun extractData(data: DataSnapshot): Todo {
        var todo = data.value.toString()

        var count: Int = 0
        var todoContent = String()
        var priority = String()
        var completion = String()

        var stringBuilder = StringBuilder()
        for(char in todo){
            if(char == '['){
                continue
            }
            if(char == ' '){
                stringBuilder.append(" ")
            }
            if(char == ',' || char == ']'){
//                           Log.d("stringBuilder", stringBuilder.toString())
                if(count == 0){
                    todoContent = stringBuilder.toString()
                    count += 1
                }
                else if(count == 1){
                    priority = stringBuilder.toString()
                    count += 1
                }
                else {
                    completion = stringBuilder.toString()
                }
                stringBuilder.delete(0, stringBuilder.length)
                continue
            }
            else{
                stringBuilder.append(char)
                Log.d("char", char.toString())
            }

        }

        Log.d("todoContent", todoContent)
        Log.d("priority", priority)
        Log.d("completion", completion)
        return (Todo(todoContent, priority, completion))
    }

}




