import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {

    val br = BufferedReader(InputStreamReader(System.`in`))
    var line = br.readLine()
    while (!line.isNullOrBlank()) {
        val (a, b) = line!!.split(' ');
        val r = Math.abs(a.toLong() - b.toLong())
        println(r)
        line = br.readLine();
    }
}