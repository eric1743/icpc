package riddler

import java.nio.file.Files.move

fun main(args: Array<String>) {
    val raw = "LUU?ULXL" +
            "RLRLUGUU" +
            "SLRLULXR" +
            "UR?RSL?R" +
            "RUURRRSL" +
            "S?SLSSLR" +
            "RLR?RL?L" +
            "LRSRSLRL"
    val map = List(64) { Space(it, raw[it]) }
    val queue = mutableListOf<Path>()
    repeat(8) { queue.add(Path(emptyList(), it, 2)) }
    repeat(8) { queue.add(Path(emptyList(), it * 8, 1)) }
    repeat(8) { queue.add(Path(emptyList(), it + 56, 0)) }
    repeat(8) { queue.add(Path(emptyList(), it * 8 + 7, 3)) }

    while (queue.isNotEmpty()) {
        val path = queue.removeAt(0)
        queue.addAll(path.forward(map[path.loc]))
    }
    println(map[13].paths)
}

class Space(val id: Int, code: Char) {
    val row = id / 8
    val col = id % 8
    val paths = MutableList<Path?>(4) { null }
    val instruction = when (code) {
        'S' -> Instruction.STRAIGHT
        'R' -> Instruction.RIGHT
        'U' -> Instruction.UTURN
        'L' -> Instruction.LEFT
        '?' -> Instruction.ANY
        'X' -> Instruction.STOP
        'G' -> Instruction.GOAL
        else -> throw Exception()
    }

    fun validMove(dir: Int): Boolean {
        when(dir){
            0 -> return row != 0
            1 -> return col != 7
            2 -> return row != 7
            3 -> return col != 0
            else -> throw Exception()
        }
    }
}

class Path(soFar: List<Int>, val loc: Int, val dir: Int) {
    val route = soFar + loc
    fun forward(space: Space): List<Path> {
        if (space.paths[dir] != null) return emptyList() //if already visited give up
        space.paths[dir] = this
        when (space.instruction){
            Instruction.STOP, Instruction.GOAL -> {}
            Instruction.ANY ->
                return (0..3).filter{dir -> space.validMove(dir)}.map { dir -> this.spawn(dir) }
            else -> { //straight, right, uturn, left
                val nextDir = space.instruction.nextDir(dir)
                if (space.validMove(nextDir)) return listOf(this.spawn(nextDir))
            }
        }
        return emptyList()
    }
    fun spawn(nextDir: Int): Path{
        val nextLoc = when(nextDir){
            0 -> loc - 8
            1 -> loc + 1
            2 -> loc + 8
            3 -> loc - 1
            else -> throw Exception()
        }
        return Path(route, nextLoc, nextDir)
    }
}

enum class Instruction(val nextDir: (Int) -> Int) {
    STRAIGHT({ dir -> dir }),
    RIGHT({ dir -> (dir + 1) % 4 }),
    UTURN({ dir -> (dir + 2) % 4 }),
    LEFT({ dir -> (dir + 3) % 4 }),
    ANY({ dir -> -1 }),
    STOP({ dir -> -1 }),
    GOAL({ dir -> -1 });
}