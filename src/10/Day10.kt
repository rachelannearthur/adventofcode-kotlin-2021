package `10`

import readInput
import readTestInput

const val DAY = "10"

fun main() {
    val openingTags = listOf('(', '[', '{', '<')
    val closingTags = listOf(')', ']', '}', '>')

//    fun part2()

    fun part1(input: List<String>) {
        val scores = mutableListOf<Long>()
        var total = 0
        input.forEach loop@{ line ->
            val opened = mutableListOf<Char>()
            line.forEach { c ->
                if (c in openingTags) {
                    opened.add(c)
                } else if (c in closingTags && c == closingTags[openingTags.indexOf(opened.last())]) {
                    opened.removeLast()
                } else if (c in closingTags && c != closingTags[openingTags.indexOf(opened.last())]){
                    when (c) {
                        ')' -> total += 3
                        ']' -> total += 57
                        '}' -> total += 1197
                        '>' -> total += 25137
                    }
                    return@loop
                }
            }
            val completionString = opened.reversed().map { tag -> closingTags[openingTags.indexOf(tag)]}
            scores.add(completionString.fold(0L) { acc, c ->
                acc * 5L + (closingTags.indexOf(c) + 1L )
            })
        }
        scores.sort()
        println("part1: $total")
        println("part2: ${scores[scores.size / 2]}")
    }



    val testInput = readTestInput(DAY)
    part1(testInput)

    val input = readInput(DAY)
    part1(input)

}
