package com.example.instagramclone.fragments

import PostAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagramclone.Models.Post
import com.example.instagramclone.R
import com.example.instagramclone.databinding.FragmentYourBinding
import com.example.instagramclone.utils.POST
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class YourFragment : Fragment() {
    private lateinit var bingding:FragmentYourBinding
    private var postList=ArrayList<Post>()
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      bingding=FragmentYourBinding.inflate(inflater, container, false)
        adapter= PostAdapter(requireContext(),postList)
        bingding.postRv.layoutManager=LinearLayoutManager(requireContext())
        bingding.postRv.adapter=adapter
        setHasOptionsMenu(true)
        (requireContext()as AppCompatActivity).setSupportActionBar(bingding.materialToolbar2)
        Firebase.firestore.collection(POST).get().addOnSuccessListener {
var tempList=ArrayList<Post>()
            postList.clear()
            for (i in it.documents){
                val post:Post=i.toObject<Post>()!!
                tempList.add(post)
            }
            postList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }

        return bingding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    }
