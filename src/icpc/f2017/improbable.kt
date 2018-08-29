package icpc.f2017

import java.io.BufferedReader
import java.io.InputStreamReader

//https://open.kattis.com/problems/improbable

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val line = br.readLine().split(" ").map { it.toInt() }
    val r = line[0]
    val c = line[1]
    val map = List(r) { br.readLine().split(" ").map { it.toLong() } }

    var boxes = 0L
    val bottom = List(r) { MutableList(c) { false } }
    val front = MutableList(c) { 0L }
    val side = MutableList(r) { 0L }
    repeat(r) { row ->
        repeat(c) { col ->
            var stack = map[row][col]
            bottom[row][col] = (stack != 0L)
            stack = Math.max(stack - 1, 0)  // leave one on the bottom
            boxes += stack
            front[col] = Math.max(front[col], stack)
            side[row] = Math.max(side[row], stack)
        }
    }

    val frontMap = front.toMap()
    val sideMap = side.toMap()
    var minRemain = 0L
    for ((height, sList) in sideMap) {
        minRemain += height * sList.size
        val fList = frontMap[height] ?: continue
        val edges = MutableList(sList.size) { MutableList(fList.size) { false } }
        for ((ridx, row) in sList.withIndex()) {
            for ((cidx, col) in fList.withIndex()) {
                edges[ridx][cidx] = bottom[row][col]
            }
        }
        val matches = maxBPM(edges)
        minRemain -= height * matches
    }
    for ((height, fList) in frontMap) {
        minRemain += height * fList.size
    }
    println(boxes - minRemain)
}

fun MutableList<Long>.toMap(): MutableMap<Long, MutableList<Int>> {
    val ret: MutableMap<Long, MutableList<Int>> = mutableMapOf()
    repeat(this.size) {
        ret[this[it]]?.add(it) ?: ret.put(this[it], mutableListOf(it))   //safecall + elvis
//        ret[this[it]]?.add(it) ?: (ret[this[it]] = mutableListOf(it))  // Assignments are not expressions
    }
    return ret
}

fun bpm(edges: MutableList<MutableList<Boolean>>, //DFS
        u: Int,
        seen: MutableList<Boolean>,
        matchR: MutableList<Int>): Boolean {
    repeat(edges[0].size) { v ->
        if (edges[u][v] && !seen[v]) {
            seen[v] = true
            if (matchR[v] < 0 || bpm(edges, matchR[v], seen, matchR)) {
                matchR[v] = u
                return true
            }
        }
    }
    return false
}

fun maxBPM(edges: MutableList<MutableList<Boolean>>): Int {
    val mtrH = edges.size
    val mtrW = edges[0].size
    val matchR = MutableList(mtrW) { -1 }
    var result = 0
    repeat(mtrH) {
        val seen = MutableList(mtrW) { false }
        if (bpm(edges, it, seen, matchR)) result++
    }
    return result
}