package repo

import model.ImageResponseModel
import service.ImageInterface
import util.ImageUtil

class ImageRepository : ImageInterface {
    private val imageUtil = ImageUtil()

    override suspend fun getImage(index: Int): ImageResponseModel? {
        //index

        val image = imageUtil.imgPathToBase64(pathName = pathName, imgName = imgName)
        return ImageResponseModel(
            singer = singer,
            index = indexDB,
            answer = answer,
            contentOwner = contentOwner,
            image = image
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
}