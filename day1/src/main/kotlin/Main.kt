import java.io.File
import kotlin.math.max

fun main(args: Array<String>) {
    val test = File("input.txt").bufferedReader()
    var total: Int = 0
    var max: List<Int> = listOf()
    test.forEachLine {
        if (it == "") {
            max = max.plus(total)
            total = 0
        }
        else {
            total += it.toInt()
        }
    }
    println(max.sortedByDescending { it }.take(3).sum())
}