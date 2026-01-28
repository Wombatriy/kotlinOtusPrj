class WhereContext {
    var where: MutableList<String> = mutableListOf()
    var orEnabled: Boolean = false

//    operator fun String.unaryPlus() {
//        where = Where.valueOf(this)
//    }

    infix fun String.eq(other: Any?) {
        if (other == null) {
            where.add("$this is null")
        } else if (other is String) {
            if (other == "null") {
                where.add("$this is null")
            } else {
                where.add("$this = '$other'")
            }
        } else if (other is Number) {
            where.add("$this = '$other'")
        }
    }

    infix fun String.nonEq(other: Any?) {
        if (other == null) {
            where.add("$this !is null")
        } else if (other is String) {
            if (other == "null") {
                where.add("$this !is null")
            } else {
                where.add("$this != $other")
            }
        } else if (other is Number) {
            where.add("$this != $other")
        }
    }

    fun or(block: WhereContext.() -> Unit) {
        this.apply(block)
        orEnabled = true
    }

    override fun toString(): String {
        return if (this.where.isEmpty()) {
            ""
        } else if (this.where.size == 1) {
            " where ${where[0]}"
        } else if (this.orEnabled) {
            " where (${where.joinToString(" or ")})"
        } else {
            " where (${where.joinToString(" and ")})"
        }
    }
}