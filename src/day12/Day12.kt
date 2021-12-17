package day12

import readInput
import readTestInput

const val DAY = "12"

fun main() {

    fun part1(caves: Map<String, List<String>>, currentPath: List<String>): Int {
        var numPaths = 0
        caves[currentPath.last()]?.forEach { cave ->
            if (cave.first().isUpperCase() || cave !in currentPath) {
                val count = if (cave == "end") 1 else part1(caves, currentPath + listOf(cave))
                numPaths += count
            }
        }
        return numPaths
    }

    fun part2(caves: Map<String, List<String>>, currentPath: List<String>): Int {
        var numPaths = 0
        caves[currentPath.last()]?.forEach { cave ->
            val count = if (cave == "end") {
                1
            } else {
                if (cave.first().isLowerCase() && cave in currentPath) {
                    part1(caves, currentPath + listOf(cave))
                } else {
                    part2(caves, currentPath + listOf(cave))                }
            }
            numPaths += count
        }
        return numPaths
    }

    val testInput = readTestInput(DAY)
    println(part1(createCaves(testInput), listOf("start")))
    println(part2(createCaves(testInput), listOf("start")))


    val input = readInput(DAY)
    println(part1(createCaves(input), listOf("start")))
    println(part2(createCaves(input), listOf("start")))

}

fun createCaves(input: List<String>): Map<String, List<String>> {
    val cavePaths = input.map { it.split("-") }.map { it[0] to it[1] }
    val caves = mutableMapOf<String, List<String>>()
    cavePaths.forEach { (first, second) ->
        if (second != "start") {
        caves[first] = (caves[first] ?: emptyList()) + listOf(second)
        }
        if (first != "start") {
            caves[second] = (caves[second] ?: emptyList()) + listOf(first)
        }
    }
    caves.remove("end")
    return caves
}

