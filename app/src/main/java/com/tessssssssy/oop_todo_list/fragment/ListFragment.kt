package com.tessssssssy.oop_todo_list.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tessssssssy.oop_todo_list.adapter.TodoAdapter
import com.tessssssssy.oop_todo_list.databinding.DialogEditBinding
import com.tessssssssy.oop_todo_list.databinding.FragmentListBinding
import com.tessssssssy.oop_todo_list.model.Todo
import com.tessssssssy.oop_todo_list.onBtnClick
import com.tessssssssy.oop_todo_list.viewModel.TodoListViewModel

class ListFragment : Fragment(), onBtnClick {

    val viewModel: TodoListViewModel by activityViewModels()
    lateinit var binding: FragmentListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter = TodoAdapter(viewModel.todoList.value!!, this)

        viewModel.todoList.observe(viewLifecycleOwner){
            binding.recTodo.layoutManager = LinearLayoutManager(this.context)
            binding.recTodo.adapter = viewModel.todoList.value?.let { adapter }
        }

        viewModel.todoList.observe(viewLifecycleOwner, Observer {
            // 작성하기 버튼 클릭
            binding?.btnWrite?.setOnClickListener{
                // dialog_edit.xml과 bind
                val bindingDialog = DialogEditBinding.inflate(LayoutInflater.from(binding.root.context), binding.root, false)

                AlertDialog.Builder(binding.root.context)
                    .setTitle("To-Do 추가하기")
                    .setView(bindingDialog.root)
                    .setPositiveButton("작성완료", DialogInterface.OnClickListener{ dialogInterface, i ->
                        var newTodoContent = bindingDialog.inputTodo.text.toString()
                        var newPriority = bindingDialog.inputPriority.text.toString()
                        var newCompletion = bindingDialog.inputCompletion.text.toString()
                        val newTodo = Todo(newTodoContent, newPriority, newCompletion)
                        viewModel.addTodo(newTodo)
                        adapter.notifyDataSetChanged()

                    })
                    .setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
                    .show()
            }
        })


    }

    // onBtnClick 콜백함수를 override해서 ViewModel에서 Todo를 삭제하도록 구현한다.
    override fun deleteToDo(todo: Todo) {
        viewModel.removeTodo(todo)
    }

    override fun updateTodo(
        todo: Todo,
        newTodo: String,
        newCompletion: String,
        newPriorty: String
    ) {
        viewModel.updateTodo(todo, newTodo, newCompletion, newPriorty)
    }



}