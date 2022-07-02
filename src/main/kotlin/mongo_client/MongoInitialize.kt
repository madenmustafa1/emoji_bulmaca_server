package mongo_client

import com.google.gson.Gson
import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import model.add_emoji.AddEmojiModel
import model.emoji.EmojiRequestModel
import model.emoji.EmojiResponseModel
import org.bson.Document
import util.ImageUtil
import util.PathHelper


class MongoInitialize: MongoClientInterface {

    companion object {
        private val pathHelper = PathHelper()
        private val imageUtil = ImageUtil()
        private var database: MongoDatabase? = null
        init {
            val mongoClient = MongoClient("localhost", 27017)
            database = mongoClient.getDatabase("emoji-bulmaca")
        }
    }


    override suspend fun getEmojiInfo(emojiRequestModel: EmojiRequestModel?): EmojiResponseModel? {
        val path: String = pathHelper.pathHelper(categoryId = emojiRequestModel?.categoryId) ?: return null
        val collection: MongoCollection<Document> = database!!.getCollection(path)
        var model: EmojiResponseModel? = null
        collection.find(eq("index", emojiRequestModel?.index))
            .forEach {
                val gson = Gson()
                model = gson.fromJson(it.toJson(), EmojiResponseModel::class.java)
            }
        return model
    }

    override suspend fun getCategoryInfo(emojiRequestModel: EmojiRequestModel?): EmojiResponseModel? {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryList(): List<EmojiResponseModel?> {
        val collection: MongoCollection<Document> = database!!.getCollection("category")
        val categoryEmojiList = arrayListOf<EmojiResponseModel>()

        collection.find().forEach {
            val gson = Gson()
            val model = gson.fromJson(it.toJson(), EmojiResponseModel::class.java)
            model.image = imageUtil.imgPathToBase64(pathName = "category", imgName = model.singer)

            if (model.image == "") {
                model.image = imageUtil.imgPathToBase64(pathName = "category", imgName = "song")
            }

            categoryEmojiList.add(model)
        }
        return categoryEmojiList
    }


    override suspend fun addEmoji(model: AddEmojiModel): Boolean {
        if (database == null) return false
        try {
            val collection: MongoCollection<Document> = database!!.getCollection("")
            val imageResponse = EmojiResponseModel(
                answer = "",
                singer = "",
                index = 2,
                categoryId = 1,
                image = ""
            )

            val document = Document("name", imageResponse.singer + " - " + imageResponse.answer)
                .append("answer", imageResponse.answer)
                .append("singer", imageResponse.singer)
                .append("index", imageResponse.index)
                .append("contentOwner", imageResponse.contentOwner)
                .append("categoryId", imageResponse.categoryId)
                .append("image", imageResponse.image)

            collection.insertOne(document)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override suspend fun addCategory(): Boolean {
        return false
    }

    override suspend fun addEmojiUserRequest(model: AddEmojiModel): Boolean {
        if (database == null) return false
        return try {
            val collection: MongoCollection<Document> = database!!.getCollection("add_emoji_user_request")
            val document = Document("name", model.uuid)
                .append("emojiName", model.emojiName)
                .append("read", model.read)
                .append("uuid", model.uuid)
                .append("date", model.date)

            collection.insertOne(document)
            true
        } catch (e: Exception) {
            false
        }
    }
}