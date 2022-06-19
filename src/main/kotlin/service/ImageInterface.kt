package service

import model.ImageRequestModel
import model.ImageResponseModel

interface ImageInterface {
    suspend fun getImage(imageRequestModel: ImageRequestModel): ImageResponseModel?
    suspend fun getAllCoverImageList(): List<ImageResponseModel?>
    suspend fun gelCoverImage(index: Int): ImageResponseModel?
}