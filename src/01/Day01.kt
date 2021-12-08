package `01`

import readInput
import readTestInput

const val DAY = "01"

fun main() {
    fun part1(input: List<Int>): Int {

        // original solution

//        var result = 0
//        var oldDepth = 0
//        input.forEachIndexed { index, newDepth ->
//            if (index > 0) {
//                if (oldDepth < newDepth) result += 1
//            }
//            oldDepth = newDepth
//        }

        // idiomatic solution
        return input.windowed(2).count() {
            (a, b) -> a < b
        }
    }

    fun part2(input: List<Int>): Int {
        return input.windowed(3).windowed(2).count() {
            (a, b) -> a.sum() < b.sum()
        }
    }

    val testInput = readTestInput(DAY).map { it.toInt() }
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput(DAY).map { it.toInt() }

    println(part1(input))
    println(part2(input))
}
