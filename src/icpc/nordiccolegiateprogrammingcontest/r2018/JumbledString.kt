package icpc.nordiccolegiateprogrammingcontest.r2018

fun main(args: Array<String>) {
    val (oo, ol, lo, ll) = readLine()!!.split(" ").map { it.toInt() }
    var zeros = pyrConvert(oo)
    var ones = pyrConvert(ll)
    if (oo == 0 && ol == 0 && lo == 0 && ll == 0) {
        println("1")
        return
    }
    if (ol != 0 || lo != 0) {
        if (zeros == 0) zeros = 1
        if (ones == 0) ones = 1
    }
    if (zeros < 0 || ones < 0 || ol + lo != zeros * ones) {
        println("impossible")
        return
    }
    val leadingOnes = if (zeros != 0) lo / zeros else ones
    val interOne = if (zeros != 0) lo % zeros else 0
    val out = StringBuilder()
    repeat(leadingOnes) { out.append("1") }
    repeat(zeros) { out.append("0") }
    if (ones > leadingOnes) out.insert(leadingOnes + zeros - interOne, "1")
    repeat(ones - 1 - leadingOnes) { out.append("1") }
    println(out)
}

fun pyrConvert(int: Int): Int {
    if (int == 0) return 0
    var temp = int
    var idx = 1
    while (temp > 0) {
        temp -= idx++
        if (temp == 0) return idx
    }
    return -2
}