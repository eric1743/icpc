package adventofcode

import java.io.File
import java.time.LocalDateTime

fun main(args: Array<String>) {
    val br = File("/Users/z00327d/src/tools/icpc/src/adventofcode/in/d1.txt").bufferedReader()

    val events = mutableMapOf<LocalDateTime, Int>()

    while (true) {
        var line = br.readLine()
        if (line.isNullOrEmpty()) break;
        line = Regex("[\\[\\]#]").replace(line, "")
        val arr = line.split(Regex("[-: ]"))

        val date = LocalDateTime.of(arr[0].toInt(), arr[1].toInt(), arr[2].toInt(), arr[3].toInt(), arr[4].toInt())
        val code = when (arr[5]) {
            "wakes" -> -2
            "falls" -> -1
            else -> arr[6].toInt()
        }
        events[date] = code
    }

    val sleepTotals = mutableMapOf<Int, MutableList<Int>>()

    var guard = 0
    var minute = 0
    val sorted = events.toSortedMap()
    for ((date, code) in sorted) {
        when (code) {
            -1 -> minute = date.minute
            -2 -> {
                if (sleepTotals[guard] == null) sleepTotals[guard] = MutableList(60) { 0 }
                for (t in minute until date.minute) sleepTotals[guard]!![t]++
            }
            else -> guard = code
        }
    }

    guard = 0
    var max = 0
    for ((num, sleep) in sleepTotals) {
        var tot = 0
        for(min in sleep) {
            tot += min
        }
        if (tot > max) {
            max = tot
            guard = num
        }
    }

    max = 0
    minute = 0
    for((min, cnt) in sleepTotals[guard]!!.withIndex()){
        if(cnt > max){
            max = cnt
            minute = min
        }
    }
    println("$guard * $minute = ${guard*minute}")
}

