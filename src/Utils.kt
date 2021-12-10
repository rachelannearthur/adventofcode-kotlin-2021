import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(day: String) = File("src/$day", "Day${day}_input.txt").readLines()

fun readTestInput(day:String) = File("src/$day", "Day${day}_test.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

// transposes array of rows into array of columns
fun <T> List<List<T>>.transpose(): List<List<T>> {
    val cols = mutableListOf<MutableList<T>>()
    this.forEach { row ->
        row.forEachIndexed { i, value ->
            if (cols.size <= i) cols.add(i, mutableListOf(value))
            else cols[i].add(value)
        }
    }
    return cols
}