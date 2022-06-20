package mongo_client

import model.image.ImageResponseModel

interface MongoClientInterface {
    suspend fun getEmojiInfo(): ImageResponseModel?
    suspend fun getCategoryInfo(): ImageResponseModel?
    suspend fun addEmoji(): Boolean
}