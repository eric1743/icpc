package adventofcode

import java.io.File

fun main(args: Array<String>) {
    val br = File("/Users/z00327d/src/tools/icpc/src/adventofcode/in/e1.txt").bufferedReader()
    val poly = br.readLine().toMutableList();
    poly += '@'
    var len = poly.size
    while (true){
        var idx = 0
        while(true){
            if(poly[idx + 1] == '@') break
            val a = poly[idx]
            val b = poly[idx+1]
            if ((a.isUpperCase() != b.isUpperCase()) && (a.toUpperCase() == b.toUpperCase())){
                poly.removeAt(idx)
                poly.removeAt(idx)
            } else idx++
        }
        if(poly.size == len) break
        len = poly.size
    }
    println(len - 1)
}