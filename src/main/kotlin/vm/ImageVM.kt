package vm

import service.ImageService
import io.javalin.http.Context
import kotlinx.coroutines.*
import model.ImageRequestModel

class ImageVM {
    private val imageService = ImageService()

    fun getImage(context: Context) = runBlocking {
        val result = CoroutineScope(Dispatchers.IO).async {
            try {
                val body = context.bodyAsClass(ImageRequestModel::class.java)
                return@async imageService.getImage(index = body.index ?: 1)
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