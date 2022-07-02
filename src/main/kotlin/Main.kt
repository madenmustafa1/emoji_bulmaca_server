
import io.javalin.Javalin
import mongo_client.MongoInitialize
import util.Route
import vm.RouteVM

fun main() {
    val app = Javalin.create().start(7070)
    val routeVM = RouteVM()

    MongoInitialize()

    with(Route) {
        app.get("/") {
            it.status(200)
        }
        app.post(SIGN_IN) { routeVM.login(it) }
        app.post(GET_SONG) { routeVM.getImage(it) }
        app.get(CATEGORY) { routeVM.getCategoryList(it) }
        app.post(ADD_EMOJI) { routeVM.addEmojiUserRequest(it) }
    }
}
