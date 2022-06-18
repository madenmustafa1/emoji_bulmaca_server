package util

import extensions.toBase64
import org.eclipse.jetty.util.Loader

class ImageUtil {

    suspend fun imgPathToBase64(pathName: String, imgName: String): String{

        //-> drawable/$pathName/$imgName
        val pathBytes = Loader.getResource("drawable/photos/$imgName")?.readBytes()
        return pathBytes?.toBase64() ?: ""
    }
}