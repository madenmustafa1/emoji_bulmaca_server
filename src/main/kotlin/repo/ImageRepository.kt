package repo

import model.add_emoji.AddEmojiModel
import model.emoji.EmojiRequestModel
import model.emoji.EmojiResponseModel
import mongo_client.MongoClientInterface
import mongo_client.MongoInitialize
import service.ImageInterface
import util.ImageUtil
import util.PathHelper

class ImageRepository : ImageInterface {
    private val imageUtil = ImageUtil()
    private val pathHelper = PathHelper()
    private val mongoDatabase: MongoInitialize = MongoInitialize()
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
        return mongoDatabase.getCategoryList()
    }

    override suspend fun gelCoverImage(index: Int): EmojiResponseModel {
        TODO("Not yet implemented")
    }

    override suspend fun addEmojiUserRequest(model: AddEmojiModel): Boolean {
        return mongoDatabase.addEmojiUserRequest(model)
    }
}