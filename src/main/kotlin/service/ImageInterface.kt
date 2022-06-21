package service

import model.emoji.EmojiRequestModel
import model.emoji.EmojiResponseModel

interface ImageInterface {
    suspend fun getImage(emojiRequestModel: EmojiRequestModel): EmojiResponseModel?
    suspend fun getCategoryList(): List<EmojiResponseModel?>
    suspend fun gelCoverImage(index: Int): EmojiResponseModel?
}