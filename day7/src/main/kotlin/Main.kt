import java.io.File

fun main(args: Array<String>) {
    val test = File("input.txt").bufferedReader()
    val myCursor = Cursor(Directory())
    test.forEachLine {
        val hello = it.split(" ")
        if (hello[0] == "$") {
            if (hello[1] == "cd") {
                myCursor.cd(hello[2])
            }
        } else if (hello[0] == "dir") {
            myCursor.mkdir(hello[1])
        } else {
            myCursor.changeSize(hello[0].toInt())
        }

    }
    myCursor.cd("/")
    // part 1
    println(myCursor.cursor.getAllSize().filter { it <= 100000}.sumOf { it })

    // part 2
    println(myCursor.cursor.getAllSize().filter {70000000 - myCursor.cursor.size() + it >=30000000}.min())
}

class Directory(
    var parent: Directory? = null,
    val name: String = "root",
    val directories: MutableList<Directory> = mutableListOf(),
    var fileSize: Int = 0
) {
    fun size(): Int = directories.sumOf { it.size() } + fileSize
    fun getAllSize(): List<Int> {
        return directories.map { it.getAllSize() }.flatten().plus(this.size())
    }

    override fun toString(): String {
        val ret = StringBuilder()
        ret.append(name)
        directories.map { ret.append("\n  ${it.toString()}, ${it.size()}") }
        return ret.toString()
    }
}

class Cursor(var cursor: Directory) {
    fun cd(dirname: String): Boolean {
        if (dirname == "..") {
            try {
                cursor = cursor.parent!!
            } catch (e: Exception) {
                return false
            }
            return true
        } else if (dirname == "/") {
            while (this.cd("..")) {
            }
            return true
        } else {
            try {
                cursor = cursor.directories.first { d -> d.name == dirname }
            } catch (e: Exception) {
                return false
            }
            return true
        }
        return false
    }

    fun mkdir(dirname: String): Boolean {
        cursor.directories.add(Directory(cursor, dirname, fileSize = 0))
        return true
    }

    fun changeSize(size: Int): Boolean {
        cursor.fileSize += size
        return true
    }
}
