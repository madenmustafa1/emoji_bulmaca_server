package util

import extensions.toBase64
import org.eclipse.jetty.util.Loader

class ImageUtil {
    private val photos: String = "photos"

    suspend fun imgPathToBase64(pathName: String, imgName: Int): String{
        val pathBytes = Loader.getResource("drawable/$photos/$pathName/$imgName.png")?.readBytes()
        return pathBytes?.toBase64() ?: ""
    }
}