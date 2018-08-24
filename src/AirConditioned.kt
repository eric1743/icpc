import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()

    val m = List<String>(n) {br.readLine()}
            .map {it -> it.split(" ")}
            .map {it -> Minion(it[0].toInt(), it[1].toInt())}
            .sortedBy { it.upper }


    var rooms = 1
    var threshhold = m[0]!!.upper
    repeat(n) {
        if(m[it]!!.lower > threshhold) {
            rooms++
            threshhold = m[it]!!.upper
        }
    }

    println(rooms)
}

class Minion(val lower: Int, val upper: Int)