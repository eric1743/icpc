import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val t = br.readLine().toInt()

    val map = HashMap<String, MutableList<Int>>()
    repeat(t){
        val line = br.readLine().split(" ")
        if (! map.containsKey(line[0])) map.put(line[0], mutableListOf())
        map.get(line[0])?.add(line[1].toInt())
    }

    map.values.forEach{it.sort()}

    val q = br.readLine().toInt()
    repeat(q){
        val line = br.readLine().split(" ")
        bw.write("" + map.get(line[0])!![line[1].toInt() - 1] + "\n")
    }
    bw.flush()
}