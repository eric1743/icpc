import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val line = br.readLine().split(" ")

//    val line = readLine()!!.split(" ")
    val n = line[0].toInt()
    val m = line[1].toInt()
    val list = mutableListOf<Int>()

    val arr = Array<Boolean>(n + 2, { false })
    repeat(m) {
        val x = br.readLine().toInt()
//        val x = readLine()!!.toInt()
        list.add(x)
        arr[x] = true
    }

    var idx = 1
    var g = 0
    var gnome = list[0]
    var printed = 0
    while (printed < n) {
        if (gnome <= idx) {
//            println(gnome)
            bw.append("" + gnome + "\n")
            printed++
            g++
            gnome = if (g == m) Int.MAX_VALUE else list[g]
        } else if (arr[idx]) {
            idx++
        } else if (idx <= n) {
//            println(idx++)
            bw.append("" + idx++ + "\n")
            printed++
        }
    }
    bw.flush()
}