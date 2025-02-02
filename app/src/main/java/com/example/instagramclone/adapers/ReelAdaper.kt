package com.example.instagramclone.adapers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclone.Models.Reel
import com.example.instagramclone.R
import com.example.instagramclone.databinding.ReelDesignBinding
import com.squareup.picasso.Picasso

class ReelAdaper(var context: Context, var reelList: ArrayList<Reel>) :
    RecyclerView.Adapter<ReelAdaper.ViewHolder>() {
    inner class ViewHolder(var binding: ReelDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ReelDesignBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(reelList.get(position).profileLink).placeholder(R.drawable.user)
            .into(holder.binding.profileImage)
        holder.binding.caption.setText(reelList.get(position).caption)
        holder.binding.viewView.setVideoPath(reelList.get(position).reelUri)
        holder.binding.viewView.setOnPreparedListener {
            holder.binding.progressBar.visibility=View.GONE
            holder.binding.viewView.start()
        }
    }
}