import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class Hw1Sql {

    private fun checkSQL(expected: String, sql: SqlSelectBuilder) {
        assertEquals(expected, sql.build())
    }

    @Test
    fun `simple select all from table`() {
        val expected = "select * from table"

        val real = query {
            from("table")
        }

        checkSQL(expected, real)
    }

    @Test
    fun `check that select can't be used without table`() {
        assertFailsWith<Exception> {
            query {
                select("col_a")
            }.build()
        }
    }

    @Test
    fun `select certain columns from table`() {
        val expected = "select col_a, col_b from table"

        val real = query {
            select("col_a", "col_b")
            from("table")
        }

        checkSQL(expected, real)
    }

    @Test
    fun `select certain columns from table 1`() {
        val expected = "select col_a, col_b from table"

        val real = query {
            select("col_a", "col_b")
            from("table")
        }

        checkSQL(expected, real)
    }

    /**
     * __eq__ is "equals" function. Must be one of char:
     *  - for strings - "="
     *  - for numbers - "="
     *  - for null - "is"
     */
    @Test
    fun `select with complex where condition with one condition`() {
        val expected = "select * from table where col_a = 'id'"

        val real = query {
            from("table")
            where { "col_a" eq "id" }
        }

        val expected2 = "select * from table where col_a = '5'"
        val real2 = query {
            from("table")
            where { "col_a" eq 5 }
        }

        val expected3 = "select * from table where col_a is null"
        val real3 = query {
            from("table")
            where { "col_a" eq "null" }
        }

        val expected4 = "select * from table where col_a is null"
        val real4 = query {
            from("table")
            where { "col_a" eq null }
        }

        checkSQL(expected, real)
        checkSQL(expected2, real2)
        checkSQL(expected3, real3)
        checkSQL(expected4, real4)
    }

    /**
     * __nonEq__ is "non equals" function. Must be one of chars:
     *  - for strings - "!="
     *  - for numbers - "!="
     *  - for null - "!is"
     */
    @Test
    fun `select with complex where condition with two conditions`() {
        val expected = "select * from table where col_a != 0"

        val real = query {
            from("table")
            where {
                "col_a" nonEq 0
            }
        }

        checkSQL(expected, real)
    }

    @Test
    fun `when 'or' conditions are specified then they are respected`() {
        val expected = "select * from table where (col_a = '4' or col_b !is null)"

        val real = query {
            from("table")
            where {
                or {
                    "col_a" eq 4
                    "col_b" nonEq null
                }
            }
        }

        checkSQL(expected, real)
    }
}

private fun query(sql: SqlSelectBuilder.() -> Unit): SqlSelectBuilder {
    return SqlSelectBuilder().apply(sql)
}

class SqlSelectBuilder {
    private var fromContext: FromContext = FromContext()
    private var columnsContext: ColumnsContext = ColumnsContext()
    private var whereContext: WhereContext = WhereContext()

    fun build(): String {
        fromContext.check()
        return "select $columnsContext from $fromContext$whereContext"
    }

    fun where(block: WhereContext.() -> Unit) {
        whereContext.apply(block)
    }

    fun from(str: String) {
        fromContext.apply { from = str }
    }

    fun select(vararg inputColumns: String) {
        columnsContext.apply { columns.addAll(inputColumns) }
    }
}