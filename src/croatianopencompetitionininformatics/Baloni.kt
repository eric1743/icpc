package croatianopencompetitionininformatics

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val balloons = br.readLine().split(" ").map { it.toInt() }
    val arrows = MutableList(1000001) { 0 }
    var out = 0
    repeat(n) {
        val h = balloons[it]
        if (arrows[h] < 1) out++
        else arrows[h]--
        arrows[h - 1]++
    }
    println(out)
}