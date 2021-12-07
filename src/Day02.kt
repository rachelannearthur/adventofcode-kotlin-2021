fun main() {
    fun part1(input: List<List<String>>): Int {
        var hor = 0
        var vert = 0

        input.forEach {
            when (it[0]) {
                "forward" -> {
                    hor += it[1].toInt()
                }
                "up" -> {
                    vert -= it[1].toInt()
                }
                "down" -> {
                    vert += it[1].toInt()
                }
            }
        }

        return hor * vert
    }

    fun part2(input: List<List<String>>): Int {
        var hor = 0
        var vert = 0
        var aim = 0

        input.forEach {
            when (it[0]) {
                "forward" -> {
                    hor += it[1].toInt()
                    vert += it[1].toInt() * aim
                }
                "up" -> {
                    aim -= it[1].toInt()
                }
                "down" -> {
                    aim += it[1].toInt()
                }
            }
        }

        return hor * vert
    }

    val testInput = readInput("Day02_test").map{ it.split(" ") } // [[forward, 5]]
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02_input").map{ it.split(" ") }

    println(part1(input))
    println(part2(input))
}
