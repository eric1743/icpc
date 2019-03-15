package icpc.nordiccolegiateprogrammingcontest.r2018

import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigInteger

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var line = br.readLine().split(" ")
    val n = line[0].toInt()
    var s = BigInteger(line[1])
    val out = mutableListOf<String>()
    val bids = mutableListOf<Pair<String,BigInteger>>()
    repeat(n){
        line = br.readLine().split(" ")
        bids.add(Pair(line[0], BigInteger(line[1])))
    }
    bids.sortBy{ it.second }
    var lastIdx = n
    while (true){
        var point = bids.binarySearch(0,lastIdx) { it.second.compareTo(s) }
        if (point == -1) out.removeAll { true }
        else if (point >= 0) out += bids[point].first
        else {
            point = (point + 2) * -1
            out += bids[point].first
            s -= bids[point].second
            lastIdx = point
            continue
        }
        break
    }
    println(out.size)
    for(bidder in out) println(bidder)
}