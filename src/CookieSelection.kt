import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val bpq = PriorityQueue<Int>()
    val spq = PriorityQueue<Int> { x, y -> y - x }

    var line = br.readLine()
    while (!line.isNullOrEmpty()) {
        if ("#" == line) {
            bw.write(bpq.poll().toString())
            bw.newLine()
        } else {
            val goin = bpq.peek() ?: 0
            val comin = line.toInt()

            if (comin < goin) spq.add(comin)
            else bpq.add(comin)
        }
        if(bpq.size - spq.size > 1) spq.add(bpq.poll())
        if(spq.size > bpq.size) bpq.add(spq.poll())
        line = br.readLine()
    }
    bw.flush()
}