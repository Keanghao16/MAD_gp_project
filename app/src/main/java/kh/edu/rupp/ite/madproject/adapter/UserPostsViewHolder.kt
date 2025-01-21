package kh.edu.rupp.ite.madproject.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kh.edu.rupp.ite.madproject.R
import kh.edu.rupp.ite.madproject.model.UserPosts

class UserPostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageContent: ImageView = itemView.findViewById(R.id.image_content)
    val textTitle: TextView = itemView.findViewById(R.id.text_title)
    val textDateTime: TextView = itemView.findViewById(R.id.text_date_time)

    fun bind(profile: UserPosts) {
        Picasso.get()
            .load(profile.imageUrl)
            .into(imageContent)
        textTitle.text = profile.title
        textDateTime.text = profile.dateTime
    }
}
