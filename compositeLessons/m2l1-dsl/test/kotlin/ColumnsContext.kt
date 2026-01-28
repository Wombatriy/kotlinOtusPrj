class ColumnsContext {
    var columns: MutableList<String> = mutableListOf()

    override fun toString(): String {
        if (columns.isEmpty()) {
            columns.add("*")
        }
        return columns.joinToString(", ")
    }
}