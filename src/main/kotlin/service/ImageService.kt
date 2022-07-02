package service

import model.add_emoji.AddEmojiModel
import model.emoji.EmojiRequestModel
import model.emoji.EmojiResponseModel
import repo.ImageRepository

class ImageService: ImageInterface {

    private val imageRepository = ImageRepository()

    override suspend fun getImage(emojiRequestModel: EmojiRequestModel): EmojiResponseModel? {
        return imageRepository.getImage(emojiRequestModel)
    }

    override suspend fun getCategoryList(): List<EmojiResponseModel?> {
        return imageRepository.getCategoryList()
    }

    override suspend fun gelCoverImage(index: Int): EmojiResponseModel? {
        TODO("Not yet implemented")
    }

    override suspend fun addEmojiUserRequest(model: AddEmojiModel): Boolean {
        return imageRepository.addEmojiUserRequest(model)
    }
}