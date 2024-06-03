package com.example.instagramclone.fragments

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagramclone.Post.PostActivity
import com.example.instagramclone.Post.ReelActivity
import com.example.instagramclone.R
import com.example.instagramclone.databinding.FragmentAddBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddFragment : BottomSheetDialogFragment() {
    private lateinit var binder: FragmentAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binder = FragmentAddBinding.inflate(inflater, container, false)

        binder.post.setOnClickListener {
          activity?.startActivity(Intent(requireContext(),PostActivity::class.java))
            activity?.finish()

        }
        binder.reel.setOnClickListener {
            activity?.startActivity(Intent(requireContext(),ReelActivity::class.java))


        }


        return binder.root
    }

    companion object {
    }
}
