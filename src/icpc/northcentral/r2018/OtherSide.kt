package icpc.northcentral.r2018

fun main(args: Array<String>) {
    val line = readLine()!!.split(" ").map { it.toInt() }
    val w = line[0] + line[2]
    val s = line[1]
    val k = line[3]
    if (s < k || w < k) println("YES")
    else if (w == k && s <= 2 * k) println("YES")
    else if (s == k && w <= 2 * k) println("YES")
    else println("NO")
}