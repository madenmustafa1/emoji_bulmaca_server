
import io.javalin.Javalin
import mongo_client.MongoInitialize
import util.Route
import vm.RouteVM

fun main() {
    val app = Javalin.create().start(7070)
    val routeVM = RouteVM()

    MongoInitialize()

    with(Route) {
        app.get(SIGN_IN) { routeVM.login(it) }
        app.get(GET_SONG) { routeVM.getImage(it) }
        app.get(CATEGORY) { routeVM.getImage(it) }
    }
}
