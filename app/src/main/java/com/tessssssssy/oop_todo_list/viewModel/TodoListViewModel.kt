package com.tessssssssy.oop_todo_list.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tessssssssy.oop_todo_list.model.Todo
import com.tessssssssy.oop_todo_list.repository.TodoRepository


class TodoListViewModel: ViewModel() {
    private var _todoList = MutableLiveData<ArrayList<Todo>>(arrayListOf())
    val todoList: LiveData<ArrayList<Todo>> get() = _todoList

    private var _scr = MutableLiveData<ArrayList<Int>>(arrayListOf())
    val scr: LiveData<ArrayList<Int>> get() = _scr

    private val repository = TodoRepository()
    init{
        repository.observeTodo(_todoList)
    }

    fun addTodo(todo: Todo){
        _todoList.value?.add(todo)
        repository.postTodo(todo)
    }

    fun removeTodo(todo:Todo){
        _todoList.value?.remove(todo)
        repository.deleteTodo(todo)
    }

    fun updateTodo(todo:Todo, newTodo: String, newPriority: String, newCompletion: String){
        val todoForUpdate = todoList.value?.find { it.todo == todo.todo }
        if (todoForUpdate != null) {
            todoForUpdate.todo = newTodo.trim()
            todoForUpdate.priority = newPriority.trim()
            todoForUpdate.completion = newCompletion.trim()

            repository.postTodo(todoForUpdate)
        }
    }

    fun calScore(): Int {
        var sum = 0
        var total = 0
        if(todoList.value!!.size == 0) {
            return 0
        }
        for (i in 0.._todoList.value!!.size - 1) {

            sum += _todoList.value?.get(i)?.priority?.replace("\\s+".toRegex(),"")?.toInt()!! * _todoList.value?.get(i)?.completion?.replace("\\s+".toRegex(),"")?.toInt()!!
            total += _todoList.value!!.get(i).priority?.replace("\\s+".toRegex(),"")?.toInt()!!
        }

        sum /= total

        return sum
    }


}