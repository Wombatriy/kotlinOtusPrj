class FromContext {
    var from: String = ""

    fun check() {
        if (from.isEmpty()) {
            throw RuntimeException("from is empty")
        }
    }

    override fun toString(): String {
        return this.from
    }
}