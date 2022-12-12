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
    game.play(10000)

    // part 1 - play game 20 times, div by 3 for worry level
    // part 2 - play game 10000 times, no div by 3 (probably using Longs instead of Ints for this)
    println(game.monkeyList.sortedByDescending { it.inspections }[0].inspections * game.monkeyList.sortedByDescending { it.inspections }[1].inspections
    )

}

class Monkey(
    var items: MutableList<Long> = mutableListOf(),
    val operation: (Long) -> Long,
    val test: (Long) -> Long,
    var inspections: Long = 0
) {
    // (item, monkey)
    fun inspect(): List<Pair<Long, Long>> {
        val itemsCopy = items
        items = mutableListOf()
        inspections += itemsCopy.size
        return itemsCopy.map { operation(it) % (5*17*2*7*3*11*13*19) to test(operation(it) % (5*17*2*7*3*11*13*19)) }
    }

    fun addItem(item: Long) {
        items.add(item)
    }
}

class MonkeyGame(val monkeyList: MutableList<Monkey> = mutableListOf()) {
    fun addMonkey(monkey: Monkey) = monkeyList.add(monkey)

    fun play(rounds: Long = 1) {
        for (i in 1..rounds) {
            for (monkey in monkeyList) {
                val listToAdd = monkey.inspect()
                listToAdd.map { (i, m) -> monkeyList[m.toInt()].addItem(i) }
            }
        }
    }
}

class MonkeyParser() {
    fun parseItems(line: String): List<Long> {
        return line.substringAfter("Starting items: ").split(",").map { it.trim().toLong() }
    }

    fun parseOperation(line: String): (Long) -> Long {
        val opNum = line.substringAfter("Operation: new = old ").split(" ")
        return when (opNum[0]) {
            "*" -> { x -> x * if (opNum[1] == "old") x else opNum[1].toLong() }
            "+" -> { x -> x + if (opNum[1] == "old") x else opNum[1].toLong() }
            else -> { x -> x }
        }
    }

    fun parseTest(line1: String, line2: String, line3: String): (Long) -> Long {
        val test = line1.substringAfter("Test: divisible by ").toLong()
        val ifTrue = line2.substringAfter("If true: throw to monkey ").toLong()
        val ifFalse = line3.substringAfter("If false: throw to monkey ").toLong()
        return { if (it % test == 0L) ifTrue else ifFalse }
    }
}
