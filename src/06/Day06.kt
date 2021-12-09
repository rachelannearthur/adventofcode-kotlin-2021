package `06`

import readInput
import readTestInput
import java.lang.Math.abs
import java.util.*

const val DAY = "06"

fun main() {
    fun part1(input: List<Fish>): Int {
        val result = mutableListOf<Fish>()
        result.addAll(input)

        for(day in 0 until 80) {
            val newFish = mutableListOf<Fish>()
            result.forEach { f ->
                f.passDay()?.let {
                    newFish.add(it)
                }
            }
            result.addAll(newFish)
        }
        return result.size
    }

    fun part2(fishList: MutableList<Long>): Long {
        for (day in 0 until 256) {
            Collections.rotate(fishList, -1)
            fishList[6] += fishList.last()
        }
        return fishList.sum()
    }

    fun getFishList(input: Map<Int, Int>): MutableList<Long> {
        val fishList = (0L until 9L).toMutableList()
        for (x in 0 until 9) {
            fishList[x] = input[x]?.toLong() ?: 0L
        }
        return fishList
    }

    val testInput1 = readTestInput(DAY)[0].split(",").map { Fish(isNew = false, initialDays = it.toInt())}
    println(part1(testInput1))

    val testInputs = readTestInput(DAY)[0].split(",").groupingBy { it.toInt() }.eachCount()

    println(part2(getFishList(testInputs)))

    val input = readInput(DAY)[0].split(",").map { Fish(isNew = false, initialDays = it.toInt())}
    val inputPt2 = readInput(DAY)[0].split(",").groupingBy { it.toInt() }.eachCount()
    println(part1(input))
    println(part2(getFishList(inputPt2)))
}



data class Fish(val isNew: Boolean, private val initialDays: Int = 0) {
    var days = if (isNew) 8 else initialDays

    fun passDay(): Fish? {
        return when (days) {
            0 -> {
                days = 6
                Fish(isNew = true)
            }
            else -> {
                days -= 1
                null
            }
        }
    }
}