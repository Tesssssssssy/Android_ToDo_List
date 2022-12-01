package com.tessssssssy.oop_todo_list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.tessssssssy.oop_todo_list.adapter.TodoAdapter
import com.tessssssssy.oop_todo_list.adapter.ViewPagerAdapter
import com.tessssssssy.oop_todo_list.databinding.ActivityMainBinding
import com.tessssssssy.oop_todo_list.fragment.GraphFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var todoAdapter: TodoAdapter

//    탭 텍스트, 아이콘 리스트 생성 및 초기화.
    private val tabTextList = listOf("ToDo-List", "Graph", "My Page")
    private val tabIconList = listOf(R.drawable.list_icon, R.drawable.graph_icon, R.drawable.profile_icon)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        viewpager를 활용해 fragment slider 구현.
        binding.viewPager01.adapter = ViewPagerAdapter(this)

//        fragment의 탭 아이콘, 텍스트 설정.
        TabLayoutMediator(binding.tabLayout01, binding.viewPager01) { tab, pos ->
            tab.text = tabTextList[pos]
            tab.setIcon(tabIconList[pos])
        }.attach()

    }
}

