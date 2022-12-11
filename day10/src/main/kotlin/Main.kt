import java.io.File

typealias Cycle = Int
typealias Register = Int
typealias State = Pair<Cycle, Register>

fun main(args: Array<String>) {
    val test = File("input.txt").bufferedReader()
    val program: Program = Program()
    test.forEachLine {
        val command = it.split(" ")
        when (command.first()) {
            "noop" -> program.noop()
            "addx" -> program.addx(command.last().toInt())
            else -> {}
        }
    }
//    program.log.forEach {
//        println(it)
//    }
    // part 1
    // nth index refers to nth cycle, state of register AFTER nth cycle
    // n - 1 to get the state of the register DURING the next cycle, not after
    println(
        program.log[20 - 1].signalStrength() +
                program.log[60 - 1].signalStrength() +
                program.log[100 - 1].signalStrength() +
                program.log[140 - 1].signalStrength() +
                program.log[180 - 1].signalStrength() +
                program.log[220 - 1].signalStrength()
    )

    //part 2
    println(program)
}

class Program(var cycle: Int = 0, var register: Int = 1) {
    val log: MutableList<State> = mutableListOf(0 to 1)

    // cycle, register AFTER cycle
    fun noop(): State {
        cycle++
        log.add(cycle to register)
        return cycle to register
    }

    fun addx(add: Int): State {
        this.noop()
        cycle++
        register += add
        log.add(cycle to register)
        return cycle to register
    }

    override fun toString(): String {
        val duringCycle = log.map { (c, r) -> c + 1 to r }
        println(duringCycle)
        val ret = StringBuilder()
        for (i in 0 until 240) {
            val current = duringCycle[i]
            if (((i + 1) % 40 - 1 - current.second) in -1..1) {
                ret.append("#")
            } else {
                ret.append(".")
            }

            if ((i + 1) % 40 == 0) ret.append("\n")
        }
        return ret.toString()
    }
}

fun Pair<Cycle, Register>.signalStrength(): Int {
    return (this.first + 1) * this.second
}


