import java.io.File
import java.math.BigInteger
import kotlin.math.absoluteValue

fun main(args: Array<String>) {
    val test = File("input.txt").bufferedReader().readLines()
    val hillMap = HillMap()
    var start = Point()
    var goal = Point()
    for ((y, s) in test.withIndex()) {
        for ((x, c) in s.withIndex()) {
            var current = Point()
            when (c) {
                'S' -> {
                    start = Point(x.toBigInteger(), y.toBigInteger(), 0.toBigInteger()); current = start
                }

                'E' -> {
                    goal = Point(x.toBigInteger(), y.toBigInteger(), 25.toBigInteger()); current = goal
                }

                else -> {
                    current = Point(x.toBigInteger(), y.toBigInteger(), (c.code - 97).toBigInteger())
                }
            }
            hillMap.addPoint(current)
        }
    }

    // part 1
    //println(hillMap.findOptimalRoute(start, goal))

    //part 2
    println(hillMap.findOptimalDistances(goal).filter { (k, _) -> k.elevation == 0.toBigInteger() }.minBy { (_, v) -> v })
}

data class Point(val x: BigInteger = 0.toBigInteger(), val y: BigInteger = 0.toBigInteger(), val elevation: BigInteger = 0.toBigInteger()) {
    fun isViable(other: Point): Boolean {
        return ((((x - other.x).toInt().absoluteValue == 1) && (y == other.y)) ||
                ((y - other.y).toInt().absoluteValue == 1) && (x == other.x))
                && (elevation + 1.toBigInteger() >= other.elevation)
    }

    fun isRevViable(other: Point): Boolean {
        return ((((x - other.x).toInt().absoluteValue == 1) && (y == other.y)) ||
                ((y - other.y).toInt().absoluteValue == 1) && (x == other.x))
                && (other.elevation + 1.toBigInteger() >= elevation)
    }
}

class HillMap(val listPoint: MutableList<Point> = mutableListOf()) {
    fun addPoint(point: Point) = listPoint.add(point)

    // finds every path, takes way too long lol
    fun findRoutes(start: Point, goal: Point): List<List<Point>> {
        fun findRoutesHelper(start: Point, goal: Point, visitedPoints: Set<Point>): List<List<Point>> {
            val unvisitedPoints = listPoint.toList().filter {
                !visitedPoints.contains(it) && start.isViable(it)
            }.sortedBy { (goal.y - it.y)*(goal.y - it.y) + (goal.x - it.x)*(goal.x - it.x) }
            return when (start) {
                goal -> listOf(listOf())
                else -> unvisitedPoints.map { p ->
                    findRoutesHelper(p, goal, visitedPoints.plus(p)).map {it.plus(start)}
                }.flatten()
            }
        }
        return findRoutesHelper(start, goal, setOf(start)).reversed()
    }

    // dijkstra
    fun findOptimalRoute(start: Point, goal: Point): BigInteger {
        val distances = listPoint.associateWith { Long.MAX_VALUE }.toMutableMap()
        distances[start] = 0

        val unvisited = listPoint.toMutableList()

        while (unvisited.isNotEmpty()) {
            val curr = unvisited.minBy { distances[it]!! }
            unvisited.remove(curr)

            val neighbors = unvisited.filter { curr.isViable(it) }
            for (i in neighbors) {
                val alt = distances[curr]!! + 1
                if (alt < distances[i]!!)
                    distances[i] = alt
                if (i == goal) {
                    return distances[goal]!!.toBigInteger()
                }
            }
        }
        return distances[goal]!!.toBigInteger()
    }

    // part 2
    fun findOptimalDistances(start: Point): Map<Point, BigInteger> {
        val distances = listPoint.associateWith { Long.MAX_VALUE.toBigInteger() }.toMutableMap()
        distances[start] = 0.toBigInteger()

        val unvisited = listPoint.toMutableList()

        while (unvisited.isNotEmpty()) {
            val curr = unvisited.minBy { distances[it]!! }
            unvisited.remove(curr)

            val neighbors = unvisited.filter { curr.isRevViable(it) }
            for (i in neighbors) {
                val alt = distances[curr]!! + 1.toBigInteger()
                if (alt < distances[i]!!)
                    distances[i] = alt
            }
        }
        return distances.toMap()
    }

}