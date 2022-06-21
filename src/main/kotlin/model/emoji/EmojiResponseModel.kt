package model.emoji

data class EmojiResponseModel(
    val answer: String,
    val singer: String,
    val index: Int,
    val contentOwner: String = "madenapps",
    val categoryId: Int,
    var image: String,
)