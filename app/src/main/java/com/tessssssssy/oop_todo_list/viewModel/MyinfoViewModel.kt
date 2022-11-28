package com.tessssssssy.oop_todo_list.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tessssssssy.oop_todo_list.repository.MyinfoRepository

const val UNCHECKED_NAME = "ToDo"
const val UNCHECKED_EMAIL = "ToDo@naver.com"

class MyinfoViewModel: ViewModel() {

    private val _nickname = MutableLiveData<String>(UNCHECKED_NAME)
    private val _email = MutableLiveData<String>(UNCHECKED_EMAIL)

    val nickname: LiveData<String> get() = _nickname
    val email: LiveData<String> get() = _email

    private val repository = MyinfoRepository()
    init {
        repository.observeMyname(_nickname)
        repository.observeMyemail(_email)
    }




}