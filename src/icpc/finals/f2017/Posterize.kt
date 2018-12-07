package icpc.finals.f2017

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var line = br.readLine().split(" ")
    val d = line[0].toInt()
    val k = line[1].toInt()
    val img = MutableList<Long>(256) { 0L }
    var lo = 0
    var hi = 0
    repeat(d) {
        line = br.readLine().split(" ")
        if (it == 0) lo = line[0].toInt()
        if (it == d-1) hi = line[0].toInt()
        img[line[0].toInt()] = line[1].toLong()
    }
    val kArr = MutableList(k) { 0 }
    for (i in 0 until k) {
        kArr[i] = ((i + 1) * (hi-lo) / (k + 1)) + lo
    }
    var wiggled = true
    var minErr = eval(img, kArr)
    while (wiggled) {
        wiggled = false
        repeat(kArr.size) {
            //add checks
            val k0 = kArr[it]
            kArr[it] = k0 + 1
            val errUp = eval(img, kArr)
            kArr[it] = k0 - 1
            val errDn = eval(img, kArr)
            kArr[it] = k0
            if (errUp < minErr) {
                minErr = errUp
                wiggled = true
                kArr[it] = k0 + 1
            }
            if (errDn < minErr) {
                minErr = errDn
                wiggled = true
                kArr[it] = k0 - 1
            }
        }
    }
    println(minErr)

}

fun eval(img: MutableList<Long>, kArr: MutableList<Int>): Long {
    var ret = 0L
    for ((lev, count) in img.withIndex()) {
        var min = Int.MAX_VALUE
        for (k in kArr) min = Math.min(min, Math.abs(k - lev)) //optimize
        ret += (min * min) * count
    }
    return ret
}