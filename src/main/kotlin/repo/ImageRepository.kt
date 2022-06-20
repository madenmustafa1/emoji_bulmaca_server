package repo

import model.image.ImageRequestModel
import model.image.ImageResponseModel
import service.ImageInterface
import util.ImageUtil
import util.PathHelper

class ImageRepository : ImageInterface {
    private val imageUtil = ImageUtil()
    private val pathHelper = PathHelper()

    override suspend fun getImage(imageRequestModel: ImageRequestModel): ImageResponseModel? {
        //index

        val pathRoute = pathHelper.pathHelper(imageRequestModel.categoryId) ?: return null

        val image = imageUtil.imgPathToBase64(pathName = pathRoute, imgName = imageRequestModel.index ?: 1)
        return ImageResponseModel(
            singer = singer,
            index = imageRequestModel.index ?: 0,
            answer = answer,
            contentOwner = contentOwner,
            image = image,
            categoryId = imageRequestModel.categoryId
        )
    }

    override suspend fun getAllCoverImageList(): List<ImageResponseModel> {
        TODO("Not yet implemented")
    }

    override suspend fun gelCoverImage(index: Int): ImageResponseModel {
        TODO("Not yet implemented")
    }


    //database em.
    private val pathName = "pathName"
    private val imgName = "test_png.png"
    private val singer = "ezhel"
    private val answer = "ağlattın"
    private val indexDB = 1
    private val contentOwner = "madenapps"
    private val categoryId = 1
}