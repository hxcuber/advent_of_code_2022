import java.io.File

fun main(args: Array<String>) {
    val test = File("input.txt").bufferedReader()
    val stacks = Array<Stack<String>>(9) { _ -> Stack()}
//    val listOfString = mutableListOf<String>()
    var counter = 0
    test.forEachLine {
        counter += 1

        if (counter < 9) {
            it.mapIndexed { i, c ->
                val pos = i - 1
                if (pos % 4 == 0 && pos/4 < 9 && c != ' ') stacks[pos/4].pushBottom(c.toString())
            }
        }
//            listOfString.add(it)
//        }
//        else if (counter == 10) {
//            for (i in 1..9){
//                val pos = listOfString[8].indexOf(i.toString())
//                print("$pos ")
//            }
//        }
        else if (counter > 10) {
            stacks.map{println(it)}
            val hello = it.split("move", "from", "to").filter{x -> x != ""}.map {x -> x.trim().toInt()}
            val listToPush = mutableListOf<String>()
            for (i in 1.. hello[0]) {
                listToPush += (stacks[hello[1] - 1].pop())
            }
            stacks[hello[2] - 1].push(listToPush.reversed())
        }
    }
    stacks.map{ print(it.peek()) }
}

class Stack<T>(val list: MutableList<T> = mutableListOf()){
    fun pop(): T = list.removeFirst()
    fun peek(): T = list.first()
    fun push(stuff: T) = list.add(0, stuff)
    fun push(stuff: List<T>) = stuff.map {this.push(it)}
    fun pushBottom(stuff: T) = list.add(stuff)
    override fun toString() = list.toString()
}
