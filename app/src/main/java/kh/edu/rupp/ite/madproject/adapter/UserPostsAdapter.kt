package kh.edu.rupp.ite.madproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kh.edu.rupp.ite.madproject.R
import kh.edu.rupp.ite.madproject.model.UserPosts

class UserPostsAdapter(private val profiles: List<UserPosts>) : RecyclerView.Adapter<UserPostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserPostsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_profile_post, parent, false)
        return UserPostsViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserPostsViewHolder, position: Int) {
        val profile = profiles[position]

        holder.bind(profile)
    }

    override fun getItemCount(): Int {
        return profiles.size
    }
}
