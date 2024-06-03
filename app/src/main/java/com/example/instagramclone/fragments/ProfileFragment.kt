package com.example.instagramclone.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagramclone.Models.User
import com.example.instagramclone.R
import com.example.instagramclone.SignUpActivity
import com.example.instagramclone.adapers.ViewPagerAdapter
import com.example.instagramclone.databinding.FragmentProfileBinding
import com.example.instagramclone.utils.USER_NODE
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.toObject
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {
    private lateinit var binder: FragmentProfileBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(requireContext())

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = FragmentProfileBinding.inflate(inflater, container, false)

        binder.editProfile.setOnClickListener {
            val intent = Intent(activity, SignUpActivity::class.java)
            intent.putExtra("MODE", 1)
            activity?.startActivity(intent)
            activity?.finish()
        }
        viewPagerAdapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        viewPagerAdapter.addFragment(MyPostFragment(), "MY POST")
        viewPagerAdapter.addFragment(MyReelsFragment(), "MY REELS")
        binder.viewPager.adapter = viewPagerAdapter
        binder.tabLayout.setupWithViewPager(binder.viewPager)


        FirebaseApp.initializeApp(requireContext())

        return binder.root
    }


    companion object {

    }

    override fun onStart() {
        super.onStart()
        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
            .addOnSuccessListener {
                val user: User = it.toObject<User>()!!
                binder.name.text = user.name
                binder.bio.text = user.email
                if (!user.image.isNullOrEmpty()) {
                    Picasso.get().load(user.image).into(binder.profileImage)
                }
            }

    }
}