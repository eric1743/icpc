package riddler

import com.sun.tools.javac.jvm.ByteCodes.ret

fun main(args: Array<String>) {
    val board = List(3) { MutableList(3) { 0 } }
    val out = board.recur(0)
    println(out)
}

private fun List<MutableList<Int>>.recur(plays: Int): MutableList<Int> {
    val won = this.isWon()
    if (won == 1) return mutableListOf(0,1,0)
    if (won == 2) return mutableListOf(0,0,1)
    if (plays == 9) return mutableListOf(1,0,0)
    val ret = mutableListOf(0,0,0)
    repeat(9) {
        val row = it / 3
        val col = it % 3
        if (this[row][col] == 0) {
            this[row][col] = if (plays % 2 == 0) 1 else 2
            ret.combine(this.recur(plays + 1))
            this[row][col] = 0
        }
    }
    return ret
}

private fun MutableList<Int>.combine(a: MutableList<Int>){
    repeat(3){
        this[it] += a[it]
    }
}

private fun List<MutableList<Int>>.isWon(): Int {
    repeat(3) {
        if (this[it][0] != 0 && this[it][0] == this[it][1] && this[it][0] == this[it][2]) return this[it][0]
    }
    repeat(3) {
        if (this[0][it] != 0 && this[0][it] == this[1][it] && this[0][it] == this[2][it]) return this[0][it]
    }
    if (this[0][0] != 0 && this[0][0] == this[1][1] && this[0][0] == this[2][2]) return this[1][1]
    if (this[2][0] != 0 && this[2][0] == this[1][1] && this[2][0] == this[0][2]) return this[1][1]
    return 0
}
