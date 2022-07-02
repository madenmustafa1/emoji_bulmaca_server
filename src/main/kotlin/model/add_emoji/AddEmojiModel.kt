package model.add_emoji

import java.util.*

data class AddEmojiModel(
    val emojiName: String,
    val read: Boolean = false,
    val uuid: String = UUID.randomUUID().toString(),
    val date: Date = Date()
)
