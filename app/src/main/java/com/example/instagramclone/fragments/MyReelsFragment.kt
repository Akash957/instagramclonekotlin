package com.example.instagramclone.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instagramclone.Models.Post
import com.example.instagramclone.Models.Reel
import com.example.instagramclone.R
import com.example.instagramclone.adapers.MyPostRvAdapers
import com.example.instagramclone.adapers.MyReelAdapter
import com.example.instagramclone.databinding.ActivityReelBinding
import com.example.instagramclone.databinding.FragmentMyReelsBinding
import com.example.instagramclone.databinding.FragmentReelBinding
import com.example.instagramclone.utils.REEL
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject


class MyReelsFragment : Fragment() {
    private lateinit var binding: FragmentMyReelsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMyReelsBinding.inflate(inflater, container, false)


        val reelList = ArrayList<Reel>()
        val adapers = MyReelAdapter(requireContext(), reelList)
        binding.rv.layoutManager
        StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.rv.adapter = adapers
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).get()
            .addOnSuccessListener {
                val tempList = arrayListOf<Reel>()
                for (i in it.documents) {
                    val reel: Reel = i.toObject<Reel>()!!
                    tempList.add(reel)
                }
                reelList.addAll(tempList)
                adapers.notifyDataSetChanged()

            }
        return binding.root
    }

    companion object {

    }
}