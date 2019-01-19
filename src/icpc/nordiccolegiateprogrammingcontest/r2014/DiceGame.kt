package icpc.nordiccolegiateprogrammingcontest.r2014

fun main(args: Array<String>) {
    val gunnar = readLine()!!.split(" ").map { it.toInt() }.sum()
    val emma = readLine()!!.split(" ").map { it.toInt() }.sum()
    if (emma > gunnar) println("Emma")
    if (emma < gunnar) println("Gunnar")
    if (emma == gunnar) println("Tie")
}