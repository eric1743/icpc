package adventofcode

import java.io.File
import kotlin.math.min

fun main(args: Array<String>) {
    val br = File("/Users/z00327d/src/tools/icpc/src/adventofcode/in/e1.txt").bufferedReader()
    val poly = br.readLine().toMutableList();
    poly += '@'
    var len = poly.size
    while (true) {
        var idx = 0
        while (true) {
            if (poly[idx + 1] == '@') break
            val a = poly[idx]
            val b = poly[idx + 1]
            if ((a.isUpperCase() != b.isUpperCase()) && (a.toUpperCase() == b.toUpperCase())) {
                poly.removeAt(idx)
                poly.removeAt(idx)
            } else idx++
        }
        if (poly.size == len) break
        len = poly.size
    }
    var short = len
    for (i in 0..25) {
        len = 0
        var polytest = poly.map { it }.toMutableList()
        while (polytest.remove('a' + i)) {
        }
        while (polytest.remove('A' + i)) {
        }
        while (true) {
            var idx = 0
            while (true) {
                if (polytest[idx + 1] == '@') break
                val a = polytest[idx]
                val b = polytest[idx + 1]
                if ((a.isUpperCase() != b.isUpperCase()) && (a.toUpperCase() == b.toUpperCase())) {
                    polytest.removeAt(idx)
                    polytest.removeAt(idx)
                } else idx++
            }
            if (polytest.size == len) break
            len = polytest.size
        }
        short = min(short,len)
    }
    println(short - 1)
}