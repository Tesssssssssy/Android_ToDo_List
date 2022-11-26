package com.tessssssssy.oop_todo_list.adapter

import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.tessssssssy.oop_todo_list.databinding.DialogEditBinding
import com.tessssssssy.oop_todo_list.databinding.ListTodoBinding
import com.tessssssssy.oop_todo_list.model.Todo
import com.tessssssssy.oop_todo_list.onBtnClick

class TodoAdapter(val todos: MutableList<Todo>, listener: onBtnClick): RecyclerView.Adapter<TodoAdapter.Holder>() {

    private val mCallback = listener

    // 생성된 뷰 홀더에 값 지정
    inner class Holder(private val binding: ListTodoBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(todo: Todo){
            binding.txtTodo.text = todo.todo
            binding.txtPriority.text = todo.priority
            binding.txtCompletion.text = todo.completion

            // To-Do 삭제하기
            binding.btnRemove.setOnClickListener{
                mCallback.deleteToDo(todo) // 콜백함수 호출 (adapter에서 viewModel을 직접 불러올 경우 생명주기 충돌 가능, 메모리 해제 안될 수 있음)
                notifyDataSetChanged() // 데이터가 비뀌었음을 알림
                Log.d("test", "clicked!")
            }


            // To-Do 수정하기
            binding.root.setOnClickListener{
                val bindingDialog = DialogEditBinding.inflate(LayoutInflater.from(binding.root.context), binding.root, false)
                Log.d("update", "update click")

                // 기존에 작성된 데이터 보여주기
                bindingDialog.inputTodo.setText(todo.todo)
                bindingDialog.inputPriority.setText(todo.priority)
                bindingDialog.inputCompletion.setText(todo.completion)


                AlertDialog.Builder(binding.root.context)
                    .setTitle("To-Do 수정하기")
                    .setView(bindingDialog.root)
                    .setPositiveButton("작성완료", DialogInterface.OnClickListener{ dialogInterface, i ->
                        var newTodo = bindingDialog.inputTodo.text.toString().trim()
                        var newPriority = bindingDialog.inputPriority.text.toString().trim()
                        var newCompletion = bindingDialog.inputCompletion.text.toString().trim()
                        // callback 함수를 통해 ViewModel에 연결된 update함수 수행
                        mCallback.updateTodo(todo, newTodo, newPriority, newCompletion)
                        notifyDataSetChanged() // 리스트 새로고침 *필수!!*/

                    })
                    .setNegativeButton("취소", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
                    .show()

            }
        }





    }

    // 어떤 xml으로 뷰 홀더를 생성할지 지정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ListTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    // 뷰 홀더에 데이터 바인딩
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(todos[position])
    }

    // 뷰 홀더의 개수 리턴
    override fun getItemCount(): Int {
        return todos.size
    }

}