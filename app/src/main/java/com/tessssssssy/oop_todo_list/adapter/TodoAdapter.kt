package com.tessssssssy.oop_todo_list.adapter

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.tessssssssy.oop_todo_list.databinding.DialogEditBinding
import com.tessssssssy.oop_todo_list.databinding.ListTodoBinding
import com.tessssssssy.oop_todo_list.model.Todo

class TodoAdapter(): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todoList: ArrayList<Todo> = ArrayList()

    init{
        var todo1 = Todo()
        todo1.todo = "Buying Cereal"
        todo1.priority = "1"
        todoList.add(todo1)

        var todo2 = Todo()
        todo2.todo = "Buying Chocolate"
        todo2.priority = "2"
        todoList.add(todo2)
    }

    fun addListItem(newTodo: Todo){
        todoList.add(newTodo)
    }

    inner class TodoViewHolder(private val binding: ListTodoBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(todo: Todo){
            // 리스트 뷰 데이터를 UI에 연동
            binding.txtTodo.text = todo.todo
            binding.txtPriority.text = todo.priority
            binding.txtCompletion.text = todo.completion

            // 리스트 삭제 버튼 클릭 연동
            binding.btnRemove.setOnClickListener{
                todoList.remove(todo)
                notifyDataSetChanged()
            }

            // 리스트 수정 클릭 연동
            binding.root.setOnClickListener{
                binding.root.setOnClickListener{
                    val bindingDialog = DialogEditBinding.inflate(LayoutInflater.from(binding.root.context), binding.root, false)
                    // 기존에 작성된 데이터 보여주기
                    bindingDialog.inputTodo.setText(todo.todo)
                    bindingDialog.inputPriority.setText(todo.priority)
                    bindingDialog.inputCompletion.setText(todo.completion)

                    AlertDialog.Builder(binding.root.context)
                        .setTitle("To-Do 수정하기")
                        .setView(bindingDialog.root)
                        .setPositiveButton("작성완료", DialogInterface.OnClickListener{ dialogInterface, i ->

                            todo.todo = bindingDialog.inputTodo.text.toString()
                            todo.priority = bindingDialog.inputPriority.text.toString()
                            todo.completion = bindingDialog.inputCompletion.text.toString()
                            todoList.set(adapterPosition, todo)   // arrayList 수정
                            notifyDataSetChanged() // 리스트 새로고침 *필수!!*/

                        })
                        .setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, i ->

                        })
                        .show()
                }

            }
        }

    }

    // 뷰홀더가 생성됨. (각 리스트 아이템 1개씩 구성될 때마다 이 오버라이드 메소드가 호출됨.)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ListTodoBinding.inflate(LayoutInflater.from(parent.context))
        return TodoViewHolder(binding)
    }

    // 뷰홀더가 바인딩 (결합) 이루어질 때 해줘야 될 처리들을 구현
    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

}