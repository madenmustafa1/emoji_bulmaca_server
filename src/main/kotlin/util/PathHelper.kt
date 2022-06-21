package util

enum class CategoryId { _0, POP, RAP }

class PathHelper {

    private val pop: String = "pop"
    private val rap: String = "rap"

    fun pathHelper(categoryId: Int?): String? {
        if (categoryId == null) return null
        if (categoryId == CategoryId.POP.ordinal) return pop
        if (categoryId == CategoryId.RAP.ordinal) return rap
        return null
    }
}