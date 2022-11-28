package com.tessssssssy.oop_todo_list.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tessssssssy.oop_todo_list.R
import com.tessssssssy.oop_todo_list.databinding.FragmentGraphBinding
import com.tessssssssy.oop_todo_list.utils.FirebaseRef
import com.tessssssssy.oop_todo_list.viewModel.TodoListViewModel
import kotlinx.android.synthetic.main.fragment_graph.*

class GraphFragment : Fragment() {

    val viewModel: TodoListViewModel by activityViewModels()
    lateinit var binding: FragmentGraphBinding
    var auth = Firebase.auth
    val user = auth.currentUser
    var uid = user?.uid.toString()

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
                FirebaseRef.userInfoRef.child(uid).child("score").push().setValue(binding.txtScore.text.toString())
            }

        })
    }

    private fun setChart() {
        val xAxis = chart_socre.xAxis

        xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            textSize = 10f // 텍스트 크기 지정(float 형태로 해주어야 함)
            setDrawGridLines(false) /// 배경 그리드 라인 세팅
            granularity = 1f //x축 데이터 표시 간격
            axisMinimum = 2f //x축 데이터의 최소 표시값
            isGranularityEnabled = true
        }
        chart_socre.apply {
            axisRight.isEnabled = false //y축의 오른쪽 데이터 비활성화
            axisLeft.axisMaximum = 100f //y축의 왼쪽 데이터 최대값은 100으로 설정
            legend.apply {
                textSize = 15f //글자크기
                verticalAlignment = Legend.LegendVerticalAlignment.TOP
                horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                orientation = Legend.LegendOrientation.HORIZONTAL
                setDrawInside(false)
            }
        }
        val lineData = LineData()
        chart_socre.data = lineData // 라인 차트 데이터 지정
        feedMiltiple()
    }


}