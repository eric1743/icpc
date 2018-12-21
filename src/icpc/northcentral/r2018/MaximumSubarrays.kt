package icpc.northcentral.r2018

fun main(args: Array<String>) {
    val k = readLine()!!.split(" ").map { it.toInt() }[1]
    var arr = readLine()!!.split(" ").map { it.toLong() }
    val positives = arr.map { it > 0 }.filter { it }.count()
    if (positives <= k) {
        arr = arr.sortedDescending()
        println(arr.subList(0, k).sum())
        return
    }
    val best = MutableList(k + 1) { 0L }
    val cur = MutableList(k + 1) { 0L }
    for (i in 0 until arr.size) {
        for (j in 1..Math.min(k, i + 1))
            cur[j] = Math.max(cur[j] + arr[i], best[j - 1] + arr[i])
        for (j in 1..Math.min(k, i + 1))
            best[j] = Math.max(cur[j], best[j])
    }
    println(best[k])
}
