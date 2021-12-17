package day13

import readInput
import readTestInput

const val DAY = "13"

fun main() {
    fun fold(points: MutableList<Pair<Int, Int>>, fold: Pair<String, Int>) {
        val isXFold = fold.first == "x"
        points.forEachIndexed { index, (x, y) ->
            val newPoint = if (isXFold) {
                if (x > fold.second) (x - (2 * (x - fold.second)) to y) else (x to y)
            } else {
                if (y > fold.second) (x to y - (2 * (y - fold.second))) else (x to y)
            }
            points[index] = newPoint
        }
    }

    fun part1(input: Input) {
        val (points, folds) = input
        fold(points, folds[0])
        println(points.distinct().size)
    }

    fun printCode(points: MutableList<Pair<Int, Int>>){
        val xMax = points.map { it.first }.maxOf { it }
        val yMax = points.map { it.second }.maxOf { it }
        val grid = Array(size = yMax + 1 ) { Array(size = xMax + 1) {" "} }
        points.forEach {
            grid[it.second][it.first] = "#"
        }
        grid.forEach { row ->
            row.forEach {
                print(it)
            }
            println()
        }
    }

    fun part2(input: Input) {
        val (points, folds) = input
        var newPoints = points
        folds.forEach { fold ->
            fold(newPoints, fold)
            newPoints = newPoints.distinct().toMutableList()
        }
        printCode(newPoints)
    }

    fun parseData(data: List<String>): Input {
        val points = mutableListOf<Pair<Int,Int>>()
        val folds = mutableListOf<Pair<String, Int>>()
        data.filterNot {it.contains("fold")}.map { it.split(",")}.mapTo(points) { it[0].toInt() to it[1].toInt() }
        data.filter{ it.contains("fold")}.map { it.replace("fold along ", "").split("=") }.mapTo(folds) { it[0] to it[1].toInt()}
        return Input(points, folds)
    }

    val testInput = readTestInput(DAY).filterNot { it.isBlank() }
    println("part1 test")
    part1(parseData(testInput))
    println("part2 test")
    part2(parseData(testInput))

    val input = readInput(DAY).filterNot { it.isBlank() }
    println("part1 input")
    part1(parseData(input))
    println("part 2 input")
    part2(parseData(input))
}

data class Input(val points: MutableList<Pair<Int, Int>>, val folds: List<Pair<String, Int>>)

