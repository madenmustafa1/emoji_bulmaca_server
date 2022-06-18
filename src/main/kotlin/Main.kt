
import io.javalin.Javalin
import util.Route
import vm.ImageVM

fun main() {
    val app = Javalin.create().start(7070)
    val imageVM = ImageVM()

    with(Route) {
        app.get(RAP) { imageVM.getImage(it) }
        app.get(POP) { imageVM.getImage(it) }
    }
}
