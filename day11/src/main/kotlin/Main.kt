import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt").bufferedReader()
    val parser = MonkeyParser()
    val game = MonkeyGame()
    for (i in 0..7) {
        file.readLine()
        val items = parser.parseItems(file.readLine()!!)
        val operation = parser.parseOperation(file.readLine()!!)
        val test = parser.parseTest(file.readLine()!!, file.readLine()!!, file.readLine()!!)
        file.readLine()
        game.addMonkey(Monkey(items.toMutableList(), operation, test))
    }
    game.play(20)

    // part 1
    println(game.monkeyList.sortedByDescending { it.inspections }[0].inspections *
            game.monkeyList.sortedByDescending { it.inspections }[1].inspections)
}

class Monkey(
    var items: MutableList<Int> = mutableListOf(),
    val operation: (Int) -> Int,
    val test: (Int) -> Int,
    var inspections: Int = 0
) {
    // (item, monkey)
    fun inspect(): List<Pair<Int, Int>> {
        val itemsCopy = items
        items = mutableListOf()
        inspections += itemsCopy.size
        return itemsCopy.map { operation(it) / 3 to test(operation(it) / 3) }
    }

    fun addItem(item: Int) {
        items.add(item)
    }
}

class MonkeyGame(val monkeyList: MutableList<Monkey> = mutableListOf()) {
    fun addMonkey(monkey: Monkey) = monkeyList.add(monkey)

    fun play(rounds: Int = 1) {
        when (rounds) {
            0 -> {}
            else -> {
                for (monkey in monkeyList) {
                    val listToAdd = monkey.inspect()
                    listToAdd.map { (i, m) -> monkeyList[m].addItem(i) }
                }
                for (monkey in monkeyList) {
                    println(monkey.items)
                }
                println("after round $rounds")
                play(rounds - 1)
            }
        }
    }
}

class MonkeyParser() {
    fun parseItems(line: String): List<Int> {
        return line.substringAfter("Starting items:").split(",").map { it.trim().toInt() }
    }

    fun parseOperation(line: String): (Int) -> Int {
        val opNum = line.substringAfter("Operation: new = old ").split(" ")
        return when (opNum[0]) {
            "*" -> { x -> x * if (opNum[1] == "old") x else opNum[1].toInt() }
            "+" -> { x -> x + if (opNum[1] == "old") x else opNum[1].toInt() }
            else -> { x -> x }
        }
    }

    fun parseTest(line1: String, line2: String, line3: String): (Int) -> Int {
        val test = line1.substringAfter("Test: divisible by ").toInt()
        val ifTrue = line2.substringAfter("If true: throw to monkey ").toInt()
        val ifFalse = line3.substringAfter("If false: throw to monkey ").toInt()
        return { if (it % test == 0) ifTrue else ifFalse }
    }
}
