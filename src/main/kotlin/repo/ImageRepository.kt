package repo

import model.emoji.EmojiRequestModel
import model.emoji.EmojiResponseModel
import mongo_client.MongoInitialize
import service.ImageInterface
import util.ImageUtil
import util.PathHelper

class ImageRepository : ImageInterface {
    private val imageUtil = ImageUtil()
    private val pathHelper = PathHelper()

    override suspend fun getImage(emojiRequestModel: EmojiRequestModel): EmojiResponseModel? {
        try {
            val pathRoute = pathHelper.pathHelper(emojiRequestModel.categoryId) ?: return null
            val emojiResponseModel: EmojiResponseModel = MongoInitialize().getEmojiInfo(emojiRequestModel) ?: return null

            val image = imageUtil.imgPathToBase64(pathName = pathRoute, imgName = emojiRequestModel.index.toString() ?: "1")
            if (image == "") return null

            emojiResponseModel.image = image
            return emojiResponseModel
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun getCategoryList(): List<EmojiResponseModel?> {
        //Go to database
        return MongoInitialize().getCategoryList()
    }

    override suspend fun gelCoverImage(index: Int): EmojiResponseModel {
        TODO("Not yet implemented")
    }
}