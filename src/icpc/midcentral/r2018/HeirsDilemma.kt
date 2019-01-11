package icpc.midcentral.r2018

fun main(args: Array<String>) {
    val (lo, hi) = readLine()!!.split(" ").map { it.toInt() }
    var out = 0
    for(i in lo..hi){
        if(check(i)) out++
    }
    println(out)
}
fun check (a: Int): Boolean {
    var buff = a
    val digits = MutableList(10) { false }
    repeat(6){
        val digit = buff % 10
        buff /= 10
        if (digit == 0) return false
        if (digits[digit]) return false
        digits[digit] = true
        if (a % digit != 0) return false
    }
    return true
}