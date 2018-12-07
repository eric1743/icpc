package icpc.finals.f2018

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

//https://open.kattis.com/problems/newfiber

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var line = br.readLine().split(" ").map { it.toInt() }
    val n = line[0]
    val m = line[1]
    val arr = MutableList(n) { 0 }
    repeat(m) { _ ->
        line = br.readLine().split(" ").map { it.toInt() }
        arr[line[0]]++
        arr[line[1]]++
    }

    var q = List(n) { Node(it, arr[it] - 1, 0) }
            .sortedBy { it.origCon }
    var linksLeft = n - 2
    var satisfied = 0
    for (node in q) {
        if (node.origCon <= linksLeft) {
            node.children = node.origCon
            linksLeft -= node.origCon
            satisfied++
        } else {
            node.children = linksLeft
            break
        }
    }
    bw.write("" + (n - satisfied) + "\n")
    bw.write("" + n + " " + (n - 1) + "\n")

    q = q.sortedByDescending { it.children }
    q[0].children++
    var childIdx = 1
    var parIdx = 0
    while(childIdx < n){
        repeat(q[parIdx].children){
            bw.write(q[parIdx].adr.toString() + " " + q[childIdx++].adr + "\n")
        }
        parIdx++
    }
    bw.flush()
}

private data class Node(val adr: Int, var origCon: Int, var children: Int)