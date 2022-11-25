package com.tessssssssy.oop_todo_list.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tessssssssy.oop_todo_list.MainActivity
import com.tessssssssy.oop_todo_list.databinding.FragmentListBinding
import com.tessssssssy.oop_todo_list.fragment.GraphFragment
import com.tessssssssy.oop_todo_list.fragment.MyPageFragment
import com.tessssssssy.oop_todo_list.fragment.ListFragment

class ViewPagerAdapter(fragmentActivity: MainActivity): FragmentStateAdapter(fragmentActivity) {

    lateinit var binding: FragmentListBinding
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MyPageFragment()
            1 -> ListFragment()
            else -> GraphFragment()
        }
    }


}