package com.tessssssssy.oop_todo_list.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.tessssssssy.oop_todo_list.R
import com.tessssssssy.oop_todo_list.auth.IntroActivity
import com.tessssssssy.oop_todo_list.databinding.FragmentMyPageBinding
import com.tessssssssy.oop_todo_list.utils.FirebaseAuthUtils
import kotlinx.android.synthetic.main.fragment_my_page.*


class MyPageFragment : Fragment() {

    private val TAG = "MyPageFragment"
    lateinit var binding: FragmentMyPageBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

//    FirebaseAuthUtils에 있는 getUid 함수를 통해 uid값 받음
    private val uid = FirebaseAuthUtils.getUid()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        database = Firebase.database.reference


    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        val setting = binding.logoutBtn
        logout_btn.setOnClickListener {

            auth.signOut()

            activity?.let {
                val intent = Intent(context, IntroActivity::class.java)
                startActivity(intent)
            }
        }
    }


}