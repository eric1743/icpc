package icpc.finals.f2017

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var line = br.readLine().split(" ").map { it.toInt() }
    val m = line[0]
    val n = line[1]
    val sets = List(26) { mutableSetOf<Int>(it) }
    repeat(m) {
        line = br.readLine().split(" ").map { letter -> letter[0] - 'a' }
        sets[line[0]].add(line[1])
    }

    repeat(26) {
        sets[it].addAll(dfs(sets, mutableSetOf<Int>(), it) )
    }
    loop@ for (i in 0 until n) {
        val words = br.readLine().split(" ")
        val first = words[0]
        val second = words[1]
        if (first.length != second.length) {
            println("no")
            continue@loop
        }
        for (j in 0 until first.length) {
            if (!sets[first[j] - 'a'].contains(second[j] - 'a')) {
                println("no")
                continue@loop
            }
        }
        println("yes")
    }

}

fun dfs(sets: List<MutableSet<Int>>, visited: MutableSet<Int>, start: Int): MutableSet<Int> {
    visited.add(start)
    for (path in sets[start]) {
        if(!visited.contains(path))
            visited.addAll(dfs(sets, visited, path))
    }
    return visited
}