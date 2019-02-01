package icpc.nordiccolegiateprogrammingcontest.r2014

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val passengers = br.readLine().split(" ").map { it.toInt() - 1 }
    val loopId = MutableList(n) { -1 }
    val loopSize = mutableListOf<Int>()
    val loopLegs = mutableListOf<Int>()
    var loopIdx = 0
    loop@ for ((passenger, friend) in passengers.withIndex()) {
        if (loopId[passenger] != -1) continue
        val buff = mutableListOf(passenger) //passengers in this loop network not yet assigned
        val timestamps = MutableList(n) { -1 } //timestamps to help determine loop size
        var ts = 0
        timestamps[passenger] = ts++
        var next = friend
        var tempLoop = loopIdx
        while (loopId[next] == -1 && timestamps[next] == -1) {
            buff += next
            timestamps[next] = ts++
            next = passengers[next]
        }
        if (loopId[next] != -1) { //found existing loop network
            tempLoop = loopId[next]
            loopLegs[tempLoop] += buff.size
        } else if (timestamps[next] != -1) { //created own loop
            loopSize += ts - timestamps[next]
            loopLegs += buff.size
            loopIdx++
        }
        for (node in buff) loopId[node] = tempLoop
    }
    val doable = MutableList(n + 1) { false }
    doable[0] = true
    for (loop in 0 until loopIdx)
        for (idx in k downTo 0)
            if (doable[idx])
                for (j in loopSize[loop]..loopLegs[loop])
                    doable[idx + j] = true
    for (out in k downTo 0)
        if(doable[out]) {
            println(out)
            return
        }
}