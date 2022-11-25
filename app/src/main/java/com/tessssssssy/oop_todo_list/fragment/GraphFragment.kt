package com.tessssssssy.oop_todo_list.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.tessssssssy.oop_todo_list.R
import com.tessssssssy.oop_todo_list.databinding.FragmentGraphBinding
import com.tessssssssy.oop_todo_list.viewModel.TodoListViewModel

class GraphFragment : Fragment() {

    val viewModel: TodoListViewModel by activityViewModels()
    lateinit var binding: FragmentGraphBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGraphBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.scr.observe(viewLifecycleOwner, Observer {
            // 작성하기 버튼 클릭
            binding.btnCalScore.setOnClickListener{
                binding.txtScore.text = viewModel.calScore().toString()
            }


        })


    }

}