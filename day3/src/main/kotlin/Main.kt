import java.io.File

// a - 97 z - 122
// A - 65 Z - 90
// part 1
//fun main(args: Array<String>) {
//    val test = File("input.txt").bufferedReader()
//    var total = 0
//    test.forEachLine {
//        if (it == "") {
//
//        }
//        else {
//            val halfway = it.length / 2
//            val firstHalf = it.take(halfway).toSet()
//            val secondHalf = it.drop(halfway).toSet()
//            println(firstHalf.intersect(secondHalf))
//            total += firstHalf.intersect(secondHalf).map{c ->
//                if (c.isUpperCase()) (c.code - 38) else (c.code - 96)
//            }.first()
//
//        }
//    }
//    println(total)
//}

// part 2
fun main(args: Array<String>) {
    val test = File("input.txt").bufferedReader()
    var total = 0
    var counter = 0
    var listOfRucksack: List<String> = listOf()
    test.forEachLine {
        counter += 1
        listOfRucksack += it
        if ( counter % 3 == 0) {
            val concac = listOfRucksack.map { r ->
                r.toSet()
            }.flatten().groupingBy { c ->
                c
            }.eachCount().filter { (_, n) ->
                n == 3
            }.map { (k, v) ->
                if (k.isUpperCase()) (k.code - 38) else (k.code - 96)
            }.first()
            total += concac
            listOfRucksack = listOf()
        }
        else {

        }
    }
    println(total)
}