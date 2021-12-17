package day05

import readInput
import readTestInput

const val DAY = "05"

fun main() {
    fun part1(input: List<Line>): Int {
        val overlappingPoints = mutableMapOf<Pair<Int, Int>, Int>()
        input.forEach { line ->
            if (line.isVertical || line.isHorizontal) {
                val allPoints = line.getAllPoints()
                for (point in allPoints) {
                    overlappingPoints[point] = (overlappingPoints[point] ?: 0) + 1
                }
            }
        }
        return overlappingPoints.values.count { it >= 2 }
    }

    fun part2(input: List<Line>): Int {
        val overlappingPoints = mutableMapOf<Pair<Int, Int>, Int>()
        input.forEach { line ->
            if (line.isVertical || line.isHorizontal || line.isDiagonal) {
                val allPoints = line.getAllPoints(true)
                for (point in allPoints) {
                    overlappingPoints[point] = (overlappingPoints[point] ?: 0) + 1
                }
            }
        }
        return overlappingPoints.values.count { it >= 2 }
    }

    val testInput = readTestInput(DAY)
    val testLines = testInput.map { line ->
        line.split(" -> ").map { point ->
            point.split (",")
        }.map { coordinates ->
            Pair(coordinates[0].toInt(), coordinates[1].toInt())
        }
    }.map {
        Line(p1 = it[0], p2 = it[1])
    }

    println(part1(testLines))
    check(part1(testLines) == 5)
    println(part2(testLines))
    check(part2(testLines) == 12)

    val input = readInput(DAY)
    val lines = input.map { line ->
        line.split(" -> ").map { point ->
            point.split (",")
        }.map { coordinates ->
            Pair(coordinates[0].toInt(), coordinates[1].toInt())
        }
    }.map {
        Line(p1 = it[0], p2 = it[1])
    }
    println(part1(lines))
    println(part2(lines))
}

data class Line(val p1: Pair<Int, Int>, val p2: Pair<Int, Int>) {
    val isVertical = p1.first == p2.first
    val isHorizontal = p1.second == p2.second
    val isDiagonal = kotlin.math.abs(p1.first - p2.first) == kotlin.math.abs(p1.second - p2.second)

    fun getAllPoints(includeDiagonal: Boolean = false): List<Pair<Int, Int>> {
        val points = mutableListOf<Pair<Int,Int>>()

        if (isHorizontal || isVertical) {
            val nums = if (p1.first > p2.first) p1.first.downTo(p2.first) else p1.first..p2.first
            val yNums =  if (p1.second > p2.second) p1.second.downTo(p2.second) else p1.second..p2.second

            for (x in nums) {
                for (y in yNums) {
                    points.add(Pair(x, y))
                }
            }
        } else if (isDiagonal && includeDiagonal) {
            val nums = if (p1.first > p2.first) p1.first.downTo(p2.first) else p1.first..p2.first
            for (x in nums) {
                val y = if (p2.second > p1.second) {
                    p1.second + kotlin.math.abs(x - p1.first)
                } else {
                    p1.second - kotlin.math.abs(x - p1.first)
                }
                points.add(Pair(x, y))
            }
        }
        return points
    }
}
