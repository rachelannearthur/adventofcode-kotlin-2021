package `09`

import readInput
import readTestInput

const val DAY = "09"

fun main() {
    val lowPoints = hashMapOf<Pair<Int, Int>, Int>()
    fun isLowPoint(input: List<List<Int>>, height: Int, row: Int, col: Int): Boolean {
        if (height == 9) return false
        var isLowPoint = true
        if (row > 0) {
            if (height > input[row - 1][col])
                isLowPoint = false
        }
        if (row + 1 < input.size) {
            if (height > input[row + 1][col])
                isLowPoint = false

        }

        if (col > 0) {
            if (height > input[row][col - 1])
                isLowPoint = false

        }

        if (col + 1 < input[0].size) {
            if (height > input[row][col + 1])
                isLowPoint = false
        }
        return isLowPoint
    }

    fun getValue(input: List<MutableList<Int>>, point: Pair<Int,Int>): Int {
        val numRows = input.size
        val numCols = input[0].size
        if (point.first < 0 || point.first >= numRows) {
            return 10
        }
        if (point.second < 0 || point.second >= numCols) {
            return 10
        }
        return input[point.first][point.second]
    }

    fun checkSurrounding(input: List<MutableList<Int>>, lowPoint: Pair<Int,Int>) {
        val pointsToCheck = mutableListOf(lowPoint)
        val checked = mutableListOf<Pair<Int,Int>>()
        while (pointsToCheck.isNotEmpty()) {
            val point = pointsToCheck[0]
            val x = point.first
            val y = point.second
            if (!checked.contains(point) && getValue(input, point) < 9) {
                lowPoints[lowPoint] = (lowPoints[lowPoint] ?: 1) + 1
                checked.add(point)
                listOf(Pair(x + 1, y), Pair(x - 1, y), Pair(x, y + 1), Pair(x, y - 1)).forEach {
                    pointsToCheck.add(it)
                }
            }
            pointsToCheck.removeAt(0)
        }
        checked.forEach {
            input[it.first][it.second] = 9
        }
    }

    fun part1(input: List<List<Int>>): Int {
        var riskFactor = 0
        input.forEachIndexed { row, line ->
            line.forEachIndexed { col, height ->
                if (isLowPoint(input, height, row, col))
                    // create map of low points with initial basin size of 1
                    lowPoints.getOrPut(Pair(row, col)) { 0 }
                    riskFactor += height + 1
            }
        }
        return riskFactor
    }

    fun part2(input: List<List<Int>>): Int {
        val list = input.map {it.toMutableList()}
        lowPoints.keys.forEach { lowPoint ->
            checkSurrounding(list, lowPoint)
        }
        val basins = lowPoints.values.sortedDescending().subList(0, 3)
        return basins[0] * basins[1] * basins[2]
    }

    val testInput = readTestInput(DAY).map { it.map { it.toString().toInt() } }
    println(part1(testInput))
    println(part2(testInput))


    val input = readInput(DAY).map { it.map { it.toString().toInt() } }
    println(part1(input))
    println(part2(input))

}
