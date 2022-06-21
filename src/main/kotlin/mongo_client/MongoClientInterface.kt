package mongo_client

import model.emoji.EmojiRequestModel
import model.emoji.EmojiResponseModel

interface MongoClientInterface {
    suspend fun getEmojiInfo(emojiRequestModel: EmojiRequestModel?): EmojiResponseModel?
    suspend fun getCategoryInfo(emojiRequestModel: EmojiRequestModel?): EmojiResponseModel?
    suspend fun getCategoryList(): List<EmojiResponseModel?>?
    suspend fun addEmoji(): Boolean
    suspend fun addCategory(): Boolean
}