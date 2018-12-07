package naipc.f2018

import java.io.*
import java.util.*

fun main(args: Array<String>) {
//    val br = File("/Users/z00327d/src/tools/icpc/src/naipc/f2018/sample/redblack-0002.in").bufferedReader()
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val line = br.readLine().split(" ").map { it.toInt() }
    val n = line[0]
    val m = line[1]

    val tree = List(n + 1) { mutableListOf<Int>() }    //Lists children of each node
    repeat(n - 1) {
        tree[br.readLine().toInt()].add(it + 2)
    }

    val red = MutableList(n + 1) { false }
    repeat(m) {
        red[br.readLine().toInt()] = true
    }

    val answer = recur(1, tree, red)

    answer[0]++
    if (answer[0] == 1000000007L) answer[0] = 0 //not needed for the inputed values apparently
    repeat(m + 1) {
        bw.write("${answer.getOrElse(it) { 0 }}\n")
    }
    bw.flush()
}

fun recur(parent: Int, tree: List<MutableList<Int>>, red: MutableList<Boolean>): MutableList<Long> {
    if (tree[parent].isEmpty()) {
        return if (red[parent]) mutableListOf(0L, 1L)
        else mutableListOf(1L)
    }
    val children = MutableList(tree[parent].size) { recur(tree[parent][it], tree, red) }
    var ret = mutableListOf(0L)
    for (child in children) ret *= child
    if (!red[parent]) ret[0]++
    else if (ret.size != 1) ret[1]++
    else ret.add(1)
    return ret
}

operator fun MutableList<Long>.times(o: MutableList<Long>): MutableList<Long> {
    val ret = MutableList(this.size + o.size - 1) { 0L }
    for ((i, a) in this.withIndex()) {
        for ((j, b) in o.withIndex()) {
            ret[i + j] = (ret[i + j] + (a * b)) % 1000000007L
        }
    }
    ret += this
    ret += o
    ret.trim()
    return ret
}

operator fun MutableList<Long>.plusAssign(o: MutableList<Long>) {
    if (this.size > o.size) repeat(o.size) { this[it] += o[it] }
    else {
        repeat(this.size) { this[it] += o[it] }
        this.addAll(o.subList(this.size, o.size))
    }
    this.trim()
}

fun MutableList<Long>.trim() {
    val mod = 1000000007L
    var trimming = true
    var idx = this.size - 1
    while (idx >= 0) {
        if (trimming && this[idx] == 0L) this.removeAt(idx)
        else {
            trimming = false
            if (this[idx] >= mod) this[idx] %= mod
        }
        idx--
    }
}

