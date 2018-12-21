package adventofcode

import java.io.File

fun main(args: Array<String>) {
    val br = File("/Users/z00327d/src/tools/icpc/src/adventofcode/in/f1.txt").bufferedReader()
    val list = mutableListOf<Pair<Int,Int>>()
    while(true){
        val line = br.readLine()
        if (line.isNullOrEmpty()) break
        val arr = line.replace(",", "").split(" ").map { it.toInt() }
        list.add(Pair(arr[0], arr[1]))
    }
    val map = MutableList(500){ MutableList(500){0}}


}