package icpc.nordiccolegiateprogrammingcontest.r2018

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (l, m) = br.readLine().split(" ").map { it.toInt() }
    var minCost = Int.MAX_VALUE
    var out = mutableListOf("no such mower")
    for (i in 0 until m) {
        val line = br.readLine().split(",")
        val p = line[1].toInt()
        if (p > minCost) continue
        val c = line[2].toInt()
        val t = line[3].toLong() //avoid Int overflow
        val r = line[4].toInt()
//        val perCharge = c * t * 1.0 / l
//        val perWeek = ( t + r ) / 10080.0
//        if (perCharge < perWeek) continue
        if ( c * t * 10080 - l * (t + r) < 0) continue
        if (p < minCost) {
            out = mutableListOf(line[0])
            minCost = p
        } else out.add(line[0])
    }
    for(i in out) println(i)
}