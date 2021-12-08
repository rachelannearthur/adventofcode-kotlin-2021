package `04`

import readInput
import readTestInput

const val DAY = "04"

fun main() {
    fun part1(system: BingoSystem): Int {
        val boards = system.boards.toMutableList()
        for (move in system.moves) {
            // for each move, mark each board
            // check each board for winner
            for (board in boards) {
                    board.mark(move)
                    if (board.hasWinner()) {
                        return move * board.unmarkedSum
                    }
                }
            }
        return 0
    }

    fun part2(system: BingoSystem): Int {
        var boards = system.boards.toMutableList()
        for (move in system.moves) {
            for (board in boards) {
                board.mark(move)
            }
            if (boards.size == 1 && boards.first().hasWinner()) {
                return move * boards.first().unmarkedSum
            }

            // remove boards that have won
            boards = boards.filterNot { it.hasWinner() }.toMutableList()
        }
        return 0
    }

    val testInput = readTestInput(DAY)
    val testMoves = testInput[0].split(",").map{it.toInt()}
    val testPart1 = part1(BingoSystem(testMoves, getBoardsFromInput(testInput.subList(1, testInput.size))))
    println(testPart1)
    check(testPart1 == 4512)
    val testPart2 = part2(BingoSystem(testMoves, getBoardsFromInput(testInput.subList(1, testInput.size))))
    println(testPart2)
    check(testPart2 == 1924)

    val input = readInput(DAY)

    val moves = input[0].split(",").map{it.toInt()}
    val part1 = part1(BingoSystem(moves, getBoardsFromInput(input.subList(1, input.size))))
    println(part1)
    val part2 = part2(BingoSystem(moves, getBoardsFromInput(input.subList(1, input.size))))
    println(part2)
}

data class Board(val rows: List<List<Square>>) {
    private val columns: List<List<Square>>
    var unmarkedSum: Int = 0

    init {
        val cols = mutableListOf<MutableList<Square>>()
        rows.forEach { row ->
            row.forEachIndexed { i, value ->
                if (cols.size <= i) cols.add(i, mutableListOf(value))
                else cols[i].add(value)
                unmarkedSum += value.number
            }
        }
        columns = cols
    }

    fun hasWinner() = checkBoard()

    fun mark(number: Int) {
        for (row in rows) {
            for (sq in row) {
                if (sq.number == number) {
                    unmarkedSum -= sq.number
                    sq.isMarked = true
                    break
                }
            }
        }
    }

    private fun checkBoard(): Boolean {
        for (row in rows) {
            if (row.filter { sq -> sq.isMarked }.size == 5) {
                return true
            }
        }
        for (col in columns) {
            if (col.filter { sq -> sq.isMarked }.size == 5) {
                return true
            }
        }

        return false
    }
}

data class Square(val number: Int, var isMarked: Boolean = false)

data class BingoSystem(val moves: List<Int>, val boards: List<Board>)

fun getBoardsFromInput(list: List<String>): List<Board> =
    list.filterNot{
        it.isEmpty()
    }.map {
        it.split(" ").filterNot {
            it.isEmpty()
        }.map { number ->
            Square(number.toInt())
        }
    }.chunked(5).map { rows ->
        Board(rows)
    }

fun printBoard(board: Board) {
    board.rows.forEach { row ->
        row.forEach {
            print("${it.number}: ${it.isMarked}  ")
        }
        println()
    }
    println()
}