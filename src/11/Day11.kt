package `11`

import readInput
import readTestInput
import java.util.*

const val DAY = "11"

fun main() {

    fun part1And2(input: List<MutableList<Int>>){
        var numFlashes = 0
        val currentlyFlashing = LinkedList<Pair<Int,Int>>()
        var synced = false
        var step = 0

        fun getNeighbors(octopus: Pair<Int, Int>): List<Pair<Int,Int>> {
            val adjacentOctopuses = listOf(
                Pair(0 + octopus.first, -1 + octopus.second),
                Pair(0 + octopus.first, 1 + octopus.second),
                Pair(1 + octopus.first, 0 + octopus.second),
                Pair(-1 + octopus.first, 0 + octopus.second),
                Pair(-1 + octopus.first, -1 + octopus.second),
                Pair(1 + octopus.first, 1 + octopus.second),
                Pair(-1 + octopus.first, 1 + octopus.second),
                Pair(1 + octopus.first, -1 + octopus.second)
            )

            return adjacentOctopuses.filter {
                it.first >= 0 && it.first < input.size && it.second >= 0 && it.second < input[0].size
            }

        }

        while (!synced) {
            // increment all by 1
            input.forEachIndexed { row, _ ->
                input[row].forEachIndexed { col, _ ->
                    input[row][col] += 1
                    if (input[row][col] > 9) {
                        currentlyFlashing.add(Pair(row, col))
                    }
                }
            }

            while (currentlyFlashing.size > 0) {
                val position = currentlyFlashing.pop()
                if (input[position.first][position.second] > 9) {
                    numFlashes++
                    input[position.first][position.second] = 0
                    getNeighbors(position).forEach { neighborOctopus ->
                        if (input[neighborOctopus.first][neighborOctopus.second] != 0) {
                            input[neighborOctopus.first][neighborOctopus.second] += 1
                        }
                        if (input[neighborOctopus.first][neighborOctopus.second] > 9) {
                            currentlyFlashing.addLast(neighborOctopus)
                        }
                    }
                }
            }
            val summedRows = input.map { it.sum() }
            if (summedRows.sum() == 0) {
                synced = true
                println("Synched on step ${step + 1}")
            }
            if (step + 1 == 100) {
                println("Flashes after 100 steps: $numFlashes")
            }
            step++
        }
    }


    val testInput = readTestInput(DAY).map { line -> line.toList().map {it.toString().toInt() }.toMutableList() }

    part1And2(testInput)

    val input = readInput(DAY).map { line -> line.toList().map {it.toString().toInt() }.toMutableList() }
    part1And2(input)

}
