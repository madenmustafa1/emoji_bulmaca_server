package mongo_client

import com.mongodb.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import kotlinx.coroutines.*
import model.image.ImageResponseModel
import org.bson.Document

class MongoInitialize: MongoClientInterface {

    companion object {
        private var database: MongoDatabase? = null
        init {
            val mongoClient = MongoClient("localhost", 27017)
            database = mongoClient.getDatabase("emoji-bulmaca")
        }
    }

    override suspend fun getEmojiInfo(): ImageResponseModel? {
        TODO("Not yet implemented")
    }

    override suspend fun getCategoryInfo(): ImageResponseModel? {
        TODO("Not yet implemented")
    }



    override suspend fun addEmoji(): Boolean {
        if (database == null) return false
        try {
            val collection: MongoCollection<Document> = database!!.getCollection("")
            val imageResponse = ImageResponseModel(
                answer = "",
                singer = "", index = 2,
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
}