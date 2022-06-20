package util

enum class CategoryId { _0, POP, RAP }

class PathHelper {

    private val pop: String = "pop"
    private val rap: String = "rap"

    fun pathHelper(pathIndex: Int?): String? {
        if (pathIndex == null) return null
        if (pathIndex == CategoryId.POP.ordinal) return pop
        if (pathIndex == CategoryId.RAP.ordinal) return rap
        return null
    }
}