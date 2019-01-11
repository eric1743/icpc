package icpc.midcentral.r2018

import java.io.BufferedReader
import java.io.InputStreamReader

//fun main(args: Array<String>) {
//    val br = BufferedReader(InputStreamReader(System.`in`))
//    val (n, e, p) = br.readLine().split(" ").map { it.toInt() }
//    val map = List(n) { br.readLine().split(" ").map { it.toDouble() } }
//    val joined = List(n) { mutableListOf<Int>() }
//    repeat(p){
//        val (a,b) = br.readLine().split(" ").map { it.toInt() - 1 }
//        joined[a].add(b)
//        joined[b].add(a)
//    }
//    val linked = MutableList(n) { false }
//    val len = List(n) { i -> MutableList(n) { j -> dist(map[i], map[j]) } }
//    repeat(e) {
//        linked[it] = true
//        len[0].combine(len[it])
//        len[0].combine(joined[it].)
//        for(prev in joined[it]){
//            if(!linked[prev]){
//                linked[prev] = true
//                len[0].combine(len[prev])
//            }
//        }
//    }
//    while (true){
//        var idx = -1
//        var min =
//    }
//
//
//}

private fun MutableList<Double>.combine(a: MutableList<Double>) {
    repeat(this.size) {
        this[it] = Math.min(this[it], a[it])
    }
}

fun dist(a: List<Double>, b: List<Double>): Double {
    val x = a[0] - b[0]
    val y = a[1] - b[1]
    return Math.sqrt(x * x + y * y)
}