package com.example.instagramclone.adapers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagramclone.R
import com.example.instagramclone.databinding.SearchRvBinding
import com.example.instagramclone.utils.FOLLOW
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class SearchAdaper(
    var context: Context,
    var userList: ArrayList<com.example.instagramclone.Models.User>
) :
    RecyclerView.Adapter<SearchAdaper.ViewHolder>() {
    inner class ViewHolder(var binding: SearchRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    data class User(
        val image: String,
        val name: String
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var isfollow=false
        Glide.with(context).load(userList[position].image).placeholder(R.drawable.user)
            .into(holder.binding.profileImage)
        holder.binding.name.text = userList[position].name


        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW)
            .whereEqualTo("email", userList.get(position).email).get().addOnSuccessListener {
            if (it.documents.size==0){
                isfollow=false

            }else{
                holder.binding.follow.text="Unfollow"
                isfollow=true
            }
        }
        holder.binding.follow.setOnClickListener {
            if (isfollow){
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW)
                    .whereEqualTo("email", userList.get(position).email).get().addOnSuccessListener {

                    Firebase.firestore.collection(Firebase.auth.currentUser!!.uid+ FOLLOW).document(it.documents.get(0).id).delete()
                        holder.binding.follow.text="follow"
                        isfollow=false
                    }

            }else{
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + FOLLOW).document()
                    .set(userList.get(position))
                holder.binding.follow.text = "Unfollow"
                isfollow=true

            }

        }

    }
}