import java.io.File
// part 1
//fun main(args: Array<String>) {
//    val test = File("input.txt").bufferedReader()
//    val hello = mutableListOf<Int>()
//
//    test.forEachLine {
//        for (i in 0 .. it.length - 4) {
//            val hi = it.substring(i, i + 4)
//             if (hi.toList() == hi.toSet().toList()){
//                 hello.add(i+4)
//             }
//        }
//
//    }
//    println(hello)
//
//}

// part 2
fun main(args: Array<String>) {
    val test = File("input.txt").bufferedReader()
    val hello = mutableListOf<Int>()

    test.forEachLine {
        for (i in 0 .. it.length - 14) {
            val hi = it.substring(i, i + 14)
            if (hi.toList() == hi.toSet().toList()){
                hello.add(i+14)
            }
        }

    }
    println(hello)

}