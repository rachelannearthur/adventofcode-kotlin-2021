package day08

import readInput
import readTestInput

const val DAY = "08"

fun main() {
    // creates sig 1, 4, 7
    var sig1 = "".toSortedSet()
    var sig4 = "".toSortedSet()
    var sig7 = "".toSortedSet()

    fun createCypher(first3: List<String>) {
        first3.forEach { digit ->
            when (digit.length) {
                2 -> sig1 = digit.toSortedSet()
                3 -> sig7 = digit.toSortedSet()
                4 -> sig4 = digit.toSortedSet()
                else -> return
            }
        }
    }

    fun decodeDigits(nonSortedDigits: List<String>)= nonSortedDigits.joinToString("") { digit ->
            when (digit.length) {
                2 ->  "1".also { sig1 = digit.toSortedSet()}
                3 -> "7".also { sig7 = digit.toSortedSet() }
                4 -> "4".also { sig4 = digit.toSortedSet() }
                5 -> {
                    val digSort = digit.toSortedSet()
                    if (digSort.containsAll(sig1)) {
                        "3"
                    } else {
                        if (digit.filter { it in sig4 }.length == 3) {
                            "5"
                        } else {
                            "2"
                        }
                    }
                }
                6 -> {
                    val digSort = digit.toSortedSet()
                    if (digSort.containsAll(sig4) && digSort.containsAll(sig7))
                        "9"
                    else if (digSort.containsAll(sig1))
                        "0"
                    else
                        "6"
                }
                7 -> "8"
                else -> ""
            }
        }

    fun part1(input: List<List<String>>): Int {
        val targets = listOf(2,3,4,7)
        var sum = 0
        input.forEach { note ->
            sum += note.count { it.length in targets }
        }
        return sum
    }

    fun part2(input: List<List<List<String>>>): Int {
        var sum = 0
        input.forEach { note ->
            createCypher(note[0].sortedBy { it.length }.subList(0,3))
            sum += decodeDigits(note[1]).toInt()
        }
        return sum
    }

    val testInput = readTestInput(DAY).map { line ->
        line.split("|").map { section ->
            section.split(" ").filterNot {
                it.isEmpty()
            }
        }[1]
    }
    val testInput2 = readTestInput(DAY).map { line ->
        line.split("|").map { section ->
            section.split(" ").filterNot {
                it.isEmpty()
            }
        }
    }

    println(part1(testInput))
    println(part2(testInput2))

    val input = readInput(DAY).map { line ->
        line.split("|").map { section ->
            section.split(" ").filterNot {
                it.isEmpty()
            }
        }[1]
    }
    val input2 = readInput(DAY).map { line ->
        line.split("|").map { section ->
            section.split(" ").filterNot {
                it.isEmpty()
            }
        }
    }

    println(part1(input))
    println(part2(input2))

}
