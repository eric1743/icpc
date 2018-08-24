import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    br.readLine() //consume n

    Stat.rs = br.readLine().split(" ").map { it.toDouble() }
    Stat.spec = br.readLine()

    println(process())

}

fun process(): Double {
    val rs = mutableListOf<Double>()
    var series = true

    loop@ while (true) {
        Stat.idx++
        when (Stat.charAt()) {
            '(' -> rs.add(process())
            'R' -> {
                Stat.idx++
                rs.add(Stat.rs[Stat.charAt().toInt() - 49])
            }
//          '-' -> nothing
            '|' -> series = false
            ')' -> break@loop
        }
    }

    if (series) return rs.sum()

    return 1 / rs.map { 1 / it }.sum()
}

private class Stat {
    companion object {
        var rs: List<Double> = emptyList()
        var spec: String = ""
        var idx = 0
        fun charAt(): Char {
            return spec[idx]
        }
    }
}