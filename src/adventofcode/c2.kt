package adventofcode

import java.io.File

fun main(args: Array<String>) {
    val br = File("/Users/z00327d/src/tools/icpc/src/adventofcode/in/c1.txt").bufferedReader()

    val fabric = MutableList(1000) { MutableList(1000) { 0 } }
    val claims = mutableListOf<List<Int>>()
    while (true) {
        var line = br.readLine()
        if (line.isNullOrEmpty()) break;
        line = Regex("[# ]").replace(line, "")
        line = Regex("[x@:]").replace(line, ",")
        val arr = line.split(",").map { it.toInt() }
        claims += arr
        for(i in arr[1] until arr[1]+arr[3]){
            for (j in arr[2] until arr[2]+arr[4]){
                fabric[i][j]++
            }
        }
    }
    for (claim in claims){
        var valid = true
        for(i in claim[1] until claim[1]+claim[3]){
            for (j in claim[2] until claim[2]+claim[4]){
                valid = valid && (fabric[i][j] == 1)
            }
        }
        if (valid) println(claim[0])
    }
}