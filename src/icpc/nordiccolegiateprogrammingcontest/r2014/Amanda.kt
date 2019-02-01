package icpc.nordiccolegiateprogrammingcontest.r2014

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
//    val br = File("/Users/z00327d/Downloads/ncpc2014testdata/amanda/conbip.1.in").bufferedReader()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val graph = List(n) { Node(it) }
    var out = 0
    try {
        repeat(m) { graph.ingest(br.readLine()) }
        for (node in graph) {
            if (node.visited) continue
            when (node.state) {
                2 -> out++
                1 -> {
                    val ret = node.netMin(false)
                    out += minOf(ret.first, ret.second)
                }
                else -> { } //node.state == 0
            }
        }
        println(out)
    } catch (e: Exception) {
        println("impossible")
    }
}

private fun List<Node>.ingest(line: String) {
    var (a, b, c) = line.split(" ").map { it.toInt() }
    a--
    b--
    var aColor = this[a].paint(c)
    var bColor = this[b].paint(c)
    this[a].links += this[b]
    this[b].links += this[a]
    when (c) {
        0, 2 -> if (aColor != c || bColor != c) throw Exception() //if tried to pain 0 or 2 and they're not 0 or 2
        else -> when (aColor + bColor) { //c == 1
                0, 4 -> throw Exception() //both are 0 or both are 2
                2 -> { }
                else -> { //1,3
                    if (aColor == 1) aColor = this[a].paint(!bColor)
                    else bColor = this[b].paint(!aColor)
                    if (aColor + bColor != 2) throw Exception()
                }
            }
    }
}

private class Node(val id: Int) {
    var state = -1
    var tag = false
    var visited = false
    val links = mutableListOf<Node>()
    fun paint(c: Int): Int {  //returns the state of the node after painting
        when (state) {
            -1, c -> state = c
            0, 2 -> if (c != 1) throw Exception() //state is 0 or 2 && state != c
            else ->  //state = 1 , c != 1
                for (node in links) {
                    state = c
                    if (node.state == 1 && !c != node.paint(!c)) throw Exception() //
                }
        }
        return state
    }

    fun netMin(toTag: Boolean): Pair<Int, Int> {
        visited = true
        tag = toTag
        var ret = if (tag) Pair(0, 1) else Pair(1, 0)
        for (node in links) {
            if (node.state != 1) continue
            if (!node.visited) ret += node.netMin(!tag)
            else if (node.tag == tag) throw Exception()
        }
        return ret
    }
}

operator fun Pair<Int, Int>.plus(o: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + o.first, this.second + o.second)
}

operator fun Int.not(): Int {
    return if (this == 0) 2 else 0
}