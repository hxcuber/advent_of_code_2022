import java.io.File
import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    val test = File("input.txt").bufferedReader()
    val newRopeSection = RopeSection()
    test.forEachLine {
        val command = it.split(" ")
        for (i in 1.. command[1].toInt()) {
            newRopeSection.moveHead(command[0])
        }
    }
    // part 1
    println(newRopeSection.positionsVisited.size)

    //part 2
}

class RopeSection(
    var headPos: Pair<Int, Int> = 0 to 0, // first x second y
    var tailPos: Pair<Int, Int> = 0 to 0,
    val positionsVisited: MutableSet<Pair<Int, Int>> = mutableSetOf()
) {
    fun tailAdjHead(): Boolean {
        return (headPos.first - tailPos.first).absoluteValue < 2
                && (headPos.second - tailPos.second).absoluteValue < 2
    }

//    fun tailEqHead() : Boolean {
//        return (headPos.first == tailPos.first)
//                && (headPos.second == tailPos.second)
//    }

    // only moves 1 block at a time, including diags
    fun moveHead(direction: String): Pair<Int, Int> {
        val prevHead = headPos
        when (direction){
            "R" -> {
                headPos = (headPos.first + 1) to headPos.second
            }
            "L" -> {
                headPos = (headPos.first - 1) to headPos.second
            }
            "U" -> {
                headPos = headPos.first to (headPos.second + 1)
            }
            "D" -> {
                headPos = headPos.first to (headPos.second - 1)
            }
            else -> {}
        }
        if (!tailAdjHead()) {
            tailPos = prevHead
        }
        positionsVisited.add(tailPos)
        return headPos
    }
}

class Rope(val knots: Int){
    val ropeSections = List(knots) {x -> RopeSection()}

    moveHead()

}


