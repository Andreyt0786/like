data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likeCount: Int,
    var likedByMe: Boolean = false,
    var numberShare: Int = 990
)