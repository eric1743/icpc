package icpc.northcentral.r2018

fun main(args: Array<String>) {
    val line = readLine()!!.split(" ").map { it.toInt() }
    val a = line[0]
    val b = line[1]
    val k = line[2]

    var out = 0
    loop@for(i in a..b){
        for(j in 2..k){
            val arr = MutableList(22) { 0 }
            var buff = i
            var num = 1

            for (k in 0..21) {
                val rem = buff % (num * j)
                arr[k] = rem / num
                num *= j
                buff -= rem
                if (buff == 0) break;
            }
            if (!arr.check()) continue@loop
        }
        out++
    }
    println(out)
}

private fun MutableList<Int>.check(): Boolean {
    var idx = 0
    for ((i, digit) in this.withIndex()) {
        if (digit != 0) idx = i
    }
    for (i in 0..idx) {
        if (idx <= i) return true
        if (this[i] != this[idx]) return false
        idx--
    }
    return true
}
