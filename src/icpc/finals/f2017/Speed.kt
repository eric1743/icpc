package icpc.finals.f2017

import java.io.BufferedReader
import java.io.InputStreamReader

//https://open.kattis.com/problems/speed

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val line = br.readLine().split(" ")
    val n = line[0].toInt()
    val t = line[1].toDouble()
    val map = mutableMapOf<Int, Double>()
    var loInt = Int.MAX_VALUE
    repeat(n) {
        val leg = br.readLine().split(" ")
        val speed = leg[1].toInt()
        map[speed] = leg[0].toDouble() + (map[speed] ?: 0.0)
        loInt = Math.min(loInt, speed)
    }
    var hi = Int.MAX_VALUE.toDouble()
    var lo = -loInt.toDouble()
    repeat(64) {
        val mid = ( hi + lo ) / 2
        val time = eval(map, mid)
        if (time < t) hi = mid
        else lo = mid
    }
    println(hi)
}

fun eval(table: MutableMap<Int, Double>, c: Double): Double {
    return table.map { (speed, dist) -> dist / (speed + c) }.sum()
}