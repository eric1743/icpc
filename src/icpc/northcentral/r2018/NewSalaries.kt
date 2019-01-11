package icpc.northcentral.r2018

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun main(args: Array<String>) {
//    val br = File("/Users/z00327d/Downloads/NCNA2018Contest/newsalaries/data/secret/16.in").bufferedReader()
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val n2 = 1.0 / n / n
    val salaries = List(n) { br.readLine().split(" ").map { it.toDouble() } }

//    val (l0, r0) = salaries[0]
//    var a = (l0 + r0) / 2 // (L + R) / 2
//    var b = (r0 * r0 * r0) / 3 / (r0 - l0) // R^3 / 3(R-L) for all colliding
//    var c = (r0 * r0) / (r0 - l0) // R^2 / (R-L) for all colliding
//    var d = r0 / (r0 - l0) // R / (R-L) for all colliding
//    var e = 1 / (r0 - l0) // 1 / (R-L) for all colliding
    var (a, b, c, d, e) = List(5) { 0.0 }
    var colIdx = 0
    var out = 0.0

    for (i in 0 until n) {
        val (li, ri) = salaries[i]
        while (salaries[colIdx][1] <= li) {  //needed?
            val (ld, rd) = salaries[colIdx]
            if (ld != rd) {
                b -= (rd * rd * rd) / 3 / (rd - ld)
                c -= (rd * rd) / (rd - ld)
                d -= rd / (rd - ld)
                e -= 1.0 / 3 / (rd - ld)
            }
            colIdx++
        }

        out += i * n2 * (li + ri) / 2
        out -= a * n2
        a += (li + ri) / 2
        if (li != ri) {
            out += b * n2 / (ri - li)
            out -= c * n2 * li / (ri - li)
            out += d * n2 * li * li / (ri - li)
            out -= e * n2 * li * li * li / (ri - li)

            b += (ri * ri * ri) / 3 / (ri - li)
            c += (ri * ri) / (ri - li)
            d += ri / (ri - li)
            e += 1.0 / 3 / (ri - li)
        }
    }
    println(out)
}