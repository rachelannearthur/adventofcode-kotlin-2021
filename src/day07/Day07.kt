package day07

import readInput
import readTestInput

const val DAY = "07"

fun main() {
    fun part1(input: List<Int>): Int {
        var minFuel = Int.MAX_VALUE
        (0..(input.maxOrNull() ?: 0)).forEach { fuel ->
            var totalDiff = 0
            input.forEach { crab ->
                totalDiff += kotlin.math.abs(crab - fuel)
            }
            if (totalDiff < minFuel) minFuel = totalDiff
        }
        return minFuel
    }

    fun part2(input: List<Int>): Int {
        var minFuel = Int.MAX_VALUE
        (0..(input.maxOrNull() ?: 0)).forEach { fuel ->
            var totalDiff = 0
            input.forEach { crab ->
                // if diff = 3 need to add 1 + 2 + 3 to for 3 add
                val diff = kotlin.math.abs(crab - fuel)
//                original inefficient solution
//                (diff downTo 0).forEach {
//                    totalDiff += it
//                }
                totalDiff += (diff + 1) * diff / 2
            }
            if (totalDiff < minFuel) minFuel = totalDiff
        }
        return minFuel
    }

    val testInput = readTestInput(DAY)[0].split(',').map { it.toInt() }

    println(part1(testInput))
    println(part2(testInput))

//    check(part1(testLines) == 5)
//    println(part2(testLines))
//    check(part2(testLines) == 12)

    val input = readInput(DAY)[0].split(',').map { it.toInt() }

    println(part1(input))
    println(part2(input))
//
//
//    println(part1(lines))
//    println(part2(lines))
}
