import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val board = List(n) { br.readLine().toList() }
    var out = mutableListOf<MutableList<Int>>()
    repeat(n) { row ->
        val best = MutableList(n) { -1 }
        best[row] = 0
        var visited = 1 shl row
        for (cost in 1 until n) {
            var newVisited = visited
            loop@ for ((idx, space) in board.withIndex()) {  //foreach space on the board
                if (visited and (1 shl idx) == 0) {  //if the space hasn't been solved
                    for (moveSet in space) {
                        if (visited.inv() and moveSet == 0) {
                            newVisited = newVisited or (1 shl idx)
                            best[idx] = cost
                            continue@loop
                        }
                    }
                }
            }
            if (visited == newVisited) break
            visited = newVisited
        }
        out.add(best)
    }
    repeat(n) { row ->
        repeat(n) { col ->
            print(out[col][row].toString() + " ")
        }
        println()
    }
}

fun String.toList(): List<Int>{
    val line = this.split(" ")
    return List(line[0].toInt()) { line[it + 1].map { letter -> 1 shl (letter - 'a') }.sum() }
}