import java.io.File
import kotlin.math.max

fun main(args: Array<String>) {
    val test = File("input.txt").readLines().map {
        it.toList().map { d -> d.digitToInt() }
    }

    val visibility = test.mapIndexed { y, l ->
        List(l.size) { x -> isVisible(y, x, test) }
    }
    val scenicScores = test.mapIndexed { y, l ->
        List(l.size) { x -> scenicScore(y, x, test) }
    }

    // part 1
    println(visibility.sumOf { it.sumOf { if (it) 1.0.toInt() else 0.0.toInt() } })

    //part 2
    scenicScores.forEach {
        println(it)
    }
    println(scenicScores.flatten().max())

}

fun isVisible(y: Int, x: Int, data: List<List<Int>>): Boolean {
    val tree = data[y][x]
    val columns = data[y].size
    val rows = data.size
//    println(tree)

    // right
    val right: Boolean = (data[y].subList(x + 1, columns).maxOrNull() ?: -1) < tree

    // left
    val left: Boolean = (data[y].subList(0, x).maxOrNull() ?: -1) < tree

    // down
    val down: Boolean = ((y + 1 until rows).maxOfOrNull { data[it][x] } ?: -1) < tree

    // up
    val up: Boolean = ((0 until y).maxOfOrNull { data[it][x] } ?: -1) < tree

    return (right || left || down || up)
}

fun scenicScore(y: Int, x: Int, data: List<List<Int>>): Int {
    val tree = data[y][x]
    val columns = data[y].size
    val rows = data.size

    if (y == rows
        || y == 0
        || x == columns
        || x == 0
    ) return 0

//    println(tree)

    // right
    val right: Int = data[y].subList(x + 1, columns).indexOfFirst { d -> d >= tree }

    // left
    val left: Int = data[y].subList(0, x).reversed().indexOfFirst { d -> d >= tree }

    // down
    val down: Int = (y + 1 until rows).map { data[it][x] }.indexOfFirst { d -> d >= tree }

    // up
    val up: Int = (0 until y).map { data[it][x] }.reversed().indexOfFirst { d -> d >= tree }

    return (if (right == -1) (columns - x - 1) else right + 1) * (if (left == -1) (x) else left + 1) *
            (if (down == -1) (rows - y - 1) else down + 1) * (if (up == -1) (y) else up + 1)
}
