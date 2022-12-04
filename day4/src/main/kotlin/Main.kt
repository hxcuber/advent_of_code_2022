import java.io.File

// part 1
//fun main(args: Array<String>) {
//    val test = File("input.txt").bufferedReader()
//    var total = 0
//    test.forEachLine {
//        if (it == "") {
//
//        } else {
//            val commaSplit = it.split(',')
//            val dashSplit = commaSplit.map { r -> r.split('-') }
//            val dashSplitInt = dashSplit.map { x -> x.map { y -> y.toInt() } }
//            total += if (((dashSplitInt.first().first() - dashSplitInt.last().first()) >= 0 && (dashSplitInt.first()
//                    .last() - dashSplitInt.last().last()) <= 0) || ((dashSplitInt.first().first() - dashSplitInt.last()
//                    .first()) <= 0 && (dashSplitInt.first().last() - dashSplitInt.last().last()) >= 0)
//            ) 1 else 0
//        }
//    }
//    println(total)
//}

fun main(args: Array<String>) {
    val test = File("input.txt").bufferedReader()
    var total = 0
    test.forEachLine {
        if (it == "") {

        } else {
            val commaSplit = it.split(',')
            val dashSplit = commaSplit.map { r -> r.split('-') }
            val dashSplitInt = dashSplit.map { x -> x.map { y -> y.toInt() } }
            total += if (
                dashSplitInt.first().last() >= dashSplitInt.last().first() &&
                dashSplitInt.last().last() >= dashSplitInt.first().first()
            ) 1 else 0
        }
    }
    println(total)
}
