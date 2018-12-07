package icpc.northcentral.r2018

import java.io.*

fun main(args: Array<String>) {
//    val br = File("/Users/z00327d/src/tools/icpc/src/icpc/northcentral/r2018/secret/4.in").bufferedReader()
//    val ar = File("/Users/z00327d/src/tools/icpc/src/icpc/northcentral/r2018/secret/4.ans").bufferedReader()
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var line = br.readLine().split(" ").map { it.toInt() }
    val n = line[0]
    val q = line[1]
    val buff = MutableList(n) { Pair(it, br.readLine()) }
    buff.sortBy { it.second }
    val map = MutableList(n) { 0 }
    for ((idx, poke) in buff.withIndex())
        map[poke.first] = idx
    val db = buff.map { it.second.toCharArray() }
    val memo = mutableMapOf<Long, Int>()
    repeat(q) {
        line = br.readLine().split(" ").map { it.toInt() }
        val k = line[0]
        val l = line[1]
        line = br.readLine().split(" ").map { it.toInt() - 1 }
        val mine = MutableList(k) { map[line[it]] }
        mine.sort()
        var ret = 0
        for (i in 0..k - l) {
            var rem = 0
            if (i != 0) rem = db.cpl(mine[i-1], mine[i], memo)
            if (i != k - l) rem = Math.max(rem, db.cpl(mine[i + l - 1], mine[i + l], memo))
            val out = db.cpl(mine[i], mine[i + l - 1], memo) - rem
            ret += Math.max(0, out)
        }
//        if (ret != ar.readLine().toInt()){
//            println("foo")
//        }
        bw.write("$ret\n")
    }
    bw.flush()
}

private fun  List<CharArray>.cpl(a: Int, b: Int, memo: MutableMap<Long, Int>): Int {
    val hash = a * 1000000L + b
    val ret = memo[hash] ?: -1
    if (ret != -1) return ret
    val c = this[a]
    val d = this[b]
    val len = Math.min(c.size, d.size)
    for (i in 0 until len) {
        if (c[i] != d[i]) {
            memo[hash] = i
            return i
        }
    }
    memo[hash] = len
    return len
}