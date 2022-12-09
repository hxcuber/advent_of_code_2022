import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main(args: Array<String>) {
    val test = File("input.txt").bufferedReader()
    val part1Rope = Rope(2)
    val part2Rope = Rope(10)
    test.forEachLine {
        val command = it.split(" ")
        for (i in 1..command[1].toInt()) {
            part1Rope.moveHead(command[0])
            part2Rope.moveHead(command[0])
        }
    }
    // part 1
    println(part1Rope.tailVisited())

    //part 2
    println(part2Rope.tailVisited())
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
    fun sameRow() = headPos.first == tailPos.first
    fun sameColumn() = headPos.second == tailPos.second
    fun headRightToTail() : Boolean = headPos.first > tailPos.first
    fun headLeftToTail() : Boolean = headPos.first < tailPos.first
    fun headUpToTail() : Boolean = headPos.second > tailPos.second
    fun headDownToTail() : Boolean = headPos.second < tailPos.second


//    fun tailEqHead() : Boolean {
//        return (headPos.first == tailPos.first)
//                && (headPos.second == tailPos.second)
//    }

    // only moves 1 block at a time, including diags
    fun moveHead(afterPos: Pair<Int, Int>): Pair<Int, Int> {
        val prevHead = headPos
        headPos = afterPos
        if (!tailAdjHead()) {
            if (sameColumn()) {
                val difference = (headPos.first - tailPos.first)
                tailPos = (tailPos.first + difference - (difference.sign) to tailPos.second)

            }

            else if (sameRow()){
                val difference = (headPos.second - tailPos.second)
                tailPos = (tailPos.first to tailPos.second + difference - (difference.sign))
            }

            else {
                val difference = headPos.first - tailPos.first to
                        headPos.second - tailPos.second

                tailPos = tailPos.first + 1 * (difference.first.sign) to
                        tailPos.second + 1 * (difference.second.sign)
            }


        }
        positionsVisited.add(tailPos)
        return headPos
    }
}

class Rope(val knots: Int) {
    val ropeSections = List(knots - 1) { RopeSection() }

    fun moveHead(direction: String): Pair<Int, Int> {
        val head = ropeSections.first()
        when (direction) {
            "R" -> {
                head.moveHead((head.headPos.first + 1) to head.headPos.second)
            }

            "L" -> {
                head.moveHead((head.headPos.first - 1) to head.headPos.second)
            }

            "U" -> {
                head.moveHead(head.headPos.first to (head.headPos.second + 1))
            }

            "D" -> {
                head.moveHead(head.headPos.first to (head.headPos.second - 1))
            }

            else -> {}
        }
        for (i in 1 until knots - 1) {
            ropeSections[i].moveHead(ropeSections[i-1].tailPos)
        }

        return head.headPos
    }

    fun tailVisited(): Int = ropeSections.last().positionsVisited.size
}


