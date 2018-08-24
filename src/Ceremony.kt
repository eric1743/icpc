import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()

    val line = br.readLine().split(" ")
    val x = MutableList<Int>(n) {i -> line[i].toInt()}
    x.sort()

    var out = n
    x.forEachIndexed { i, it ->
        if(i >= it ) {
            out = Math.min(it + n - 1 - i, out)
        }
    }

    println(out)

}