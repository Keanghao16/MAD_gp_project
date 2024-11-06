package kh.edu.rupp.ite.madproject.model

data class Profile(
    val profileImg: String,
    val firstName: String,
    val lastName: String,
    val follower: Int,
    val following: Int,

    val userPosts: List<UserPosts>

)
