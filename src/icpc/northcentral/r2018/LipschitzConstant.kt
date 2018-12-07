package icpc.northcentral.r2018

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    val coords = MutableList(n) { br.readLine().split(" ").map { it.toDouble() } }
    coords.sortBy { it[0] }
    var max = 0.0
    repeat(n - 1) {
        val buff = Math.abs((coords[it][1] - coords[it + 1][1]) / (coords[it][0] - coords[it + 1][0]))
        max = Math.max(max , buff)
    }
    println(max)
}