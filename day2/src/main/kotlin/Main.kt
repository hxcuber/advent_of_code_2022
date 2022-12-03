import java.io.File
//part 1
//fun main(args: Array<String>) {
//    val test = File("input.txt").bufferedReader()
//    val mapOfWinScore: Map<Pair<String, String>, Int>
//    // draw = 3, win = 6, loss = 0
//    // rock = 1, paper = 2, scissors = 3
//    // A/X = rock, B/Y = paper, C/Z = scissors
//    mapOfWinScore = mapOf(
//        ("A" to "X") to 3,
//        ("A" to "Y") to 6,
//        ("A" to "Z") to 0,
//        ("B" to "X") to 0,
//        ("B" to "Y") to 3,
//        ("B" to "Z") to 6,
//        ("C" to "X") to 6,
//        ("C" to "Y") to 0,
//        ("C" to "Z") to 3,
//        )
//    val secondMap = mapOf("X" to 1, "Y" to 2, "Z" to 3)
//    var score = 0
//    test.forEachLine {
//        if (it == "") {
//
//        }
//        else {
//            val splitted = it.split(" ")
//            val (x,y) = splitted[0] to splitted[1]
//            score += (mapOfWinScore[(x to y)] ?: 0) + (secondMap[y] ?: 0)
//        }
//    }
//    println(score)
//
//}

//part 2
fun main(args: Array<String>) {
    val test = File("input.txt").bufferedReader()
    val mapOfWinScore: Map<Pair<String, String>, Int>
    // X/loss = 0, Y/draw = 3, Z/win = 6
    // rock = 1, paper = 2, scissors = 3
    // A = rock, B = paper, C = scissors
    mapOfWinScore = mapOf(
        ("A" to "X") to 3,
        ("A" to "Y") to 1,
        ("A" to "Z") to 2,
        ("B" to "X") to 1,
        ("B" to "Y") to 2,
        ("B" to "Z") to 3,
        ("C" to "X") to 2,
        ("C" to "Y") to 3,
        ("C" to "Z") to 1,
        )
    val secondMap = mapOf("X" to 0, "Y" to 3, "Z" to 6)
    var score = 0
    test.forEachLine {
        if (it == "") {

        }
        else {
            val splitted = it.split(" ")
            val (x,y) = splitted[0] to splitted[1]
            score += (mapOfWinScore[(x to y)] ?: 0) + (secondMap[y] ?: 0)
        }
    }
    println(score)

}

//import java.io.File
//
//fun main(args: Array<String>) {
//    val test = File("input.txt").bufferedReader()
//
//    test.forEachLine {
//        if (it == "") {
//
//        }
//        else {
//
//        }
//    }
//
//}