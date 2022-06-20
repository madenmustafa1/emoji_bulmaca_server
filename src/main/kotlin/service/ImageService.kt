package service

import model.image.ImageRequestModel
import model.image.ImageResponseModel
import repo.ImageRepository

class ImageService: ImageInterface {

    private val imageRepository = ImageRepository()

    override suspend fun getImage(imageRequestModel: ImageRequestModel): ImageResponseModel? {
        return imageRepository.getImage(imageRequestModel)
    }

    override suspend fun getAllCoverImageList(): List<ImageResponseModel?> {
        TODO("Not yet implemented")
    }

    override suspend fun gelCoverImage(index: Int): ImageResponseModel? {
        TODO("Not yet implemented")
    }
}