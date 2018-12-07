package adventofcode

import java.io.File

fun main(args: Array<String>) {
    val br = File("/Users/z00327d/src/tools/icpc/src/adventofcode/in/b1.txt").bufferedReader()
    var check2 = 0
    var check3 = 0
    while (true) {
        val line = br.readLine()
        if (line.isNullOrEmpty()) break
        val check = MutableList<Int>(26) { 0 }
        for(c in line){
            check [c.toChar() - 'a']++
        }
        var two = false
        var three = false
        for(c in check){
            if (c == 2) two = true
            if (c == 3) three = true
        }
        if (two) check2++
        if (three) check3++
    }
    println("$check2, $check3")
    println(check2*check3)
}