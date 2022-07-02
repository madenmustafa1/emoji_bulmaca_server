package mongo_client

import model.add_emoji.AddEmojiModel
import model.emoji.EmojiRequestModel
import model.emoji.EmojiResponseModel

interface MongoClientInterface {
    suspend fun getEmojiInfo(emojiRequestModel: EmojiRequestModel?): EmojiResponseModel?
    suspend fun getCategoryInfo(emojiRequestModel: EmojiRequestModel?): EmojiResponseModel?
    suspend fun getCategoryList(): List<EmojiResponseModel?>?
    suspend fun addEmoji(model: AddEmojiModel): Boolean
    suspend fun addCategory(): Boolean
    suspend fun addEmojiUserRequest(model: AddEmojiModel): Boolean
}