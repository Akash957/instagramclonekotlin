import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagramclone.Models.Post
import com.example.instagramclone.Models.User
import com.example.instagramclone.R
import com.example.instagramclone.databinding.PostRvBinding
import com.example.instagramclone.utils.USER_NODE
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.toObject

class PostAdapter(private val context: Context, private val postList: List<Post>) :
    RecyclerView.Adapter<PostAdapter.MyHolder>() {

    inner class MyHolder(val binding: PostRvBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = PostRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        try {
            Firebase.firestore.collection(USER_NODE).document(postList[position].name).get()
                .addOnSuccessListener { documentSnapshot ->
                    val user = documentSnapshot.toObject<User>()
                    user?.let {
                        Glide.with(context)
                            .load(it.image)
                            .placeholder(R.drawable.user)
                            .into(holder.binding.profileImage)
                        holder.binding.name.text = it.name
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        Glide.with(context)
            .load(postList[position].postUri)
            .placeholder(R.drawable.loading)
            .into(holder.binding.postImage)


        holder.binding.time.text = postList[position].time
        holder.binding.csption.text = postList[position].caption

        holder.binding.like.setOnClickListener {

            holder.binding.like.setImageResource(R.drawable.heartlike)
        }
        holder.binding.share.setOnClickListener {
            val i= Intent(android.content.Intent.ACTION_SEND)
            i.type="text/plain"
            i.putExtra(Intent.EXTRA_TEXT,postList[position].postUri)
            context.startActivity(i)
        }
    }
}
