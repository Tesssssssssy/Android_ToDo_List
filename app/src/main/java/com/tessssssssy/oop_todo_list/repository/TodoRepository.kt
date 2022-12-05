package com.tessssssssy.oop_todo_list.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
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

                for(data in snapshot.children) {
                    Log.d("data", data.toString())
                    todoList.value!!.add(extractData(data)) // 직접 snapshot에서 받아온 데이터를 parsing해서 Todo를 담는 arrayList에 담습니다.
                }

                val result = todoList.value
                todoList.value= result

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    fun postTodo(todo: Todo) {
        val list = mutableListOf(todo.todo, todo.priority, todo.completion)
        FirebaseRef.userInfoRef.child(uid).child("ToDo_List").child(todo.todo.trim()).setValue(list)
    }

    fun deleteTodo(todo: Todo){
        FirebaseRef.userInfoRef.child(uid).child("ToDo_List").child(todo.todo).removeValue().addOnSuccessListener {
            Log.d("successListener", "remove success!")
        }
    }



    // list로 넘어오는 데이터를 toString으로 바꾼 뒤 parsing하는 함수를 만들었습니다.
    fun extractData(data: DataSnapshot): Todo {
        var todo = data.value.toString()
        Log.d("todo", todo)
        var count: Int = 0
        var todoContent = String()
        var priority = String()
        var completion = String()

        var string = String()
        for(char in todo){
            if(char == '['){
                continue
            }
            if(char == ' '){
//                string += ""
            }
            if(char == ',' || char == ']'){
//                           Log.d("stringBuilder", stringBuilder.toString())
                if(count == 0){
                    todoContent = string.trim()
                    count += 1
                }
                else if(count == 1){
                    priority = string.trim()
                    count += 1
                }
                else {
                    completion = string.trim()
                }
                string = ""
                continue
            }
            else{
                string += char
//                Log.d("stringBuilder", stringBuilder.toString())
//                Log.d("char", char.toString())
            }

        }

        Log.d("todoContent", todoContent.trim())
        Log.d("priority", priority.trim())
        Log.d("completion", completion.trim())
        return (Todo(todoContent, priority, completion))
    }

}




