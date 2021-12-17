package day03

import readInput
import readTestInput

const val DAY = "03"

fun main() {
    fun part1(input: List<String>): Int {
        val columns = input[0].indices
        val gamma = buildString {
            for (column in columns) {
                var zeroes = 0
                var ones = 0
                for (line in input) {
                    if (line[column] == '0') zeroes++ else ones++
                }
                append(if (zeroes > ones) "0" else "1")
            }
        }
        val epsilon = gamma.asIterable().joinToString("") { if (it == '0') "1" else "0" }

        return gamma.toInt(radix = 2) * epsilon.toInt(radix = 2)
    }

    fun part2(input: List<String>): Int {
        fun oxyResult(): String {
            val columns = input[0].indices
            var inputs = input
            for (column in columns) {
                var zeroes = 0
                var ones = 0
                for (line in inputs) {
                    if (line[column] == '0') zeroes++ else ones++
                }
                val criteria = if (zeroes > ones) '0' else '1'

                inputs = inputs.filter { it[column] == criteria }
                if (inputs.size == 1) {
                    break
                }
            }
            return inputs[0]
        }
        fun co2Result(): String {
            val columns = input[0].indices
            var inputs = input
            for (column in columns) {
                var zeroes = 0
                var ones = 0
                for (line in inputs) {
                    if (line[column] == '0') zeroes++ else ones++
                }
                val criteria = if (zeroes > ones) '1' else '0'
                inputs = inputs.filter { it[column] == criteria }
                if (inputs.size == 1) {
                    break
                }
            }
            return inputs[0]
        }

        return oxyResult().toInt(radix = 2) * co2Result().toInt(radix = 2)
    }

    val testInput = readTestInput(DAY)
    println(part1(testInput))
    check(part1(testInput) == 198)
    println(part2(testInput))
    check(part2(testInput) == 230)

    val input = readInput(DAY)

    println(part1(input))
    println(part2(input))
}
