
import io.javalin.Javalin
import util.Route
import util.TokenUtil
import vm.ImageVM

fun main() {
    val app = Javalin.create().start(7070)
    val imageVM = ImageVM()

    with(Route) {
        app.get(GET_SONG) { imageVM.getImage(it) }
        app.get(CATEGORY) { imageVM.getImage(it) }
    }
}
