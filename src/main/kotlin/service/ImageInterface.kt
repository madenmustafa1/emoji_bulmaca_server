package service

import model.ImageResponseModel

interface ImageInterface {
    suspend fun getImage(index: Int): ImageResponseModel?
    suspend fun getAllCoverImageList(): List<ImageResponseModel?>
    suspend fun gelCoverImage(index: Int): ImageResponseModel?
}