package adventofcode

import java.io.File

fun main(args: Array<String>) {
    val br = File("/Users/z00327d/src/tools/icpc/src/adventofcode/in/a1.txt").bufferedReader()
    var out = 0
    while (true) {
        val line = br.readLine()
        if (line.isNullOrEmpty()) break
        out += line.toInt()
    }
    println(out)
}