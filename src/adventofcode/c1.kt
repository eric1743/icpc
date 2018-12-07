package adventofcode

import java.io.File

fun main(args: Array<String>) {
    val br = File("/Users/z00327d/src/tools/icpc/src/adventofcode/in/c1.txt").bufferedReader()

    val claims = MutableList(1000) { MutableList(1000) { 0 } }
    while (true) {
        var line = br.readLine()
        if (line.isNullOrEmpty()) break;
        line = Regex("[# ]").replace(line, "")
        line = Regex("[x@:]").replace(line, ",")
        val arr = line.split(",").map { it.toInt() }
        for(i in arr[1] until arr[1]+arr[3]){
            for (j in arr[2] until arr[2]+arr[4]){
                claims[i][j]++
            }
        }
    }
    var out = 0
    for(i in 0..999){
        for(j in 0..999){
            if(claims[i][j] >= 2) out++
        }
    }
    println(out)
}