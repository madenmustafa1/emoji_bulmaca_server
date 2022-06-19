package model

data class ImageResponseModel(
    val answer: String,
    val singer: String,
    val index: Int,
    val contentOwner: String = "madenapps",
    val categoryId: Int,
    val image: String,
)