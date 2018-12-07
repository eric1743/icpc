package icpc.singapore.r2015

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.coroutines.experimental.coroutineContext

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val raw = br.readLine()
    val rawLen = raw.length
    val rose = hashMapOf('^' to -n, '>' to 1, 'v' to n, '<' to -1)
    val input = List(rawLen) { rose[raw[it]] ?: 0 } //elvis to avoid null in list

    var r = 0
    val map = mutableListOf<Boolean>()
    repeat(n) { _ ->
        val line = br.readLine()
        repeat(n) {
            if (line[it] == 'R') r = map.size
            map.add(line[it] != '#')
        }
    }

    val breadcrumbs = List(n * n) { mutableSetOf<Int>() }
    var inputIdx = 0

    while (breadcrumbs[r].add(inputIdx)) {
        if (map[r + input[inputIdx]]) r += input[inputIdx]
        inputIdx = (inputIdx + 1) % rawLen
    }

    val origin = r
    val originIdx = inputIdx

    val originLog = mutableSetOf(0)
    val observable = mutableListOf(origin)
    var moved = false

    if (map[r + input[inputIdx]]) {
        r += input[inputIdx]
        moved = true
    } else {
        moved = false
    }
    inputIdx = (inputIdx + 1) % rawLen
    while (origin != r || originIdx != inputIdx) {
        if (moved && origin == r) originLog.add(observable.size)
        observable += r

        if (map[r + input[inputIdx]]) {
            r += input[inputIdx]
            moved = true
        } else {
            moved = false
        }
        inputIdx = (inputIdx + 1) % rawLen
    }

    val len = observable.size
    if(originLog.isEmpty()) {
        println(len)
        return
    }

    loop@for(i in originLog.sorted()){ //loop, sorted
        if(i % len != 0) continue
        var listOfStarts = mutableListOf<Int>()
        for (j in 1..(len/i)){
            if (!originLog.contains(j*i)) continue@loop
        }
        val first = observable.subList(0,i)
        for (j in 1..(len/i)){
            if (observable.subList(i*j, (i*j)+i) != first) continue@loop
        }
    }
}
