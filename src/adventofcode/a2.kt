package adventofcode

import java.io.File

fun main(args: Array<String>) {
    val br = File("/Users/z00327d/src/tools/icpc/src/adventofcode/in/a1.txt").bufferedReader()
    val freq = mutableListOf<Int>()
    while (true) {
        val line = br.readLine()
        if (line.isNullOrEmpty()) break
        freq += line.toInt()
    }
    var cur = 0
    val set = mutableSetOf<Int>()
    var idx = 0
    while (true){
        if (set.contains(cur)) break
        set += cur
        cur += freq[idx++]
        idx %= freq.size

    }
    println(cur)
}