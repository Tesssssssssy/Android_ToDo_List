package com.tessssssssy.oop_todo_list

import com.tessssssssy.oop_todo_list.model.Todo

interface onBtnClick {
    fun deleteToDo(todo: Todo)

    fun updateTodo(todo: Todo, newTodo: String, newCompletion: String, newPriorty: String)
}