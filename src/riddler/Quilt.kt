package riddler

import java.util.*
import kotlin.math.max

fun main(args: Array<String>) {
    var count = 0
    var highest = 0
    loop@ while (true) {
        count++
        var quilt = List(100) { List(100) { (0 until 10).random() } }
        for (row in 0..98) {
            for (col in 0..98) {
                if (quilt[row][col] == quilt[row + 1][col] && quilt[row][col] == quilt[row][col + 1] && quilt[row][col] == quilt[row + 1][col + 1]) {
                    highest = max(highest, row*100 + col)
                    if (count%100 == 0) println("$highest")
                    continue@loop
                }
            }
        }
        break
    }
    println("Success $count")

}

fun ClosedRange<Int>.random() = Random().nextInt((endInclusive + 1) - start) + start