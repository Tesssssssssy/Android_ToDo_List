package com.tessssssssy.oop_todo_list.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tessssssssy.oop_todo_list.R
import com.tessssssssy.oop_todo_list.auth.IntroActivity
import com.tessssssssy.oop_todo_list.databinding.FragmentMyPageBinding
import kotlinx.android.synthetic.main.fragment_my_page.*


class MyPageFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

//        로그아웃 버튼 - > 인트로 액티비티로 이동 (현재 작동 안됨).

    }

    lateinit var binding: FragmentMyPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyPageBinding.inflate(inflater, container, false)


//        auth = Firebase.auth

//        val setting = binding.logoutBtn
//        logout_btn.setOnClickListener {
//
//            auth.signOut()
//
//            activity?.let {
//                val intent = Intent(context, IntroActivity::class.java)
//                startActivity(intent)
//            }
//        }

//        val setting2 = binding.signoutBtn
//        signout_btn.setOnClickListener {
//
//            auth.signOut()
//
//            activity?.let {
//                val intent = Intent(context, IntroActivity::class.java)
//                startActivity(intent)
//            }
        return binding.root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyPageFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyPageFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}