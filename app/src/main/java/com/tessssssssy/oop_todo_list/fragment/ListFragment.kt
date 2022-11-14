package com.tessssssssy.oop_todo_list.fragment

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.tessssssssy.oop_todo_list.adapter.TodoAdapter
import com.tessssssssy.oop_todo_list.databinding.DialogEditBinding
import com.tessssssssy.oop_todo_list.databinding.FragmentListBinding
import com.tessssssssy.oop_todo_list.model.Todo

class ListFragment : Fragment() {

    // fragment_list.xml과 연결합니다.
    lateinit var binding: FragmentListBinding
    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.recTodo.layoutManager = LinearLayoutManager(this.context)

        todoAdapter = TodoAdapter()
        binding.recTodo.adapter = todoAdapter

//         작성하기 버튼 클릭
        binding.btnWrite.setOnClickListener{
            // dialog_edit.xml과 bind
            val bindingDialog = DialogEditBinding.inflate(LayoutInflater.from(binding.root.context), binding.root, false)

            AlertDialog.Builder(binding.root.context)
                .setTitle("To-Do 추가하기")
                .setView(bindingDialog.root)
                .setPositiveButton("작성완료", DialogInterface.OnClickListener{dialogInterface, i ->
                    val newTodo = Todo()
                    newTodo.todo = bindingDialog.inputTodo.text.toString()
                    newTodo.priority = bindingDialog.inputPriority.text.toString()
                    newTodo.completion = bindingDialog.inputCompletion.text.toString()
                    todoAdapter.addListItem(newTodo) // 어댑터의 전역변수의 arrayList쪽에 아이템 추가하기 위한 메소드 호출
                    todoAdapter.notifyDataSetChanged() // 리스트 새로고침 *필수!!*/

                })
                .setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, i ->

                })
                .show()
        }


        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = ListFragment()


    }
}