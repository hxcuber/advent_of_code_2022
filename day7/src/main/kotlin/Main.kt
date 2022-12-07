import java.io.File

fun main(args: Array<String>) {
    val test = File("input.txt").bufferedReader()

    test.forEachLine {
        if (it == "") {

        } else {

        }
    }

}

class Directory(
    private val name: String,
    private val directories: MutableList<Directory> = mutableListOf(),
    private var fileSize: Int
){
    fun changeFileSize(newSize: Int) {
        fileSize = newSize
    }
    fun name(): String = name
    fun addDirectories(dir: Directory) {
        directories.add(dir)
    }
    fun addDirectories(dir: List<Directory>) {
        directories.addAll(dir)
    }

}

class Parser(filename: String) {
    val test = File(filename).bufferedReader()

}
