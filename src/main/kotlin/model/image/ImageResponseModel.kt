package model.image

data class ImageResponseModel(
    val answer: String,
    val singer: String,
    val index: Int,
    val contentOwner: String = "madenapps",
    val categoryId: Int,
    var image: String,
)