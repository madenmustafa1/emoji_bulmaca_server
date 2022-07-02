package service

import model.add_emoji.AddEmojiModel
import model.emoji.EmojiRequestModel
import model.emoji.EmojiResponseModel

interface ImageInterface {
    suspend fun getImage(emojiRequestModel: EmojiRequestModel): EmojiResponseModel?
    suspend fun getCategoryList(): List<EmojiResponseModel?>
    suspend fun gelCoverImage(index: Int): EmojiResponseModel?
    suspend fun addEmojiUserRequest(model: AddEmojiModel): Boolean
}