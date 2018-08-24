fun main(args: Array<String>) {
    for (i in 0 until 5) {
        for (j in 0 until 5) {
            doMoves(getOne(i, j), i.toString() + "," + j + ":", i, j)
        }
    }
//    doMoves(getOne(0,1), "0,1:", 0,1)
    println(Stat2.squares.toString() + "\n${Stat2.paths}"+ "\n" + Stat2.moves)
}

fun doMoves(grid: Int, moves: String, row: Int, col: Int) {
    var count = count(grid)
    if (count > Stat2.squares){
        Stat2.squares = count
        Stat2.moves = moves + "\n"
    } else if (count == Stat2.squares){
        Stat2.moves += moves + "\n"
        Stat2.paths++
    }

    repeat(8) {
        doOneMove(grid, moves, row, col, it)
    }

}

fun doOneMove(oldGrid: Int, moves: String, oldRow: Int, oldCol: Int, dir: Int) {
    if (checkBounds( oldRow, oldCol, dir)) {
        val grid = checkPath(oldGrid, oldRow, oldCol, dir)
        if (grid != oldGrid) {
            var row = oldRow
            var col = oldCol
            when(dir){
                0 -> row += 3
                1 -> col += 3
                2 -> row -= 3
                3 -> col -= 3
                4 -> {
                    row += 2
                    col += 2
                }
                5 -> {
                    row -= 2
                    col += 2
                }
                6 -> {
                    row -= 2
                    col -= 2
                }
                7 -> {
                    row += 2
                    col -= 2
                }
            }
            doMoves(grid, moves + "$dir", row, col)
        }
    }
}

fun checkBounds(row: Int, col: Int, dir: Int): Boolean {
    when (dir) {
        0 -> return row <= 1
        1 -> return col <= 1
        2 -> return row >= 3
        3 -> return col >= 3
        4 -> return row <= 2 && col <= 2
        5 -> return row >= 2 && col <= 2
        6 -> return row >= 2 && col >= 2
        7 -> return row <= 2 && col >= 2
    }
    return false
}

fun checkPath(grid: Int, row: Int, col: Int, dir: Int): Int {
//    val mask = getMaskCovered(getOne(row, col), dir)
    val mask = getMaskUncovered(getOne(row, col), dir)
    if (mask and grid == 0) return mask + grid
    return grid
}

fun getMaskCovered(mask: Int, dir: Int): Int {
    return when (dir) {
        0 -> moveMask(mask,5,3)
        1 -> moveMask(mask,1,3)
        2 -> moveMask(mask,-5,3)
        3 -> moveMask(mask,-1,3)
        4 -> moveMask(mask,6,2)
        5 -> moveMask(mask,-4,2)
        6 -> moveMask(mask,-6,2)
        7 -> moveMask(mask,4,2)
        else -> -1
    }
}

fun getMaskUncovered(mask: Int, dir: Int): Int {
    return when (dir) {
        0 -> mask shl 15 //moveMask(mask,5,3)
        1 -> mask shl 3 //moveMask(mask,1,3)
        2 -> mask shr 15 //moveMask(mask,-5,3)
        3 -> mask shr 3 //moveMask(mask,-1,3)
        4 -> mask shl 12 //moveMask(mask,6,2)
        5 -> mask shr 8 //moveMask(mask,-4,2)
        6 -> mask shr 12 //moveMask(mask,-6,2)
        7 -> mask shl 8 //moveMask(mask,4,2)
        else -> -1
    }
}

fun moveMask(oldMask: Int, step: Int, times: Int): Int{
    var ret = if (step >0) oldMask shl step
    else oldMask shr -step
    var idx = ret

    repeat(times-1){
        idx = if(step > 0) idx shl step else idx shr -step
        ret += idx
    }
    return ret
}

fun getOne(row: Int, col: Int): Int {
    return 1 shl (col + row * 5)
}

class Stat2 {
    companion object {
        var squares = 0
        var moves = ""
        var paths = 0
    }
}

fun count(oldGrid: Int): Int{
    var out = 0
    var grid = oldGrid
    repeat(26){
        if (grid % 2 != 0) out++
        grid = grid shr 1
    }
    return out
}

fun getFullGrid(): Int {
    return 33554431
//    var out = 0
//    repeat(25){
//        out = (out shl 1) + 1
//    }
//    return out
}