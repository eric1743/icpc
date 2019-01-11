package icpc.midcentral.r2018

fun main(args: Array<String>) {
    val right = readLine()!!.toInt()
    val mine = readLine()!!
    val your = readLine()!!
    val n = mine.length
    var same = 0
    for(i in 0 until n){
        if (mine[i] == your[i]) same++
    }
    println(Math.min(right, same) + Math.min(n-right, n-same))
}