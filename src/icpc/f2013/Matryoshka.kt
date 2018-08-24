package icpc.f2013

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val lineup = br.readLine().split(" ").toList(n)
    if (!lineup.isWholeValid()) {
        println("impossible")
        return
    }
    val parts = List(n) { MutableList(n) { -1 } }
    repeat(n) { parts[it][it] = 0 }
    val wholes = MutableList<Int>(n + 1) { Int.MAX_VALUE }
    wholes[0] = 0
    for (start in 0 until n) {
        if (wholes[start] == Int.MAX_VALUE) continue
        for (end in start until n) {
            val segCost = lineup.getWholeCost(start, end, parts)
            if (segCost == -1) break  //duplicate
            if (segCost == -2) continue  //not complete segment
            wholes[end + 1] = Math.min(wholes[end + 1], wholes[start] + segCost)
        }
    }
    println(if (wholes[n] == Int.MAX_VALUE) "impossible" else wholes[n])
}

fun List<String>.toList(n: Int): List<Int> {
    return List(n) { this[it].toInt() }
}

fun List<Int>.isWholeValid(): Boolean {
    var highest = 0
    val count = MutableList(501) { 0 }
    count[0] = Int.MAX_VALUE
    this.forEach {
        count[it]++
        highest = Math.max(highest, it)
    }
    repeat(highest) {
        if (count[it + 1] > count[it]) return false
    }
    return true
}


fun List<Int>.getWholeCost(start: Int, end: Int, parts: List<MutableList<Int>>): Int {
    val map = sortedMapOf<Int, Int>()
    var hi = 0
    for (i in start..end) {
        if ((map.put(this[i], i)) != null) return -1 //Duplicates break
        hi = Math.max(hi, this[i])
    }
    if (map.size != hi) return -2 //Incomplete segment
    return this.getPartialCost(start, end, map, parts)
}

fun List<Int>.getPartialCost(start: Int, end: Int, map: SortedMap<Int, Int>, parts: List<MutableList<Int>>): Int {
    if (parts[start][end] != -1) return parts[start][end]
    var cost = Int.MAX_VALUE
    for (k in start until end) {
        val left = getPartialCost(start, k, map, parts)
        val right = getPartialCost(k + 1, end, map, parts)
        var assembly = end - start
        var isFirstHalf: Boolean? = null
        for (loc in map.values) {
            if (loc in start..end) {
                if (isFirstHalf == null) {
                    isFirstHalf = (loc <= k)
                } else if (isFirstHalf == (loc <= k)) {
                    assembly--
                } else break
            }
        }
        cost = Math.min(cost, left + right + assembly)
    }
    parts[start][end] = cost
    return cost
}