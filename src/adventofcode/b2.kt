package adventofcode

import java.io.File

fun main(args: Array<String>) {
    val br = File("/Users/z00327d/src/tools/icpc/src/adventofcode/in/b1.txt").bufferedReader()
    val boxes = mutableListOf<String>()
    while (true) {
        val line = br.readLine()
        if (line.isNullOrEmpty()) break
        boxes += line
    }
    for((j,b) in boxes.withIndex()){
        loop@for(k in j+1 until boxes.size){
            val c = boxes[k]
            var oneOff = false
            for(i in 0 until b.length){
                if (b[i] != c[i]){
                    if (oneOff) continue@loop
                    oneOff = true
                }
            }
            println("$b, $c")
        }
    }
}