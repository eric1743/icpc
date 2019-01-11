package riddler

fun main(args: Array<String>) {
    val home = listOf(
            mutableListOf(0.5, 0.0, 0.0, 0.0, 0.5),
            mutableListOf(0.5, 0.5, 0.0, 0.0, 0.0),
            mutableListOf(0.0, 0.5, 0.5, 0.0, 0.0),
            mutableListOf(0.0, 0.0, 0.5, 0.5, 0.0),
            mutableListOf(0.0, 0.0, 0.0, 0.0, 1.0)
    )
    val away = listOf(
            mutableListOf(0.6, 0.4, 0.0, 0.0, 0.0),
            mutableListOf(0.0, 0.6, 0.4, 0.0, 0.0),
            mutableListOf(0.0, 0.0, 0.6, 0.4, 0.0),
            mutableListOf(0.0, 0.0, 0.0, 0.6, 0.4),
            mutableListOf(0.0, 0.0, 0.0, 0.0, 1.0)
    )

    val comb = home * away
    var buff = home * away
    repeat(4){
        buff *= comb
    }
    println(buff)
}

operator fun List<MutableList<Double>>.times(o: List<MutableList<Double>>):  List<MutableList<Double>>{
    val ret = List(5) { MutableList(5) { 0.0 } }
    repeat(5) { row ->
        repeat(5) { col ->
            var buff = 0.0
            repeat(5) {
                buff += this[row][it] * o[it][col]
            }
            ret[row][col] = buff
        }
    }
    return ret
}