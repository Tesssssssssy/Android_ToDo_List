package com.tessssssssy.oop_todo_list.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    var lineDataSet = LineDataSet(null, null)
    var iLineDataSets = ArrayList<ILineDataSet>()
    var lineData: LineData? = null
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
            }

        })
    }

    fun setLineChartData() {

        FirebaseRef.userInfoRef.child(uid).child("score").addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataVals = ArrayList<Entry>()
                var cnt: Int = 0

                for(myDataSnapshot in snapshot.children) {
                        val graphScore: String = myDataSnapshot.getValue() as String
                        dataVals.add(cnt, Entry(cnt.toFloat(), graphScore.toFloat()))
                        cnt++
                }

                val linedataset = LineDataSet(dataVals, "First")
                val data = LineData(linedataset)
                lineChart.data = data
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })






    }




}