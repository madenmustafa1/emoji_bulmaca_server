package service

import model.image.ImageRequestModel
import model.image.ImageResponseModel

interface ImageInterface {
    suspend fun getImage(imageRequestModel: ImageRequestModel): ImageResponseModel?
    suspend fun getAllCoverImageList(): List<ImageResponseModel?>
    suspend fun gelCoverImage(index: Int): ImageResponseModel?
}