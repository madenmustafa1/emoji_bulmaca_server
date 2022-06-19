package vm

import service.ImageService
import io.javalin.http.Context
import kotlinx.coroutines.*
import model.ImageRequestModel
import util.TokenUtil

class ImageVM {
    private val imageService = ImageService()
    private val token = TokenUtil()

    fun getImage(context: Context) = runBlocking {
        val header = context.header("Authorization")
        if (token.verifyToken(header)) {
            context.status(404)
            return@runBlocking
        }

        val result = CoroutineScope(Dispatchers.IO).async {
            try {
                val body = context.bodyAsClass(ImageRequestModel::class.java)
                return@async imageService.getImage(body)
            } catch (e: java.lang.Exception) {
                return@async null
            }
        }
        result.await()?.let {
            context.json(it)
            context.status(200)
        } ?: run {
            context.status(404)
        }
    }
}