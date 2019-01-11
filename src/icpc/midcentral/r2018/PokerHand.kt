package icpc.midcentral.r2018

fun main(args: Array<String>) {
    val line = readLine()!!.split(" ")
    val cnt = MutableList(13){0}
    var curMax = 0
    repeat(5){
        val rank = line[it][0]
        val idx = when(rank){
            'A' -> 0
            'T' -> 9
            'J' -> 10
            'Q' -> 11
            'K' -> 12
            else -> rank.toString().toInt() - 1
        }
        cnt[idx]++
        curMax = Math.max(cnt[idx], curMax)
    }
    println("$curMax")
}