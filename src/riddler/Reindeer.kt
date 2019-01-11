package riddler

import java.util.*

fun main(args: Array<String>) {

    var avg = 0
    repeat(100000) {
        avg += sortDeer()
    }
    println(avg / 100000.0)
}

fun sortDeer(): Int {
    val rand = Random()
    var picked = MutableList(8) { it }
    val order = mutableListOf<Int>()
    repeat(7) {
        val idx = rand.nextInt(8 - it)
        order += picked[idx]
        picked.removeAt(idx)
    }
    order += picked[0]
    picked = MutableList(8) { it }
    var out = 0
    while (order.size != 0) {
        val rem = order[0]
        order.removeAt(0)
        order += rem
        val len = order.size
        out += len
        for (i in len - 1 downTo 0) {
            if (order[i] == picked[i]){
                order.removeAt(i)
                picked.removeAt(i)
            }
        }
    }
    return out
}