package model

data class ImageResponseModel(
    val answer: String,
    val singer: String,
    val index: Int,
    val image: String,
    val contentOwner: String = "madenapps",
)