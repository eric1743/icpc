package icpc.northcentral.r2018

fun main(args: Array<String>) {
    var n = readLine()!!.toInt()
    var denom = 1L
    var sum = 1.0
    n = Math.min(n, 17)
    repeat(n){
        denom *= (it + 1)
        sum += (1.0 / denom)
    }
    println(sum)
}
