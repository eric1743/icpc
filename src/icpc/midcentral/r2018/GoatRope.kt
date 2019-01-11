package icpc.midcentral.r2018

fun main(args: Array<String>) {
    val line = readLine()!!.split(" ").map { it.toInt() }
    val gx = line[0]
    val gy = line[1]
    val x1 = line[2]
    val y1 = line[3]
    val x2 = line[4]
    val y2 = line[5]
    if (gx >= x1 && gx <= x2) {
        println(if (gy < y1)
            y1 - gy
        else
            gy - y2)
    } else if (gy >= y1 && gy <= y2) {
        println(if (gx < x1)
            x1 - gx
        else
            gx - x2)
    } else {
        var out = dist(gx, gy, x1, y1)
        out = Math.min(out, dist(gx, gy, x1, y2))
        out = Math.min(out, dist(gx, gy, x2, y2))
        out = Math.min(out, dist(gx, gy, x2, y1))
        println(out)
    }
}

private fun dist(a: Int, b: Int, c: Int, d: Int): Double {
    val x = c - a.toDouble()
    val y = d - b.toDouble()
    return Math.sqrt(x * x + y * y)
}