package vm

import service.ImageService
import io.javalin.http.Context
import kotlinx.coroutines.*
import model.ErrorModel
import model.add_emoji.AddEmojiModel
import model.emoji.EmojiRequestModel
import model.login.LoginRequestModel
import model.login.LoginResponseModel
import util.Constants
import util.TokenUtil

class RouteVM {
    private val imageService = ImageService()
    private val token = TokenUtil()

    fun login(context: Context) {
        try {
            val body = context.bodyAsClass(LoginRequestModel::class.java)
            with(Constants) {
                if (this.loginKey == body.key &&
                    this.username == body.username &&
                    this.password == body.password
                ) {
                    val token = TokenUtil().createToken(body.username)
                    token?.let {
                        context.json(LoginResponseModel(token = token))
                        context.status(200)
                    } ?: run {
                        context.status(404)
                    }
                    return
                }
                context.status(404)
            }
        } catch (e: java.lang.Exception) {
            context.status(404)
        }
    }

    fun getImage(context: Context) = runBlocking {
        val header = context.header("Authorization")
        if (!token.verifyToken(header)) {
            context.status(404)
            return@runBlocking
        }

        val result = CoroutineScope(Dispatchers.IO).async {
            try {
                val body = context.bodyAsClass(EmojiRequestModel::class.java)
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

    fun getCategoryList(context: Context) = runBlocking {
        val header = context.header("Authorization")
        if (!token.verifyToken(header)) {
            context.json(ErrorModel())
            context.status(401)
            return@runBlocking
        }

        val result = CoroutineScope(Dispatchers.IO).async {
            try {
                return@async imageService.getCategoryList()
            } catch (e: java.lang.Exception) {
                return@async null
            }
        }
        result.await()?.let {
            context.json(it)
            context.status(200)
        } ?: run {
            context.json(ErrorModel())
            context.status(404)
        }
    }



    fun addEmojiUserRequest(context: Context) = runBlocking {
        val header = context.header("Authorization")
        if (!token.verifyToken(header)) {
            context.status(404)
            return@runBlocking
        }

        val result = CoroutineScope(Dispatchers.IO).async {
            try {
                val body = context.bodyAsClass(AddEmojiModel::class.java)
                return@async imageService.addEmojiUserRequest(body)
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