package com.tessssssssy.oop_todo_list.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
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
        setLineChartData()
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
                setLineChartData()
                Toast.makeText(requireContext(), "계산 완료! 아래 그래프에서 점수변화를 확인할 수 있습니다!", Toast.LENGTH_LONG).show()
            }

        })
    }

    // 이 함수로 라인 차트 구현
    fun setLineChartData() {

        FirebaseRef.userInfoRef.child(uid).child("score").addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // dataVals에 Entry들을 담는다.
                val dataVals = ArrayList<Entry>()
                var cnt: Int = 0
                // snapshot.children 만큼 루프가 돌아가며, graphScore에 database value를 받아와 Entry로 추가
                // cnt는 x축의 좌표를 나타내주는 변수
               for(myDataSnapshot in snapshot.children) {
                   if(myDataSnapshot == null) {
                       dataVals.add(0, Entry(0f, 0f))
                   }
                        val graphScore: String = myDataSnapshot.getValue() as String
                        dataVals.add(cnt, Entry(cnt.toFloat(), graphScore.toFloat()))
                        cnt++
                }
                // Entry를 LineDataSet에 넣음
                val linedataset = LineDataSet(dataVals, "Score")
                // LineDataSet을 data에 넣음
                val data = LineData(linedataset)
                // lineChart에 data를 넣어줌
                lineChart.data = data

                lineChart.animateXY(2000, 2000)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })






    }




}