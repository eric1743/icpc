package icpc.nordiccolegiateprogrammingcontest.r2018

fun main(args: Array<String>) {
    readLine()
    val days = readLine()!!.split(" ").map { it.toInt() }
    var out = 0
    var buff = mutableListOf<Int>()
    for(day in days){
        val debt = buff.map { day - it }.sum()
        if (debt >= 19) {
            buff = mutableListOf()
            out++
        }
        if (debt != 19) buff.add(day)
    }
    if (buff.size > 0) out++
    println(out)
}