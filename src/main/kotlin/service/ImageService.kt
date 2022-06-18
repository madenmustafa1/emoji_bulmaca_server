package service

import model.ImageResponseModel
import repo.ImageRepository

class ImageService: ImageInterface {

    private val imageRepository = ImageRepository()

    override suspend fun getImage(index: Int): ImageResponseModel? {
        return imageRepository.getImage(index)
    }

    override suspend fun getAllCoverImageList(): List<ImageResponseModel?> {
        TODO("Not yet implemented")
    }

    override suspend fun gelCoverImage(index: Int): ImageResponseModel? {
        TODO("Not yet implemented")
    }
}